package com.wallet.manager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wallet.manager.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
	
	Customer findByEmail(String email);
     
}