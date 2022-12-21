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
    public EmployeeEntity create(EmployeeEntity empleado) {
        return this.repository.save(empleado);
    }

    @Override
    public EmployeeEntity update(UUID id, EmployeeDto empleado) {
        EmployeeEntity point = repository.findById(id).orElse(null);

        if (point == null) return null;

        if (empleado.getName() != null) {
            point.setName(empleado.getName());
        }

        if (empleado.getSalary() != null) {
            point.setSalary(empleado.getSalary());
        }

        if (empleado.getLastName() != null) {
            point.setLastName(empleado.getLastName());
        }

        if (empleado.getBirthDate() != null) {
            point.setBirthDate(empleado.getBirthDate());
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
