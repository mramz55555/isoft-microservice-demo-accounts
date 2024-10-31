package com.isoft.accounts.controller;

import com.isoft.accounts.dto.CustomerDetailsDTO;
import com.isoft.accounts.dto.ErrorResponseDTO;
import com.isoft.accounts.service.CustomerDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Customer's rest api")
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerDetailsService customerDetailsService;

    @Operation(summary = "a GET method for fetching customer related details", description = "GET customer details")
    @ApiResponses({@ApiResponse(
            responseCode = "201",
            description = "returns 201 if its unique by mobile number"
    ), @ApiResponse(
            responseCode = "500",
            content = {@Content(schema = @Schema(implementation = ErrorResponseDTO.class))},
            description = "returns 500 if its duplicate"
    )})
    @GetMapping("/get-customer-details")
    public ResponseEntity<CustomerDetailsDTO> getCustomerDetails(@RequestParam String mobileNumber,
                                                                 @RequestHeader(name = "correlation-id") String correlationId) {
        return ResponseEntity.ok(customerDetailsService.getCustomerDetails(mobileNumber));

    }
}
