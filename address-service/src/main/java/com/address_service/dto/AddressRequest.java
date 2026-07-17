package com.address_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressRequest {

    @NotNull(message = "Employee Id is required")
    private Long employeeId;

    @NotNull(message = "Address Line 1 is required")
    private String addressLine1;

    private String addressLine2;

    @NotNull(message = "City is required")
    private String city;

    @NotNull(message = "State is required")
    private String state;

    @NotNull(message = "Country is required")
    private String country;

    @NotNull(message = "Pin Code id required")
    private int pinCode;
}
