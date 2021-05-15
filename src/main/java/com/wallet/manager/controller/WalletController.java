package com.wallet.manager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.manager.dto.TransferDTO;
import com.wallet.manager.dto.WalletDTO;
import com.wallet.manager.exception.BadRequestException;
import com.wallet.manager.exception.NotFoundException;
import com.wallet.manager.mapper.WalletToWalletDTO;
import com.wallet.manager.service.WalletService;

@RestController
public class WalletController implements WalletApi {

	private WalletService walletService;
	
	
	public WalletController(WalletService walletService) {
		super();
		this.walletService = walletService;
	}


	@Override
	public ResponseEntity<List<WalletDTO>> getCustomerWallets(String email) throws NotFoundException {
		List<WalletDTO> walletList = WalletToWalletDTO.INSTANCE.toWalletDTOList(walletService.getCustomerWallets(email));
		return ResponseEntity.ok(walletList);
	}


	@Override
	public ResponseEntity<Void> transferFunds(TransferDTO transferRequest) throws BadRequestException, NotFoundException {
		this.walletService.transferFunds(transferRequest.getFunds(), transferRequest.getSenderHash(), transferRequest.getRecipientHash());
		return new ResponseEntity<Void>( HttpStatus.OK );
	}

}
