package com.address_service.repository;

import com.address_service.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
    boolean existsByEmployeeId(Long employeeId);
    Optional<Address> findByEmployeeId(Long employeeId);
}
