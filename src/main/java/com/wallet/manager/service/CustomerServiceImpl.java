package com.wallet.manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wallet.manager.model.Customer;
import com.wallet.manager.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}


	@Override
	public List<Customer> getAllRegisteredCustomers() {
		return this.customerRepository.findAll();
	}


	@Override
	public Customer getByCustomerByEmail(String email) {
		return this.customerRepository.findByEmail(email);
	}

}
