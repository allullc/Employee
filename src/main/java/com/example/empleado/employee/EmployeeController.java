package com.example.empleado.employee;

import com.example.empleado.employee.dto.EmployeeDto;
import com.example.empleado.employee.dto.EmployeeSalaryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @GetMapping()
    public ResponseEntity<List<EmployeeEntity>> getList() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping()
    public ResponseEntity<EmployeeEntity> create(@RequestBody EmployeeEntity empleado) {
        return ResponseEntity.ok(service.create(empleado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeEntity> update(@PathVariable("id") UUID id,
                                                 @RequestBody EmployeeDto empleado) {
        return ResponseEntity.ok(service.update(id, empleado));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return "Eliminado correctamente";
    }

    @GetMapping("/findAllByBirthDate")
    public ResponseEntity<List<EmployeeEntity>> findAllByOrderByBirthDateAsc(){
        return ResponseEntity.ok(service.findAllByOrderByBirthDateAsc());
    }

    @GetMapping("/findAllBySalary")
    public ResponseEntity<List<EmployeeSalaryDto>> findAllByOrderBySalaryDesc(){
        List<EmployeeEntity> employeesEntity = service.findAllByOrderBySalaryDesc();
        List<EmployeeSalaryDto> employeesList = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();

        for (EmployeeEntity point : employeesEntity) {
            employeesList.add(objectMapper.convertValue(point, EmployeeSalaryDto.class));
        }
        return ResponseEntity.ok(employeesList);
    }
}
