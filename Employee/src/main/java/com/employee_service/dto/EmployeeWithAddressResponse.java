package com.employee_service.dto;

import lombok.Data;

@Data
public class EmployeeWithAddressResponse {

    private EmployeeResponse employee;
    private AddressResponse address;
}
