package com.example.empleado.employee.dto;

import lombok.*;

import javax.validation.constraints.Min;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeUpdateDto {
    private String name;

    private String lastName;

    private Date birthDate;

    @Min(value = 0, message = "Salary must be greater than or equal to 0")
    private Double salary;
}
