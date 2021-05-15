package com.wallet.manager.dto;

import java.math.BigDecimal;

public class WalletDTO {
	
	private String hash;

	private String name;
	
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
