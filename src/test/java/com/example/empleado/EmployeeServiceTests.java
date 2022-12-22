package com.example.empleado;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.empleado.employee.EmployeeEntity;
import com.example.empleado.employee.EmployeeService;
import com.example.empleado.employee.IEmployeeRepository;
import com.example.empleado.employee.dto.EmployeeUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTests {
    @Mock
    private IEmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private EmployeeEntity employee;


    @BeforeEach
    public void setup(){
        this.employee = EmployeeEntity.builder()
                .name("Pedro")
                .lastName("Lautaro")
                .birthDate(new Date(1996, 5, 3))
                .salary(23.5)
                .build();
    }

    // JUnit test for saveEmployee method
    @DisplayName("JUnit test for create method in Service")
    @Test
    void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
        // given - precondition or setup
        given(employeeRepository.save(employee)).willReturn(employee);

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        // when -  action or the behaviour that we are going test
        EmployeeEntity savedEmployee = employeeService.create(employee);

        System.out.println(savedEmployee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    @DisplayName("JUnit test for getAll method in Service")
    @Test
    void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList(){
        // given - precondition or setup
        EmployeeEntity employee1 = EmployeeEntity.builder()
                .name("Brus")
                .lastName("Banner")
                .birthDate(new Date(2001, 2, 3))
                .salary(23.5)
                .build();

        given(employeeRepository.findAll()).willReturn(List.of(this.employee,employee1));

        // when -  action or the behaviour that we are going test
        List<EmployeeEntity> employeeList = employeeService.getAll();

        // then - verify the output
        assertAll("Should return a non-null list with size equal to 2",
                () -> assertThat(employeeList).isNotNull(),
                () -> assertThat(employeeList).hasSize(2)
        );
    }

    @DisplayName("JUnit test for getAllEmployees method (negative scenario)")
    @Test
    void givenEmptyEmployeesList_whenGetAllEmployees_thenReturnEmptyEmployeesList(){
        // given - precondition or setup
        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<EmployeeEntity> employeeList = employeeService.getAll();

        // then - verify the output
        assertAll("Should return a null list with size equal to 0",
                () -> assertThat(employeeList).isEmpty(),
                () -> assertThat(employeeList).hasSize(0)
        );
    }

    @DisplayName("JUnit test for update method in Service")
    @Test
    void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        UUID id = UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db");

        // given - precondition or setup
        EmployeeEntity employeeFindId = EmployeeEntity.builder()
                .name("Brus")
                .lastName("Banner")
                .birthDate(new Date(2001, 2, 3))
                .salary(23.5)
                .build();

        EmployeeEntity employeeSave = EmployeeEntity
                .builder()
                .name("Leonel")
                .lastName("Messi")
                .birthDate(new Date(2001, 2, 3))
                .salary(23.5)
                .build();

        EmployeeUpdateDto employeeUpdateDto = EmployeeUpdateDto
                .builder()
                .name("Leonel")
                .lastName("Messi")
                .build();

        given(employeeRepository.findById(id)).willReturn(Optional.of(employeeFindId));
        given(employeeRepository.save(employeeFindId)).willReturn(employeeSave);

        // when -  action or the behaviour that we are going test
        EmployeeEntity updatedEmployee = employeeService.update(id, employeeUpdateDto);

        // then - verify the output
        assertAll("Should return an employee with the name Leonel and the last name Messi",
                () -> assertThat(updatedEmployee.getName()).isEqualTo("Leonel"),
                () -> assertThat(updatedEmployee.getLastName()).isEqualTo("Messi")
        );
    }


    @DisplayName("JUnit test for update method null return in Service")
    @Test
    void givenEmployeeObject_whenUpdateEmployee_thenReturnNullUpdatedEmployee(){
        UUID id = UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db");

        // given - precondition or setup
        EmployeeUpdateDto employeeUpdateDto = EmployeeUpdateDto
                .builder()
                .name("Leonel")
                .lastName("Messi")
                .build();

        given(employeeRepository.findById(id)).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        EmployeeEntity updatedEmployee = employeeService.update(id, employeeUpdateDto);

        // then - verify the output
        assertNull(updatedEmployee);
    }

    @DisplayName("JUnit test for delete method in Service")
    @Test
    void givenEmployeeId_whenDeleteEmployee_thenNothing(){
        // given - precondition or setup
        UUID id = UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db");

        willDoNothing().given(employeeRepository).deleteById(id);

        // when -  action or the behaviour that we are going test
        employeeService.delete(id);

        // then - verify the output
        verify(employeeRepository, times(1)).deleteById(id);
    }


    @DisplayName("JUnit test for findAllByOrderByBirthDateAsc method in Service")
    @Test
    void givenEmployeesListSortedByBirthDate_whenFindAllByOrderByBirthDateAsc(){
        // given - precondition or setup
        EmployeeEntity employee1 = EmployeeEntity.builder()
                .name("Brus")
                .lastName("Banner")
                .birthDate(new Date(1994, 2, 3))
                .salary(23.5)
                .build();

        EmployeeEntity employee2 = EmployeeEntity
                .builder()
                .name("Leonel")
                .lastName("Messi")
                .birthDate(new Date(1996, 2, 3))
                .salary(78.9)
                .build();

        List<EmployeeEntity> employeesSortList = new ArrayList<>(List.of(employee1, employee2));
        employeesSortList.sort((e1, e2) -> Long.compare(e1.getBirthDate().getTime(), e2.getBirthDate().getTime()));

        given(employeeRepository.findAllByOrderByBirthDateAsc()).willReturn(List.of(employee1, employee2));

        // when -  action or the behaviour that we are going test
        List<EmployeeEntity> employeeList = employeeService.findAllByOrderByBirthDateAsc();

        assertArrayEquals(employeeList.toArray(), employeesSortList.toArray());
    }

    @DisplayName("JUnit test for findAllByOrderBySalaryDesc method in Service")
    @Test
    void givenEmployeesListSortedBySalary_whenFindAllByOrderBySalaryDesc(){
        // given - precondition or setup
        EmployeeEntity employee1 = EmployeeEntity.builder()
                .name("Brus")
                .lastName("Banner")
                .birthDate(new Date(1994, 2, 3))
                .salary(23)
                .build();

        EmployeeEntity employee2 = EmployeeEntity
                .builder()
                .name("Leonel")
                .lastName("Messi")
                .birthDate(new Date(1996, 2, 3))
                .salary(12)
                .build();

        List<EmployeeEntity> employeesSortList = new ArrayList<>(List.of(employee2, employee1));
        employeesSortList.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));

        given(employeeRepository.findAllByOrderBySalaryDesc()).willReturn(List.of(employee1, employee2));

        // when -  action or the behaviour that we are going test
        List<EmployeeEntity> employeeList = employeeService.findAllByOrderBySalaryDesc();

        assertArrayEquals(employeeList.toArray(), employeesSortList.toArray());
    }

}
