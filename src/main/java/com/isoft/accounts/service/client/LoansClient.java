package com.isoft.accounts.service.client;

import com.isoft.accounts.dto.client.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansClient {

    @GetMapping(value = "/api/v1/fetch")
    ResponseEntity<LoansDto> getLoanDetails(@RequestParam String mobileNumber);
}
