package com.isoft.accounts.service;

import com.isoft.accounts.dto.AccountDTO;
import com.isoft.accounts.dto.CustomerDTO;
import com.isoft.accounts.entity.Account;
import com.isoft.accounts.entity.Customer;
import com.isoft.accounts.exception.EntityAlreadyExistsException;
import com.isoft.accounts.exception.EntityNotFoundException;
import com.isoft.accounts.mapper.AccountMapper;
import com.isoft.accounts.mapper.CustomerMapper;
import com.isoft.accounts.repository.AccountRepository;
import com.isoft.accounts.repository.CustomerRepository;
import com.isoft.accounts.validator.CustomerValidator;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Random;

@Service
@Validated
public class AccountService extends AbstractService<Account, AccountDTO, AccountRepository> {
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final CustomerValidator customerValidator;
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository repository,
                          CustomerRepository customerRepository,
                          CustomerService customerService,
                          EntityManager entityManager,
                          AccountRepository accountRepository) {
        super(repository, entityManager, AccountMapper.get());
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.customerValidator = new CustomerValidator();
        this.accountRepository = accountRepository;
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public AccountDTO save(@Valid CustomerDTO customerDTO) {
        this.customerValidator.validate(customerDTO);
        if (this.customerRepository.existsByMobileNumber(customerDTO.getMobileNumber())) {
            throw new EntityAlreadyExistsException("customer", "mobile number", customerDTO.getMobileNumber());
        } else {
            CustomerDTO savedCustomer = this.customerService.save(customerDTO);
            Account account = this.createNewAccount(CustomerMapper.get().dTOToEntity(savedCustomer));
            return AccountMapper.get().entityToDTO((Account) ((AccountRepository) this.getRepository()).save(account));
        }
    }

    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getId());
        account.setId((long) (new Random()).nextInt(1000) + 1000L);
        account.setAccountType("saving");
        account.setBranchAddress("number 78 , shan street washington US");
        return account;
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public CustomerDTO findCustomerByMobileNumber(@Pattern(
            regexp = "^[0-9]{10}$",
            message = "please provide a valid mobile number"
    ) String mobileNumber) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setMobileNumber(mobileNumber);
        Customer customer;
        try {
            customer = (Customer) this.customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> {
                throw new EntityNotFoundException("customer", "mobile number", mobileNumber);
            });
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        Account account = this.getRepository().findByCustomerId(customer.getId()).get();
        CustomerDTO customerDTO1 = CustomerMapper.get().entityToDTO(customer);
        customerDTO1.getAccountDTOList().add(AccountMapper.get().entityToDTO(account));
        return customerDTO1;
    }

    public AccountDTO findByCustomerId(Long customerId) {
        return AccountMapper.get().entityToDTO(accountRepository.findByCustomerId(customerId).orElseThrow(() -> new EntityNotFoundException("customer", "id", customerId + "")));
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public CustomerDTO update(@Valid CustomerDTO customerDTO) {
        Customer savedCustomer = (Customer) this.customerService.update(customerDTO, Customer.class);
        return CustomerMapper.get().entityToDTO(savedCustomer);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public void deleteByMobileNumber(@Pattern(
            regexp = "^[0-9]{10}$",
            message = "please provide a valid mobile number"
    ) String mobileNumber) {
        Customer customer;
        try {
            customer = (Customer) this.customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> {
                throw new EntityNotFoundException("customer", "mobile number", "" + mobileNumber);
            });
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        this.accountRepository.deleteByCustomerId(customer.getId());
        this.customerService.deleteById(customer.getId());
    }
}
