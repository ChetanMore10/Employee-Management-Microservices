package com.address_service.controlle;

import com.address_service.dto.AddressRequest;
import com.address_service.dto.AddressResponse;
import com.address_service.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponse> addAddress (@Valid @RequestBody AddressRequest request){
        AddressResponse response = addressService.addAddress(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAllAddress(){
        List<AddressResponse> responses = addressService.getAllAddress();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getById(@PathVariable Long id){
        AddressResponse response = addressService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long id, @RequestBody AddressRequest address){
        AddressResponse response = addressService.updateAddress(id, address);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return new ResponseEntity<>("Delete Address Successfully...!",HttpStatus.OK);
    }
}
