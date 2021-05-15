package com.wallet.manager.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wallet.manager.exception.BadRequestException;
import com.wallet.manager.exception.NotFoundException;
import com.wallet.manager.model.Customer;
import com.wallet.manager.model.Wallet;
import com.wallet.manager.repository.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService {
	
	private WalletRepository walletRepository;
	
	private CustomerService customerService;

	public WalletServiceImpl(WalletRepository walletRepository, CustomerService customerService) {
		super();
		this.walletRepository = walletRepository;
		this.customerService = customerService;
	}

	@Override
	public List<Wallet> getCustomerWallets(String email) throws NotFoundException {
			Customer customer = customerService.getByCustomerByEmail(email);
			if (customer == null) {
				throw new NotFoundException("Customer not found");
			}
			return this.walletRepository.findByCustomerId(new ObjectId(customer.getId()));
	}

	@Transactional
	@Override
	public void transferFunds(BigDecimal funds, String senderHash, String recipientHash) throws BadRequestException, NotFoundException {
		if (funds.compareTo(new BigDecimal("0.00")) < 0) {
			throw new BadRequestException("Funds to transfer must be equal or greater than zero");
		}
		
		Wallet sender = this.walletRepository.findByHash(senderHash);
		
		if (sender == null) {
			throw new NotFoundException("Sender wallet not found");
		}
		
		Wallet recipient = this.walletRepository.findByHash(recipientHash);
		
		if (recipient == null) {
			throw new NotFoundException("Recipient wallet not found");
		}
		
		if (sender.getHash().equals(recipient.getHash())) {
			throw new BadRequestException("A wallet can't transfer funds to itself!");
		}

		BigDecimal scaledFunds = funds;
		scaledFunds.setScale(2, RoundingMode.HALF_EVEN);
		sender.getBalance().setScale(2, RoundingMode.HALF_EVEN);
		recipient.getBalance().setScale(2, RoundingMode.HALF_EVEN);
		
		if (sender.getBalance().subtract(funds).compareTo(new BigDecimal(0)) >= 0) {
			recipient.setBalance(recipient.getBalance().add(funds));
			this.walletRepository.save(recipient);
			sender.setBalance(sender.getBalance().subtract(funds));
			this.walletRepository.save(sender);
		} else {
			throw new BadRequestException("Not enough funds");
		}
		
		
	}

}
