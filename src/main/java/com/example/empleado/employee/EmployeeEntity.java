package com.example.empleado.employee;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
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
    private String name;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    //TODO: Debe ser 2022-12-22
    @Column(name = "Birth_Date", nullable = false)
    private Date birthDate;

    @Column(name = "Salary", nullable = false)
    private double salary;
}
