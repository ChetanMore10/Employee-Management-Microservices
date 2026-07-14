package com.employee_service.repository;

import com.employee_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    boolean existsByEmail(String email);
    List<Employee> findByEmpNameContainingIgnoreCase(String empName);
    Optional<Employee> findByEmployeeCode(String employeeCode);
}
