package com.wallet.manager.repository;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wallet.manager.model.Customer;
import com.wallet.manager.model.Wallet;
import com.wallet.manager.util.HashingUtils;

@SpringBootTest
public class RepositoryTest {
	
	@Autowired
	protected CustomerRepository customerRepository;
	
	@Autowired
	protected WalletRepository walletRepository;
	
	public Customer createTestSingleCustomer() {
		Customer customer = new Customer();
		customer.setName("John");
		customer.setSurname("Winchester");
		customer.setEmail("jwinchester@test.com");
		customerRepository.save(customer);
		return customer;
	}
	
	public List<Customer> createTestCustomerList() {
		Customer customer1 = new Customer();
		customer1.setName("John");
		customer1.setSurname("Winchester");
		customer1.setEmail("jwinchester@test.com");
		customerRepository.save(customer1);
		
		Customer customer2 = new Customer();
		customer2.setName("Carla");
		customer2.setSurname("Perez");
		customer2.setEmail("cperez@test.com");
		customerRepository.save(customer2);
		
		return List.of(customer1, customer2);
	}
	
	protected Wallet createTestWalletOfCustomer(Customer customer, String walletName, BigDecimal balance) throws NoSuchAlgorithmException {
		Wallet wallet = new Wallet();
		wallet.setCustomerId(new ObjectId(customer.getId()));
		wallet.setHash(HashingUtils.generateRandomHash());
		wallet.setName("Tester wallet 1");
		wallet.setBalance(new BigDecimal("20.50"));
		walletRepository.save(wallet);
		
		return wallet;
	}
	

}
