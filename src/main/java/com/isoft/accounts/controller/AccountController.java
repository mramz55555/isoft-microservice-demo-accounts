package com.isoft.accounts.controller;

import com.isoft.accounts.dto.*;
import com.isoft.accounts.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"/api/v1"}, produces = {"application/json"})
@Tag(name = "Accounts rest api")
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;
    private final DataBaseConfigDTO dataBaseConfigDTO;

    public AccountController(AccountService accountService,
                             DataBaseConfigDTO dataBaseConfigDTO) {
        this.accountService = accountService;
        this.dataBaseConfigDTO = dataBaseConfigDTO;
    }

    @PostMapping({"/create"})
    @Operation(
            summary = "a post method for creating account based on its customer",
            description = "post account method",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "customer object v2"
            )
    )
    @ApiResponses({@ApiResponse(
            responseCode = "201",
            description = "returns 201 if its unique by mobile number"
    ), @ApiResponse(
            responseCode = "500",
            content = {@Content(
                    schema = @Schema(
                            implementation = ErrorResponseDTO.class
                    )
            )},
            description = "returns 500 if its duplicate"
    )})
    public ResponseEntity<ResponseDTO<AccountDTO>> create(@RequestBody CustomerDTO customerDTO,
                                                          @RequestHeader(name = "correlation-id") String correlationId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO<>(this.accountService.save(customerDTO), "Account created"));
    }

    @PutMapping({"/update"})
    @Operation(
            summary = "a put method for updating account based on its customer"
    )
    @ApiResponses({@ApiResponse(
            responseCode = "200",
            description = "returns 200 if its exists by mobile number"
    ), @ApiResponse(
            responseCode = "500",
            content = {@Content(
                    schema = @Schema(
                            implementation = ErrorResponseDTO.class
                    )
            )},
            description = "returns 500 if its does not exist"
    )})
    public ResponseEntity<ResponseDTO<CustomerDTO>> update(@RequestBody CustomerDTO customerDTO,
                                                           @RequestHeader(name = "correlation-id") String correlationId) {
        logger.info("correlation-id received : {}", correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(this.accountService.update(customerDTO), "Account updated"));
    }

    @GetMapping({"/find-by-mobile-number/{mobile_number}"})
    @Operation(
            summary = "a get method for fetching account based on its mobile number",
            description = "get account method"
    )
    @ApiResponses({@ApiResponse(
            responseCode = "302",
            description = "returns 302 if its found by mobile number"
    ), @ApiResponse(
            responseCode = "500",
            content = {@Content(
                    schema = @Schema(
                            implementation = ErrorResponseDTO.class
                    )
            )},
            description = "returns 500 if it does not exist"
    )})
    public ResponseEntity<ResponseDTO<CustomerDTO>> findCustomerByMobileNumber(@PathVariable("mobile_number") String mobileNumber,
                                                                               @RequestHeader(name = "correlation-id") String correlationId) {
        logger.info("correlation-id received : {}", correlationId);
        return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseDTO<>(this.accountService.findCustomerByMobileNumber(mobileNumber), "found"));
    }

    @DeleteMapping({"/delete-by-mobile-num/{mobileNumber}"})
    @Operation(
            summary = "a delete method for deleting account based on its mobile number",
            description = "delete account method",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "delete by mobile number's request body",
                    required = true
            )
    )
    @ApiResponses({@ApiResponse(
            responseCode = "200",
            description = "returns 200 if its found and delete by mobile number"
    ), @ApiResponse(
            responseCode = "500",
            content = {@Content(
                    schema = @Schema(
                            implementation = ErrorResponseDTO.class
                    )
            )}
    )})
    public ResponseEntity<String> delete(@PathVariable String mobileNumber, @RequestHeader(name = "correlation-id") String correlationId) {
        this.accountService.deleteByMobileNumber(mobileNumber);
        return ResponseEntity.ok().body("ok");
    }

    @GetMapping({"/props"})
    public ResponseEntity<DataBaseConfigDTO> getProps(@RequestHeader(name = "correlation-id") String correlationId) {
        logger.info("correlation id received : {}", correlationId);
        return ResponseEntity.ok(this.dataBaseConfigDTO);
    }

    @GetMapping({"/props2"})
    public ResponseEntity<DataBaseConfigDTO> getProps2(@RequestHeader(name = "correlation-id") String correlationId) {
        return ResponseEntity.ok(this.dataBaseConfigDTO);
    }
}
