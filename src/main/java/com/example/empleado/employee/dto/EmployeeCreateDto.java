package com.example.empleado.employee.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeCreateDto {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "LastName is mandatory")
    private String lastName;

    @NotNull(message = "BirthDate may not be null")
    private Date birthDate;

    @Min(value = 0, message = "Salary must be greater than or equal to 0")
    @NotNull(message = "Salary may not be null")
    private Double salary;
}
