package com.wallet.manager.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.wallet.manager.exception.BadRequestException;
import com.wallet.manager.exception.NotFoundException;
import com.wallet.manager.model.Customer;
import com.wallet.manager.model.Wallet;
import com.wallet.manager.repository.WalletRepository;
import com.wallet.manager.service.CustomerService;
import com.wallet.manager.service.WalletServiceImpl;

public class WalletServiceImplTest {
	
	@Mock
	private WalletRepository repository;
	
	@Mock
	private CustomerService customerService;
	
	@InjectMocks
	private WalletServiceImpl service;
	
	@BeforeEach
	void initMockito() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testGetCustomerWallets_Success() throws NotFoundException {		
		Customer customer = new Customer();
		customer.setId(ObjectId.get().toHexString());
		customer.setEmail("test@email.com");
		
		ObjectId customerId = new ObjectId(customer.getId());
		
		Wallet wallet1 = new Wallet();
		wallet1.setId("1");
		wallet1.setCustomerId(customerId);
		wallet1.setHash("65ed0deb00146fb9df30c1b0eab471fb95df37fefa3d7a034d785d1892a4ce40");
		wallet1.setName("Tester wallet 1");
		wallet1.setBalance(new BigDecimal("2.50"));
		
		Wallet wallet2 = new Wallet();
		wallet2.setId("2");
		wallet2.setCustomerId(customerId);
		wallet2.setHash("077b08788bbf0ece31eef75478f8219f80ae72a9233d83ae722e23731108318e");
		wallet2.setName("Tester wallet 2");
		wallet2.setBalance(new BigDecimal("5.00"));
		
		List<Wallet> walletList = List.of(wallet1, wallet2);
		Mockito.when(customerService.getByCustomerByEmail(customer.getEmail())).thenReturn(customer);
		Mockito.when(repository.findByCustomerId(customerId)).thenReturn(walletList);
		RecursiveComparisonConfiguration comparisonConfig = RecursiveComparisonConfiguration.builder().build();
		assertThat(service.getCustomerWallets("test@email.com")).isNotNull().hasSize(walletList.size()).usingRecursiveFieldByFieldElementComparator(comparisonConfig).contains(wallet1, wallet2);

		Mockito.verify(customerService, Mockito.times(1)).getByCustomerByEmail(customer.getEmail());
		Mockito.verify(repository, Mockito.times(1)).findByCustomerId(customerId);

	}
	
	@Test
	void testGetCustomerWallets_NotFoundException() throws NotFoundException {		
		Customer customer = new Customer();
		customer.setId(ObjectId.get().toHexString());
		customer.setEmail("test@email.com");
				
		Mockito.when(customerService.getByCustomerByEmail(customer.getEmail())).thenReturn(null);
		
		assertThrows(NotFoundException.class, () -> {
			service.getCustomerWallets(customer.getEmail());
			}
		);	
		Mockito.verify(customerService, Mockito.times(1)).getByCustomerByEmail(customer.getEmail());

	}
	
	@Test
	void testTransferFunds_Success() throws BadRequestException, NotFoundException {
		String hash1 = "65ed0deb00146fb9df30c1b0eab471fb95df37fefa3d7a034d785d1892a4ce40";
		String hash2 = "077b08788bbf0ece31eef75478f8219f80ae72a9233d83ae722e23731108318e";
		
		ObjectId customerId1 = new ObjectId("609abc30188d7b0279203187");
		ObjectId customerId2 = new ObjectId("609abd8f188d7b0279203188");
		
		Wallet wallet1 = new Wallet();
		wallet1.setId("1");
		wallet1.setCustomerId(customerId1);
		wallet1.setHash(hash1);
		wallet1.setName("Tester wallet 1");
		wallet1.setBalance(new BigDecimal("10.50"));
		
		Wallet wallet2 = new Wallet();
		wallet2.setId("2");
		wallet2.setCustomerId(customerId2);
		wallet2.setHash(hash2);
		wallet2.setName("Tester wallet 2");
		wallet2.setBalance(new BigDecimal("5.00"));
		
		Wallet walletUpdated1 = new Wallet();
		walletUpdated1.setId("1");
		walletUpdated1.setCustomerId(customerId1);
		walletUpdated1.setHash(hash1);
		walletUpdated1.setName("Tester wallet 1");
		walletUpdated1.setBalance(new BigDecimal("0.50"));
		
		Wallet walletUpdated2 = new Wallet();
		walletUpdated2.setId("2");
		walletUpdated2.setCustomerId(customerId2);
		walletUpdated2.setHash(hash2);
		walletUpdated2.setName("Tester wallet 2");
		walletUpdated2.setBalance(new BigDecimal("15.00"));
		
		Mockito.when(repository.findByHash(hash1)).thenReturn(wallet1);
		Mockito.when(repository.findByHash(hash2)).thenReturn(wallet2);
		Mockito.when(repository.save(Mockito.any(Wallet.class))).thenReturn(Mockito.mock(Wallet.class));
		
		RecursiveComparisonConfiguration comparisonConfig = RecursiveComparisonConfiguration.builder().build();
		service.transferFunds(new BigDecimal(10.00), hash1, hash2);
		
		assertThat(List.of(wallet1, wallet2)).usingRecursiveFieldByFieldElementComparator(comparisonConfig).contains(walletUpdated1, walletUpdated2);
		
		Mockito.verify(repository, Mockito.times(2)).findByHash(Mockito.anyString());
		Mockito.verify(repository, Mockito.times(2)).save(Mockito.any(Wallet.class));

	}
	
	@Test
	void testTransferFunds_NotEnoughFundsException() throws BadRequestException {
		String hash1 = "65ed0deb00146fb9df30c1b0eab471fb95df37fefa3d7a034d785d1892a4ce40";
		String hash2 = "077b08788bbf0ece31eef75478f8219f80ae72a9233d83ae722e23731108318e";
		
		ObjectId customerId1 = new ObjectId("609abc30188d7b0279203187");
		ObjectId customerId2 = new ObjectId("609abd8f188d7b0279203188");
		
		Wallet wallet1 = new Wallet();
		wallet1.setId("1");
		wallet1.setCustomerId(customerId1);
		wallet1.setHash(hash1);
		wallet1.setName("Tester wallet 1");
		wallet1.setBalance(new BigDecimal("0.50"));
		
		Wallet wallet2 = new Wallet();
		wallet2.setId("2");
		wallet2.setCustomerId(customerId2);
		wallet2.setHash(hash2);
		wallet2.setName("Tester wallet 2");
		wallet2.setBalance(new BigDecimal("5.00"));
		
		Mockito.when(repository.findByHash(hash1)).thenReturn(wallet1);
		Mockito.when(repository.findByHash(hash2)).thenReturn(wallet2);
		
		assertThrows(BadRequestException.class, () -> {
			service.transferFunds(new BigDecimal(10.00), hash1, hash2);
			}
		);	
		
		Mockito.verify(repository, Mockito.times(2)).findByHash(Mockito.anyString());

	}
	
	@Test
	void testTransferFunds_InvalidFundsException() throws BadRequestException {
		String hash1 = "65ed0deb00146fb9df30c1b0eab471fb95df37fefa3d7a034d785d1892a4ce40";
		String hash2 = "077b08788bbf0ece31eef75478f8219f80ae72a9233d83ae722e23731108318e";
		
		ObjectId customerId1 = new ObjectId("609abc30188d7b0279203187");
		ObjectId customerId2 = new ObjectId("609abd8f188d7b0279203188");
		
		Wallet wallet1 = new Wallet();
		wallet1.setId("1");
		wallet1.setCustomerId(customerId1);
		wallet1.setHash(hash1);
		wallet1.setName("Tester wallet 1");
		wallet1.setBalance(new BigDecimal("0.50"));
		
		Wallet wallet2 = new Wallet();
		wallet2.setId("2");
		wallet2.setCustomerId(customerId2);
		wallet2.setHash(hash2);
		wallet2.setName("Tester wallet 2");
		wallet2.setBalance(new BigDecimal("5.00"));
		
		assertThrows(BadRequestException.class, () -> {
			service.transferFunds(new BigDecimal(-1.00), hash1, hash2);
			}
		);	
		
	}
	
	@Test
	void testTransferFunds_SenderNotFoundException() throws BadRequestException {
		String hash1 = "65ed0deb00146fb9df30c1b0eab471fb95df37fefa3d7a034d785d1892a4ce40";
		String hash2 = "077b08788bbf0ece31eef75478f8219f80ae72a9233d83ae722e23731108318e";
		
		ObjectId customerId1 = new ObjectId("609abc30188d7b0279203187");
		ObjectId customerId2 = new ObjectId("609abd8f188d7b0279203188");
		
		Wallet wallet1 = new Wallet();
		wallet1.setId("1");
		wallet1.setCustomerId(customerId1);
		wallet1.setHash(hash1);
		wallet1.setName("Tester wallet 1");
		wallet1.setBalance(new BigDecimal("10.50"));
		
		Wallet wallet2 = new Wallet();
		wallet2.setId("2");
		wallet2.setCustomerId(customerId2);
		wallet2.setHash(hash2);
		wallet2.setName("Tester wallet 2");
		wallet2.setBalance(new BigDecimal("5.00"));
				
		assertThrows(NotFoundException.class, () -> {
			service.transferFunds(new BigDecimal(5.00), hash1, hash2);
			}
		);	
		
		Mockito.verify(repository, Mockito.times(1)).findByHash(Mockito.anyString());
		
	}
	
	@Test
	void testTransferFunds_RecipientNotFoundException() throws BadRequestException {
		String hash1 = "65ed0deb00146fb9df30c1b0eab471fb95df37fefa3d7a034d785d1892a4ce40";
		String hash2 = "077b08788bbf0ece31eef75478f8219f80ae72a9233d83ae722e23731108318e";
		
		ObjectId customerId1 = new ObjectId("609abc30188d7b0279203187");
		ObjectId customerId2 = new ObjectId("609abd8f188d7b0279203188");
		
		Wallet wallet1 = new Wallet();
		wallet1.setId("1");
		wallet1.setCustomerId(customerId1);
		wallet1.setHash(hash1);
		wallet1.setName("Tester wallet 1");
		wallet1.setBalance(new BigDecimal("10.50"));
		
		Wallet wallet2 = new Wallet();
		wallet2.setId("2");
		wallet2.setCustomerId(customerId2);
		wallet2.setHash(hash2);
		wallet2.setName("Tester wallet 2");
		wallet2.setBalance(new BigDecimal("5.00"));
		
		Mockito.when(repository.findByHash(hash1)).thenReturn(wallet1);
		
		assertThrows(NotFoundException.class, () -> {
			service.transferFunds(new BigDecimal(5.00), hash1, hash2);
			}
		);	
		
		Mockito.verify(repository, Mockito.times(2)).findByHash(Mockito.anyString());

		
	}
	
	@Test
	void testTransferFunds_DuplicateHashesException() throws BadRequestException {
		String hash1 = "65ed0deb00146fb9df30c1b0eab471fb95df37fefa3d7a034d785d1892a4ce40";
		String hash2 = "65ed0deb00146fb9df30c1b0eab471fb95df37fefa3d7a034d785d1892a4ce40";
		
		ObjectId customerId1 = new ObjectId("609abc30188d7b0279203187");
		ObjectId customerId2 = new ObjectId("609abd8f188d7b0279203188");
		
		Wallet wallet1 = new Wallet();
		wallet1.setId("1");
		wallet1.setCustomerId(customerId1);
		wallet1.setHash(hash1);
		wallet1.setName("Tester wallet 1");
		wallet1.setBalance(new BigDecimal("10.50"));
		
		Wallet wallet2 = new Wallet();
		wallet2.setId("2");
		wallet2.setCustomerId(customerId2);
		wallet2.setHash(hash2);
		wallet2.setName("Tester wallet 2");
		wallet2.setBalance(new BigDecimal("5.00"));
		
		Mockito.when(repository.findByHash(hash1)).thenReturn(wallet1);
		Mockito.when(repository.findByHash(hash2)).thenReturn(wallet2);
		
		assertThrows(BadRequestException.class, () -> {
			service.transferFunds(new BigDecimal(5.00), hash1, hash2);
			}
		);	
		
		Mockito.verify(repository, Mockito.times(2)).findByHash(Mockito.anyString());

		
	}
	

}
