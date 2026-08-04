package com.employee_service.feign;

import com.employee_service.dto.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ADDRESS-SERVICE")
public interface AddressClient {

    @GetMapping("/api/address/employee/{employeeId}")
    AddressResponse getAddressByEmployeeId(@PathVariable("employeeId") Long employeeId);

}