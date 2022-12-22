package com.example.empleado.employee.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class EmployeeSalaryDto {
    private UUID id;

    private String name;

    private String lastName;

    private Date birthDate;

    private double salary;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private double inputAFP;

    public double getInputAFP() {
        return Math.round((this.salary * 0.1) * 100.0) / 100.0;
    }
}
