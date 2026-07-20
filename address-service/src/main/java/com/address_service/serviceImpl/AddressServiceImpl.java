package com.address_service.serviceImpl;

import com.address_service.dto.AddressRequest;
import com.address_service.dto.AddressResponse;
import com.address_service.entity.Address;
import com.address_service.exception.DuplicateResourceException;
import com.address_service.exception.ResourceNotFoundException;
import com.address_service.repository.AddressRepo;
import com.address_service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepo addressRepo;

    private Address mapToEntity(AddressRequest request){
        Address address = new Address();

        address.setEmployeeId(request.getEmployeeId());
        address.setAddressLine1(request.getAddressLine1());
        address.setAddressLine2(request.getAddressLine2());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setCountry(request.getCountry());
        address.setPinCode(request.getPinCode());

        return address;
    }

    private AddressResponse mapToResponse(Address address){
        AddressResponse response = new AddressResponse();

        response.setId(address.getId());
        response.setEmployeeId(address.getEmployeeId());
        response.setAddressLine1(address.getAddressLine1());
        response.setAddressLine2(address.getAddressLine2());
        response.setCity(address.getCity());
        response.setState(address.getState());
        response.setCountry(address.getCountry());
        response.setPinCode(address.getPinCode());
        return response;
    }

    @Override
    public AddressResponse addAddress(AddressRequest address) {
        if (addressRepo.existsByEmployeeId(address.getEmployeeId())) {
            throw new DuplicateResourceException(
                    "Address already exists for Employee Id : " + address.getEmployeeId());
        }
        Address address1 = mapToEntity(address);
        Address saveAddress = addressRepo.save(address1);
        return mapToResponse(saveAddress);
    }

    @Override
    public List<AddressResponse> getAllAddress() {
        List<Address> addresses = addressRepo.findAll();
        if (addresses.isEmpty()){
            throw new ResourceNotFoundException("No Address Found...!");
        }
        return addresses.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponse getById(Long id) {
        Address address = addressRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Address not found with id :"+id));
        return mapToResponse(address);
    }

    @Override
    public AddressResponse updateAddress(Long id, AddressRequest address) {
        Address updateAddress = addressRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Address not found with id : " + id));

        updateAddress.setAddressLine1(address.getAddressLine1());
        updateAddress.setAddressLine2(address.getAddressLine2());
        updateAddress.setCity(address.getCity());
        updateAddress.setState(address.getState());
        updateAddress.setCountry(address.getCountry());
        updateAddress.setPinCode(address.getPinCode());

        Address savedAddress = addressRepo.save(updateAddress);
        return mapToResponse(savedAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = addressRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Address not found with id :"+id));
        addressRepo.delete(address);
    }
}