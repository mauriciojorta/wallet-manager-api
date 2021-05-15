package com.wallet.manager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wallet.manager.controller.constant.Constants;
import com.wallet.manager.dto.TransferDTO;
import com.wallet.manager.dto.WalletDTO;
import com.wallet.manager.exception.BadRequestException;
import com.wallet.manager.exception.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RequestMapping(value = Constants.API_WALLETS)
public interface WalletApi {
	
    @Operation(description = "Get all wallets of a customer")
    @ApiResponses( value = {
            @ApiResponse(responseCode = Constants.OK_CODE_RESPONSE, description = Constants.OK_RESPONSE),
            @ApiResponse(responseCode = Constants.NOT_FOUND_CODE_RESPONSE, description = Constants.NOT_FOUND_RESPONSE)
    })
    @CrossOrigin(origins = Constants.ALLOWED_CORS_URL)
    @GetMapping
    ResponseEntity<List<WalletDTO>> getCustomerWallets(@RequestParam(required= true) String email) throws NotFoundException;

    @Operation(description = "Transfer funds between two wallets")
    @ApiResponses( value = {
            @ApiResponse(responseCode = Constants.OK_CODE_RESPONSE, description = Constants.OK_RESPONSE),
            @ApiResponse(responseCode = Constants.NOT_FOUND_CODE_RESPONSE, description = Constants.NOT_FOUND_RESPONSE),
            @ApiResponse(responseCode = Constants.BAD_REQUEST_CODE_RESPONSE, description = Constants.BAD_REQUEST_RESPONSE)
    })
    @CrossOrigin(origins = Constants.ALLOWED_CORS_URL)
    @PutMapping(Constants.API_WALLETS_TRANSFER)
    ResponseEntity<Void> transferFunds(@RequestBody @Valid TransferDTO transferRequest) throws BadRequestException, NotFoundException;
}
