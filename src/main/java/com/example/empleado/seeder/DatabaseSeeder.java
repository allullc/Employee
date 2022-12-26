package com.example.empleado.seeder;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
//        String sql = "INSERT INTO employee (id, birth_date, last_name, name, salary)" +
//                "SELECT 'c41f6764-e05e-407e-8402-2dd4d9abf47e', '1968-03-03 00:00:00', 'Messi', 'Leonel', 35" +
//                "WHERE NOT EXISTS(SELECT 1 FROM employee WHERE id = 'c41f6764-e05e-407e-8402-2dd4d9abf47e');";
//        jdbcTemplate.execute(this.getSql());
    }

    public String getSql() {
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;
        StringBuilder sql = new StringBuilder();

        try {
            file = new File("data.sql");
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null)
                sql.append(linea);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return sql.toString();
    }

}
