package com.address_service.dto;

import lombok.Data;

@Data
public class AddressResponse {

    private Long id;

    private Long employeeId;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private Integer pinCode;
}
