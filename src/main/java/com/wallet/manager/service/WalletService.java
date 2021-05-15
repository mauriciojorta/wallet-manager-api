package com.wallet.manager.service;

import java.math.BigDecimal;
import java.util.List;

import com.wallet.manager.exception.BadRequestException;
import com.wallet.manager.exception.NotFoundException;
import com.wallet.manager.model.Wallet;

public interface WalletService {
	
	List<Wallet> getCustomerWallets(String email) throws NotFoundException;
	
	void transferFunds(BigDecimal funds, String senderHash, String recipientHash) throws BadRequestException, NotFoundException;

}
