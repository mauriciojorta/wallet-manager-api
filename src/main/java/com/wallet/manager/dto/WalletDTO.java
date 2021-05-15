package com.wallet.manager.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class WalletDTO {
	
    @ApiModelProperty(value = "Hash of the wallet", required = true, example = "b701dc7d38a8e3f5f05330c72074d60c8d0596711acd12bc72456dee4ea7922b")
	private String hash;

    @ApiModelProperty(value = "Name of the wallet", required = true, example = "Example wallet")
	private String name;
	
    @ApiModelProperty(value = "Balance of the wallet", required = true, example = "100.25")
	private BigDecimal balance;
	

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
}
