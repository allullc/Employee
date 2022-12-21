package com.example.empleado.employee.dto;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto {
    private String name;

    private String lastName;

    private Date birthDate;

    private Integer salary;
}
