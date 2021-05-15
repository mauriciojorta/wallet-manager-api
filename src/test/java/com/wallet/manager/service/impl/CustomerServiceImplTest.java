package com.wallet.manager.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.wallet.manager.model.Customer;
import com.wallet.manager.repository.CustomerRepository;
import com.wallet.manager.service.CustomerServiceImpl;

public class CustomerServiceImplTest {

	@Mock
	private CustomerRepository repository;

	@InjectMocks
	private CustomerServiceImpl service;

	@BeforeEach
	void initMockito() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllRegisteredCustomers_Success() {
		Customer customer1 = new Customer();
		customer1.setId("1");
		customer1.setName("John");
		customer1.setSurname("Winchester");
		customer1.setEmail("jwinchester@test.com");

		Customer customer2 = new Customer();
		customer2.setId("2");
		customer2.setName("Carla");
		customer2.setSurname("Perez");
		customer2.setEmail("cperez@test.com");

		List<Customer> customerList = Arrays.asList(customer1, customer2);
		Mockito.when(repository.findAll()).thenReturn(customerList);

		RecursiveComparisonConfiguration comparisonConfig = RecursiveComparisonConfiguration.builder().build();
		assertThat(service.getAllRegisteredCustomers()).isNotNull().hasSize(customerList.size()).usingRecursiveFieldByFieldElementComparator(comparisonConfig).contains(customer1, customer2);

		Mockito.verify(repository, Mockito.times(1)).findAll();

	}
	
	@Test
	void testGetCustomerByEmail_Success() {
		Customer customer = new Customer();
		customer.setId("1");
		customer.setName("John");
		customer.setSurname("Winchester");
		customer.setEmail("jwinchester@test.com");
		
		Mockito.when(repository.findByEmail(customer.getEmail())).thenReturn(customer);
		assertThat(service.getByCustomerByEmail(customer.getEmail())).isNotNull().usingRecursiveComparison().isEqualTo(customer);
		Mockito.verify(repository, Mockito.times(1)).findByEmail(customer.getEmail());

	}

}
