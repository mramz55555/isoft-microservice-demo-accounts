package com.isoft.accounts.service;

import com.isoft.accounts.dto.AccountDTO;
import com.isoft.accounts.dto.CustomerDTO;
import com.isoft.accounts.dto.CustomerDetailsDTO;
import com.isoft.accounts.dto.client.CardsDto;
import com.isoft.accounts.dto.client.LoansDto;
import com.isoft.accounts.service.client.CardsClient;
import com.isoft.accounts.service.client.LoansClient;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class CustomerDetailsService {
    private final AccountService accountService;
    private final CustomerService customerService;
    private final CardsClient cardsClient;
    private final LoansClient loansClient;

    public CustomerDetailsDTO getCustomerDetails(@Pattern(regexp = "^[0-9]{11}$", message = "please provide a valid mobile number")
                                                 String mobileNumber) {

        CustomerDTO customerDTO = customerService.findByMobileNumber(mobileNumber);
        AccountDTO accountDTO = accountService.findByCustomerId(customerDTO.getId());
        CardsDto cardsDto = cardsClient.getCardsDetail(mobileNumber).getBody();
        LoansDto loansDto = loansClient.getLoanDetails(mobileNumber).getBody();

        CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO();

        customerDetailsDTO.getCardDTOList().add(cardsDto);
        customerDetailsDTO.getLoanDTOList().add(loansDto);
        customerDetailsDTO.getAccountDTOList().add(accountDTO);
        customerDetailsDTO.setCustomer(customerDTO);

        return customerDetailsDTO;
    }
}
