package com.isoft.accounts.validator;

import com.isoft.accounts.dto.CustomerDTO;
import com.isoft.accounts.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator extends Validator<Customer, CustomerDTO> {
    public CustomerValidator() {
    }

    public void validate(CustomerDTO dto) {
        super.validate(dto);
    }
}
