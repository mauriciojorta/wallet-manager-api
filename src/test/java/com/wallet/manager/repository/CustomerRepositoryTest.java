package com.wallet.manager.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import com.wallet.manager.model.Customer;

public class CustomerRepositoryTest extends RepositoryTest {
	
	@Transactional
	@Test
	public void testFindAll_Success() {
		createTestCustomerList();
		List<Customer> customerList = customerRepository.findAll();
		assertThat(customerList).isNotNull().hasSizeGreaterThan(0);
	}
	

}
