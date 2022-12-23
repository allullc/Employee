package com.example.empleado.employee;

import com.example.empleado.employee.dto.EmployeeCreateDto;
import com.example.empleado.employee.dto.EmployeeDTO;
import com.example.empleado.employee.dto.EmployeeUpdateDto;
import com.example.empleado.employee.dto.EmployeeSalaryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final IEmployeeService service;

    private final ModelMapper modelMapper;

    @Operation(summary = "Get all employees from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all files"),
    })
    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getList() {
        List<EmployeeEntity> employees = service.getAll();

        return ResponseEntity.ok(
                employees.stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    @Operation(summary = "Create an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The employee was created successfully"),
    })
    @PostMapping()
    public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody EmployeeCreateDto employeeDTO) {
        EmployeeEntity employeeEntity = this.convertToEntity(employeeDTO);

        return ResponseEntity.ok(
                this.convertToDto(service.create(employeeEntity))
        );
    }

    @Operation(summary = "Updates the data of an employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The employee was successfully updated"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable("id") UUID id,
                                                 @Valid @RequestBody EmployeeUpdateDto employeeUpdateDto) {

        return ResponseEntity.ok(
                this.convertToDto(service.update(id, employeeUpdateDto))
        );
    }

    @Operation(summary = "Delete an employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The employee was successfully deleted"),
    })
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return "Deleted successfully";
    }

    @Operation(summary = "Find all employees ordered from largest to smallest")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all employees ordered from highest to lowest"),
    })
    @GetMapping("/findAllByBirthDate")
    public ResponseEntity<List<EmployeeDTO>> findAllByOrderByBirthDateAsc() {
        List<EmployeeEntity> employees = this.service.findAllByOrderByBirthDateAsc();

        return ResponseEntity.ok(employees.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Find all employees ordered by their salary from highest to lowest including the AFP amount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all employees ordered by their salary" +
                    "from highest to lowest including the AFP amount"),
    })
    @GetMapping("/findAllBySalary")
    public ResponseEntity<List<EmployeeSalaryDto>> findAllByOrderBySalaryDesc() {
        List<EmployeeEntity> employees = this.service.findAllByOrderBySalaryDesc();


        return ResponseEntity.ok(employees.stream()
                .map(this::convertToSalaryDto)
                .collect(Collectors.toList()));
    }

    private EmployeeDTO convertToDto(EmployeeEntity employeeEntity) {
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    private EmployeeSalaryDto convertToSalaryDto(EmployeeEntity employeeEntity) {
        return modelMapper.map(employeeEntity, EmployeeSalaryDto.class);
    }

    private EmployeeEntity convertToEntity(EmployeeCreateDto postDto) {
        return modelMapper.map(postDto, EmployeeEntity.class);
    }
}
