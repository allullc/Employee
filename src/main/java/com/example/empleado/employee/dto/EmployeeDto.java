package com.example.empleado.employee.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class EmployeeDto {
    private String name;

    private String lastName;

    private Date birthDate;

    private Integer salary;
}
