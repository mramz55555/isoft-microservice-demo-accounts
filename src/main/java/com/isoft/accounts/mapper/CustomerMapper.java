package com.isoft.accounts.mapper;

import com.isoft.accounts.dto.CustomerDTO;
import com.isoft.accounts.entity.Customer;

public class CustomerMapper extends Mapper<Customer, CustomerDTO> {
    private static final CustomerMapper accountMapper = new CustomerMapper();

    private CustomerMapper() {
    }

    public static CustomerMapper get() {
        return accountMapper;
    }

    public CustomerDTO entityToDTO(Customer entity) {
        return super.entityToDTO(entity, CustomerDTO.class);
    }

    public Customer dTOToEntity(CustomerDTO dto) {
        return super.dTOToEntity(dto, Customer.class);
    }
}

