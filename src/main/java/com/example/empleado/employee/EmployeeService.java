package com.example.empleado.employee;

import com.example.empleado.employee.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepository repository;

    @Override
    public List<EmployeeEntity> getAll() {
        return this.repository.findAll();
    }

    @Override
    public EmployeeEntity create(EmployeeEntity employee) {
        return this.repository.save(employee);
    }

    @Override
    public EmployeeEntity update(UUID id, EmployeeDto employee) {
        EmployeeEntity point = repository.findById(id).orElse(null);

        if (point == null) return null;

        if (employee.getName() != null) {
            point.setName(employee.getName());
        }

        if (employee.getSalary() != null) {
            point.setSalary(employee.getSalary());
        }

        if (employee.getLastName() != null) {
            point.setLastName(employee.getLastName());
        }

        if (employee.getBirthDate() != null) {
            point.setBirthDate(employee.getBirthDate());
        }

        return repository.save(point);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<EmployeeEntity> findAllByOrderByBirthDateAsc(){
        return repository.findAllByOrderByBirthDateAsc();
    }

    @Override
    public List<EmployeeEntity> findAllByOrderBySalaryDesc(){
        return repository.findAllByOrderBySalaryDesc();
    }
}
