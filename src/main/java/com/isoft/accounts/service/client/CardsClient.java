package com.isoft.accounts.service.client;

import com.isoft.accounts.dto.client.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsClient {

    @GetMapping("/api/v1/fetch-card-detail")
    ResponseEntity<CardsDto> getCardsDetail(@RequestParam String mobileNumber);
}
