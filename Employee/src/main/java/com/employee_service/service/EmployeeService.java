package com.employee_service.service;

import com.employee_service.dto.EmployeeRequest;
import com.employee_service.dto.EmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    EmployeeResponse addEmployee(EmployeeRequest request);
    Page<EmployeeResponse> getEmployeesPage(int page, int size);
    List<EmployeeResponse> getAll();
    EmployeeResponse getByID(Long id);
    EmployeeResponse getByEmployeeCode(String employeeCode);
    List<EmployeeResponse> getByEmpName(String empName);
    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);
    void deleteEmployee(Long id);
}