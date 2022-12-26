package com.example.commerce.employee.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
    private UUID id;

    private String name;

    private String lastName;

    private Date birthDate;

    private double salary;
}
