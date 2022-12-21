package com.example.empleado.employee;

import com.example.empleado.employee.dto.EmployeeDto;
import com.example.empleado.employee.dto.EmployeeSalaryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/empleado")
public class EmployeeController {

    private final IEmployeeService service;

    @Operation(summary = "Get all employees from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all files"),
    })
    @GetMapping()
    public ResponseEntity<List<EmployeeEntity>> getList() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Create an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The employee was created successfully"),
    })
    @PostMapping()
    public ResponseEntity<EmployeeEntity> create(@RequestBody EmployeeEntity empleado) {
        return ResponseEntity.ok(service.create(empleado));
    }

    @Operation(summary = "Updates the data of an employee by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The employee was successfully updated"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeEntity> update(@PathVariable("id") UUID id,
                                                 @RequestBody EmployeeDto empleado) {
        return ResponseEntity.ok(service.update(id, empleado));
    }

    @Operation(summary = "Delete an employee by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The employee was successfully deleted"),
    })
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return "Eliminado correctamente";
    }

    @Operation(summary = "Find all employees ordered from largest to smallest")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all employees ordered from highest to lowest"),
    })
    @GetMapping("/findAllByBirthDate")
    public ResponseEntity<List<EmployeeEntity>> findAllByOrderByBirthDateAsc() {
        return ResponseEntity.ok(service.findAllByOrderByBirthDateAsc());
    }

    @Operation(summary = "Find all employees ordered by their salary from highest to lowest including the AFP amount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all employees ordered by their salary" +
                    "from highest to lowest including the AFP amount"),
    })
    @GetMapping("/findAllBySalary")
    public ResponseEntity<List<EmployeeSalaryDto>> findAllByOrderBySalaryDesc() {
        List<EmployeeEntity> employeesEntity = service.findAllByOrderBySalaryDesc();
        List<EmployeeSalaryDto> employeesList = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();

        for (EmployeeEntity point : employeesEntity) {
            employeesList.add(objectMapper.convertValue(point, EmployeeSalaryDto.class));
        }
        return ResponseEntity.ok(employeesList);
    }
}
