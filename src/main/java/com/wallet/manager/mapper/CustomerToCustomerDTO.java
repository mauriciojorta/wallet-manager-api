package com.wallet.manager.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.wallet.manager.dto.CustomerDTO;
import com.wallet.manager.model.Customer;

@Mapper
public interface CustomerToCustomerDTO {
	CustomerToCustomerDTO INSTANCE = Mappers.getMapper(CustomerToCustomerDTO.class);
	CustomerDTO toCustomerDTO(Customer customer);
	Customer toCustomer(CustomerDTO customerDTO);
	List<CustomerDTO> toCustomerDTOList(List<Customer> customerList);
}
