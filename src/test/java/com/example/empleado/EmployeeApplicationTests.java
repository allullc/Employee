package com.example.empleado;

import com.example.empleado.employee.EmployeeEntity;
import com.example.empleado.employee.IEmployeeService;

import com.example.empleado.employee.dto.EmployeeDto;
import com.example.empleado.employee.dto.EmployeeSalaryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SpringBootTest
class EmployeeApplicationTests {
    @Autowired
    private IEmployeeService service;

    @Test
    void employeeCRUD() {

        List<EmployeeEntity> employeesInit = this.service.getAll();

        //-Create ////////////////////////////////////////////////////////////////////////
        EmployeeEntity employeeEntity = EmployeeEntity
                .builder()
                .name("Juan")
                .lastName("Perez")
                .birthDate(new Date(1996, 5, 3))
                .salary(23)
                .build();

        EmployeeEntity employeeCreate = this.service.create(employeeEntity);

        assertEquals("Juan", employeeCreate.getName());
        assertEquals("Perez", employeeCreate.getLastName());
        assertEquals(new Date(1996, 5, 3), employeeCreate.getBirthDate());
        assertEquals(23, employeeCreate.getSalary());

        List<EmployeeEntity> employeesAfterCreate = this.service.getAll();

        assertEquals(employeesInit.size() + 1, employeesAfterCreate.size());

        //-Update ////////////////////////////////////////////////////////////////////////

        EmployeeDto employeeDto = EmployeeDto
                .builder()
                .name("Pedro")
                .salary(190.0)
                .build();

        EmployeeEntity employeeUpdate = this.service.update(employeeCreate.getId(), employeeDto);

        assertNotNull(employeeUpdate);
        assertEquals("Pedro", employeeUpdate.getName());
        assertEquals("Perez", employeeUpdate.getLastName());
        assertEquals(new Date(1996, 5, 3), employeeUpdate.getBirthDate());
        assertEquals(190, employeeUpdate.getSalary());

        List<EmployeeEntity> employeesAfterUpdate = this.service.getAll();

        assertEquals(employeesAfterUpdate.size(), employeesAfterCreate.size());

        //-Delete ////////////////////////////////////////////////////////////////////////

        this.service.delete(employeeCreate.getId());

        List<EmployeeEntity> employeesAfterDelete = this.service.getAll();

        assertEquals(employeesAfterUpdate.size() - 1, employeesAfterDelete.size());
    }

    @Test
    void findAllByOrderByBirthDateAsc() {
        List<EmployeeEntity> employees = this.service.findAllByOrderByBirthDateAsc();
        List<EmployeeEntity> employeesSortList = new ArrayList<>(employees);
        employeesSortList.sort((e1, e2) -> Long.compare(e1.getBirthDate().getTime(), e2.getBirthDate().getTime()));

        // Check that the array is sorted ////////////////////////////////////////////////////
        assertArrayEquals(employees.toArray(), employeesSortList.toArray());
    }

    @Test
    void findAllByOrderBySalaryDesc() {
        List<EmployeeEntity> employees = this.service.findAllByOrderBySalaryDesc();
        List<EmployeeEntity> employeesSortList = new ArrayList<>(employees);
        employeesSortList.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));

        // Check that the array is sorted ////////////////////////////////////////////////////
        assertArrayEquals(employees.toArray(), employeesSortList.toArray());

        // Check that the AFP amount is calculated correctly ////////////////////////////////
        EmployeeSalaryDto employeesSalary;
        ObjectMapper objectMapper = new ObjectMapper();

        for (EmployeeEntity point : employees) {
            employeesSalary = objectMapper.convertValue(point, EmployeeSalaryDto.class);
            assertEquals((Math.round((employeesSalary.getSalary() * 0.1) * 100.0) / 100.0),
                    employeesSalary.getInputAFP());
        }
    }

}
