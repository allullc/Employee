package com.example.commerce;

import com.example.commerce.employee.EmployeeController;
import com.example.commerce.employee.EmployeeEntity;
import com.example.commerce.employee.EmployeeService;
import com.example.commerce.util.NotFoundIdException;
import com.example.commerce.employee.dto.EmployeeCreateDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTests {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    public static final ObjectWriter ow = (new ObjectMapper())
            .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
            .writer()
            .withDefaultPrettyPrinter();

    @MockBean
    EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("JUnit test for getAll method in Controller")
    void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList() throws Exception {
        EmployeeEntity employee1 = EmployeeEntity
                .builder()
                .id(UUID.randomUUID())
                .name("Leonel")
                .lastName("Messi")
                .birthDate(new Date(1967, Calendar.MARCH, 3))
                .salary(24.67)
                .build();

        EmployeeEntity employee2 = EmployeeEntity
                .builder()
                .id(UUID.randomUUID())
                .name("Luis")
                .lastName("Garcia")
                .birthDate(new Date(1988, Calendar.MARCH, 8))
                .salary(400)
                .build();

        List<EmployeeEntity> employees = List.of(employee1, employee2);

        when(employeeService.getAll()).thenReturn(employees);

        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Leonel")));
    }

    @Test
    @DisplayName("JUnit test for create method with empty body in Controller")
    void givenEmptyBody_whenControllerCreate_thenJsonErrors() throws Exception {
        EmployeeCreateDto employeeCreateDto = EmployeeCreateDto
                .builder()
                .build();

        mockMvc.perform(post("/employee")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(this.WriteValueAsString(employeeCreateDto)))
                .andExpectAll(
                        status().is(400),
                        jsonPath("$", Matchers.aMapWithSize(4)),
                        jsonPath("$['name']", Matchers.is("Name is mandatory")),
                        jsonPath("$['lastName']", Matchers.is("LastName is mandatory")),
                        jsonPath("$['birthDate']", Matchers.is("BirthDate may not be null")),
                        jsonPath("$['salary']", Matchers.is("Salary may not be null"))
                );
    }

    @Test
    @DisplayName("JUnit test for create method with negative salary in Controller")
    void givenNegativeSalary_whenControllerCreate_thenJsonErrors() throws Exception {
        EmployeeCreateDto employeeCreateDto = EmployeeCreateDto
                .builder()
                .name("Luis")
                .lastName("Garcia")
                .birthDate(new Date(1988, Calendar.MARCH, 8))
                .salary(-4.80)
                .build();

        mockMvc.perform(post("/employee")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(this.WriteValueAsString(employeeCreateDto)))
                .andExpectAll(
                        status().is(400),
                        jsonPath("$", Matchers.aMapWithSize(1)),
                        jsonPath("$['salary']", Matchers.is("Salary must be greater than or equal to 0"))
                );
    }

//    @Test
//    @DisplayName("JUnit test for update method with empty body in Controller")
//    public void givenEmptyBody_whenControllerUpdate_thenJsonErrors() throws Exception {
//        UUID id = UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db");
//        EmployeeUpdateDto employeeUpdateDto = EmployeeUpdateDto
//                .builder()
//                .build();
//
//        EmployeeEntity employee = EmployeeEntity
//                .builder()
//                .id(id)
//                .name("Leonel")
//                .lastName("Messi")
//                .birthDate(new Date(1967, Calendar.MARCH, 3))
//                .salary(24.67)
//                .build();
//
//        when(employeeService.update(id, employeeUpdateDto)).thenReturn(employee);
//
//        mockMvc.perform(put("/employee/{id}", id)
//                        .contentType(APPLICATION_JSON_UTF8)
//                        .content(this.WriteValueAsString(employeeUpdateDto))
//                        .accept(APPLICATION_JSON_UTF8)
//                )
//                .andExpectAll(
//                        status().is(400),
//                        jsonPath("$", Matchers.aMapWithSize(4)),
//                        jsonPath("$['name']", Matchers.is("Name is mandatory")),
//                        jsonPath("$['lastName']", Matchers.is("LastName is mandatory")),
//                        jsonPath("$['birthDate']", Matchers.is("BirthDate may not be null")),
//                        jsonPath("$['salary']", Matchers.is("Salary may not be null"))
//                );
//    }

    @Test
    @DisplayName("JUnit test for delete method with correct id in Controller")
    void givenCorrectId_whenControllerDelete_thenStatusOk() throws Exception {
        UUID id = UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db");

        doNothing().when(this.employeeService).delete(any(UUID.class));

        mockMvc.perform(delete("/employee/{id}", id)
                        .contentType(APPLICATION_JSON_UTF8)
                )
                .andExpectAll(
                        status().isOk()
                );
    }

    @Test
    @DisplayName("JUnit test for delete method with incorrect id in Controller")
    void givenIncorrectId_whenControllerDelete_thenThrowError() throws Exception {
        UUID id = UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db");

        doThrow(new NotFoundIdException()).when(this.employeeService).delete(any(UUID.class));

        mockMvc.perform(delete("/employee/{id}", id)
                        .contentType(APPLICATION_JSON_UTF8)
                )
                .andExpectAll(
                        status().isNotFound(),
                        MockMvcResultMatchers.content()
                                .string("The id was not found")
                );
    }

    @Test
    @DisplayName("JUnit test for findAllByOrderByBirthDateAsc method in Controller")
    void givenEmployeesList_whenFindAllByOrderByBirthDateAsc_thenReturnEmployeesListByOrderByBirthDateAsc() throws Exception {
        EmployeeEntity employee1 = EmployeeEntity
                .builder()
                .id(UUID.randomUUID())
                .name("Leonel")
                .lastName("Messi")
                .birthDate(new Date(1967, Calendar.MARCH, 3))
                .salary(24.67)
                .build();

        EmployeeEntity employee2 = EmployeeEntity
                .builder()
                .id(UUID.randomUUID())
                .name("Luis")
                .lastName("Garcia")
                .birthDate(new Date(1988, Calendar.MARCH, 8))
                .salary(400)
                .build();

        List<EmployeeEntity> employees = List.of(employee1, employee2);

        when(employeeService.findAllByOrderByBirthDateAsc()).thenReturn(employees);

        mockMvc.perform(get("/employee/findAllByBirthDate"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$", Matchers.hasSize(2)),
                        jsonPath("$[0].name", Matchers.is("Leonel"))
                );
    }

    @Test
    @DisplayName("JUnit test for findAllByOrderBySalaryDesc method in Controller")
    void givenEmployeesList_whenFindAllByOrderBySalaryDesc_thenReturnEmployeesListByOrderBySalaryDesc() throws Exception {
        EmployeeEntity employee1 = EmployeeEntity
                .builder()
                .id(UUID.randomUUID())
                .name("Leonel")
                .lastName("Messi")
                .birthDate(new Date(1967, Calendar.MARCH, 3))
                .salary(24.67)
                .build();

        EmployeeEntity employee2 = EmployeeEntity
                .builder()
                .id(UUID.randomUUID())
                .name("Luis")
                .lastName("Garcia")
                .birthDate(new Date(1988, Calendar.MARCH, 8))
                .salary(400)
                .build();

        List<EmployeeEntity> employees = List.of(employee2, employee1);

        when(employeeService.findAllByOrderBySalaryDesc()).thenReturn(employees);

        mockMvc.perform(get("/employee/findAllBySalary"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$", Matchers.hasSize(2)),
                        jsonPath("$[0].name", Matchers.is("Luis")),
                        jsonPath("$[0].inputAFP", Matchers.is(40.0))
                );
    }

    private String WriteValueAsString(Object obj) throws JsonProcessingException {
        return ow.writeValueAsString(obj);
    }
}
