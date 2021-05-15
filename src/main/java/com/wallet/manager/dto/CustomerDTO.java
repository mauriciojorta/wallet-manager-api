package com.wallet.manager.dto;

import io.swagger.annotations.ApiModelProperty;

public class CustomerDTO {
	
    @ApiModelProperty(value = "Name of the customer", required = true, example = "John")
    private String name;
    @ApiModelProperty(value = "Surname of the customer", required = true, example = "Doe")
    private String surname;
    @ApiModelProperty(value = "Email of the customer", required = true, example = "example@email.com")
    private String email;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String idDocument) {
		this.email = idDocument;
	}
    
    

}
