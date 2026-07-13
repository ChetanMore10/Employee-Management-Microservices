package com.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {

    private Long id;

    private String employeeCode;

    private String empName;

    private String email;

    private Long phoneNo;

    private String department;

    private String designation;

    private BigDecimal salary;

    private LocalDate hireDate;
}