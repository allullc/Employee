package com.example.commerce.seeder;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class DatabaseSeeder {
    private final JdbcTemplate jdbcTemplate;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        this.seedEmployeesTable();
    }

    private void seedEmployeesTable() {
        // Insert Employee Data
//        jdbcTemplate.execute(this.getEmployeeSQL());
    }

    private String getEmployeeSQL() {
        return "INSERT INTO employee (id, name, last_name, birth_date, salary)" +
                "SELECT 'c41f6764-e05e-407e-8402-2dd4d9abf47e', 'Leonel', 'Messi', '1968-03-03 00:00:00', 35.23" +
                "WHERE NOT EXISTS(SELECT 1 FROM employee WHERE id = 'c41f6764-e05e-407e-8402-2dd4d9abf47e');" +

                "INSERT INTO employee (id, name, last_name, birth_date, salary)" +
                "SELECT 'c41f6764-e04e-407e-8402-2dd4d9abf47e', 'Lautaro', 'Martinez', '1998-11-23 00:00:00', 2083.52" +
                "WHERE NOT EXISTS(SELECT 1 FROM employee WHERE id = 'c41f6764-e04e-407e-8402-2dd4d9abf47e');" +

                "INSERT INTO employee (id, name, last_name, birth_date, salary)" +
                "SELECT 'c41f6764-e06e-407e-8402-2dd4d9abf47e', 'Pedro', 'Gonzales', '2014-05-14 00:00:00', 1023.51" +
                "WHERE NOT EXISTS(SELECT 1 FROM employee WHERE id = 'c41f6764-e06e-407e-8402-2dd4d9abf47e');" +

                "INSERT INTO employee (id, name, last_name, birth_date, salary)" +
                "SELECT 'c41f6764-e07e-407e-8402-2dd4d9abf47e', 'Maira', 'Perez', '1968-02-16 00:00:00', 4083.45" +
                "WHERE NOT EXISTS(SELECT 1 FROM employee WHERE id = 'c41f6764-e07e-407e-8402-2dd4d9abf47e');" +

                "INSERT INTO employee (id, name, last_name, birth_date, salary)" +
                "SELECT 'c41f6764-e08e-407e-8402-2dd4d9abf47e', 'Sergio', 'Lamoru', '2001-08-13 00:00:00', 2950.34" +
                "WHERE NOT EXISTS(SELECT 1 FROM employee WHERE id = 'c41f6764-e08e-407e-8402-2dd4d9abf47e');" +

                "INSERT INTO employee (id, name, last_name, birth_date, salary)" +
                "SELECT 'c41f6764-e09e-407e-8402-2dd4d9abf47e', 'Melisa', 'Hernandez', '1994-08-03 00:00:00', 2012.78" +
                "WHERE NOT EXISTS(SELECT 1 FROM employee WHERE id = 'c41f6764-e09e-407e-8402-2dd4d9abf47e');";
    }
}
