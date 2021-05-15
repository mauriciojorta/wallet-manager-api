package com.wallet.manager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.wallet.manager.dto.CustomerDTO;
import com.wallet.manager.mapper.CustomerToCustomerDTO;
import com.wallet.manager.service.CustomerService;

@Controller
public class CustomerController implements CustomerApi {

	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@Override
	public ResponseEntity<List<CustomerDTO>> getRegisteredCustomers() {
		List<CustomerDTO> customerList = CustomerToCustomerDTO.INSTANCE.toCustomerDTOList(customerService.getAllRegisteredCustomers());
		return ResponseEntity.ok(customerList);
	}

}
