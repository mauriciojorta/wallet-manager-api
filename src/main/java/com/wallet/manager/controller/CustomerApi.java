package com.wallet.manager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.manager.controller.constant.Constants;
import com.wallet.manager.dto.CustomerDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RequestMapping(value = Constants.API_CUSTOMERS)
public interface CustomerApi {
	
    @Operation(description = "Get all employees")
    @ApiResponses( value = {
            @ApiResponse(responseCode = Constants.OK_CODE_RESPONSE, description = Constants.OK_RESPONSE),
    })
    @CrossOrigin(origins = Constants.ALLOWED_CORS_URL)
    @GetMapping
    ResponseEntity<List<CustomerDTO>> getRegisteredCustomers();

}
