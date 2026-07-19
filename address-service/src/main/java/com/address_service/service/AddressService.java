package com.address_service.service;

import com.address_service.dto.AddressRequest;
import com.address_service.dto.AddressResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    AddressResponse addAddress(AddressRequest address);
    List<AddressResponse> getAllAddress();
    AddressResponse getById(Long id);
    AddressResponse updateAddress(Long id, AddressRequest address);
    void deleteAddress(Long id);
}
