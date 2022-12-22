package com.example.empleado.employee;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee")
public class EmployeeEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "Name", nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "LastName", nullable = false)
    @NotBlank(message = "LastName is mandatory")
    private String lastName;

    @Column(name = "Birth_Date", nullable = false)
    @NotNull(message = "BirthDate may not be null")
    private Date birthDate;

    @Column(name = "Salary", nullable = false)
    @Min(value = 0, message = "Salary must be greater than or equal to 0")
    @NotNull(message = "Salary may not be null")
    private double salary;
}
