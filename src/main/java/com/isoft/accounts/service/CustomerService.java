package com.isoft.accounts.service;

import com.isoft.accounts.dto.CustomerDTO;
import com.isoft.accounts.entity.Customer;
import com.isoft.accounts.exception.EntityNotFoundException;
import com.isoft.accounts.mapper.CustomerMapper;
import com.isoft.accounts.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends AbstractService<Customer, CustomerDTO, CustomerRepository> {
    public CustomerService(CustomerRepository repository, EntityManager entityManager) {
        super(repository, entityManager, CustomerMapper.get());
    }

    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer entity = CustomerMapper.get().dTOToEntity(customerDTO);
        Customer saved = this.getRepository().save(entity);
        return CustomerMapper.get().entityToDTO(saved);
    }

    public CustomerDTO findByMobileNumber(String mobileNumber) {
        return CustomerMapper.get().entityToDTO(getRepository().findByMobileNumber(mobileNumber).orElseThrow(() -> new EntityNotFoundException("customer", "mobile number", mobileNumber)));
    }

    public Customer findById(Long id) {
        return (Customer) super.findById(id, Customer.class);
    }

    public void deleteById(Long id) {
        this.getRepository().deleteById(id);
    }
}
