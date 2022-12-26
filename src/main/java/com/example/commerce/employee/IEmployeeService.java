package com.example.commerce.employee;

import com.example.commerce.employee.dto.EmployeeUpdateDto;

import java.util.List;
import java.util.UUID;

public interface IEmployeeService {
    EmployeeEntity findOne(UUID id);
    List<EmployeeEntity> getAll();
    EmployeeEntity create(EmployeeEntity employee);
    EmployeeEntity update(UUID id, EmployeeUpdateDto employee);
    void delete(UUID id);

    List<EmployeeEntity> findAllByOrderByBirthDateAsc();
    List<EmployeeEntity> findAllByOrderBySalaryDesc();
}