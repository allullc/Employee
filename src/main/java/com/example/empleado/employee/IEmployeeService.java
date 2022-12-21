package com.example.empleado.employee;

import com.example.empleado.employee.dto.EmployeeDto;

import java.util.List;
import java.util.UUID;

public interface IEmployeeService {
    public List<EmployeeEntity> getAll();
    public EmployeeEntity create(EmployeeEntity empleado);
    public EmployeeEntity update(UUID id, EmployeeDto empleado);
    public void delete(UUID id);

    public List<EmployeeEntity> findAllByOrderByBirthDateAsc();
    public List<EmployeeEntity> findAllByOrderBySalaryDesc();
}