package com.example.empleado.employee;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "empleado")
public class EmployeeEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "Nombre")
    private String name;

    @Column(name = "Apellido")
    private String lastName;

    @Column(name = "Fecha_Nacimiento")
    private Date birthDate;

    @Column(name = "Sueldo")
    private int salary;
}
