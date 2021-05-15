package com.wallet.manager.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import com.wallet.manager.model.Customer;
import com.wallet.manager.model.Wallet;


public class WalletRepositoryTest extends RepositoryTest{

	@Transactional
	@Test
	public void testFindByCustomer_Success() throws NoSuchAlgorithmException {
		Customer customer = createTestCustomerList().get(0);

		createTestWalletOfCustomer(customer, "Test wallet 1", new BigDecimal("10.00"));
		createTestWalletOfCustomer(customer, "Test wallet 2", new BigDecimal("10.00"));

		List<Wallet> walletList = walletRepository.findByCustomerId(new ObjectId(customer.getId()));
		assertThat(walletList).isNotNull().hasSizeGreaterThan(0);
	}
	
	@Transactional
	@Test
	public void testFindByHash_Success() throws NoSuchAlgorithmException {
		Customer customer = createTestCustomerList().get(0);
		Wallet wallet = createTestWalletOfCustomer(customer, "Test wallet 1", new BigDecimal("10.00"));
		assertThat(walletRepository.findByHash(wallet.getHash())).isNotNull().hasFieldOrPropertyWithValue("id", wallet.getId());
	}
}
