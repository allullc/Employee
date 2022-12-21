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
@Table(name = "empleado")
public class EmployeeEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "Nombre", nullable = false)
    private String name;

    @Column(name = "Apellido", nullable = false)
    private String lastName;

    @Column(name = "Fecha_Nacimiento", nullable = false)
    private Date birthDate;

    @Column(name = "Sueldo", nullable = false)
    private int salary;
}
