package com.wallet.manager.service;

import java.util.List;

import com.wallet.manager.model.Customer;

public interface CustomerService {
	
	public List<Customer> getAllRegisteredCustomers();
	public Customer getByCustomerByEmail(String email);
}
