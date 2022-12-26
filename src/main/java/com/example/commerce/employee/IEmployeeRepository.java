package com.example.commerce.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {
    List<EmployeeEntity> findAllByOrderByBirthDateAsc();

    List<EmployeeEntity> findAllByOrderBySalaryDesc();
}
