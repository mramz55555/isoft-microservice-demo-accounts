package com.isoft.accounts.repository;

import com.isoft.accounts.entity.Customer;
import jakarta.validation.constraints.Pattern;

import java.util.Optional;

public interface CustomerRepository extends BaseRepository<Customer> {
    boolean existsByMobileNumber(@Pattern(regexp = "^[0-9]{11}$", message = "please provide a valid mobile number") String mobileNumber);

    Optional<Customer> findByMobileNumber(@Pattern(
            regexp = "^[0-9]{10}$",
            message = "please provide a valid mobile number"
    ) String mobileNumber);
}

