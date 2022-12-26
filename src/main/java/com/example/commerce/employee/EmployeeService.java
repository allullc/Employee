package com.example.commerce.employee;

import com.example.commerce.employee.dto.EmployeeUpdateDto;
import com.example.commerce.util.NotFoundIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepository repository;

    @Override
    public EmployeeEntity findOne(UUID id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return this.repository.findAll();
    }

    @Override
    public EmployeeEntity create(EmployeeEntity employee) {
        return this.repository.save(employee);
    }

    @Override
    public EmployeeEntity update(UUID id, EmployeeUpdateDto employeeUpdateDto) {
        EmployeeEntity point = this.repository.findById(id).orElse(null);

        if (point == null) throw new NotFoundIdException();

        if (employeeUpdateDto.getName() != null) {
            point.setName(employeeUpdateDto.getName());
        }

        if (employeeUpdateDto.getSalary() != null) {
            point.setSalary(employeeUpdateDto.getSalary());
        }

        if (employeeUpdateDto.getLastName() != null) {
            point.setLastName(employeeUpdateDto.getLastName());
        }

        if (employeeUpdateDto.getBirthDate() != null) {
            point.setBirthDate(employeeUpdateDto.getBirthDate());
        }

        return this.repository.save(point);
    }

    @Override
    public void delete(UUID id) throws NotFoundIdException {
        try {
            this.repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundIdException();
        }
    }

    @Override
    public List<EmployeeEntity> findAllByOrderByBirthDateAsc() {
        return repository.findAllByOrderByBirthDateAsc();
    }

    @Override
    public List<EmployeeEntity> findAllByOrderBySalaryDesc() {
        return repository.findAllByOrderBySalaryDesc();
    }
}
