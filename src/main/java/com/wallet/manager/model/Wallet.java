package com.wallet.manager.model;

import java.math.BigDecimal;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("wallets")
@CompoundIndex(name = "customer_wallet_idx", def = "{'customerId' : 1, 'hash' : 1}", unique=true)
public class Wallet {
	@Id
	private String id;
	
	private ObjectId customerId;
	
	@Indexed(unique= true)
	private String hash;

	private String name;
	
	private BigDecimal balance;

	public Wallet() {
	}

	public Wallet(String hash, String name, BigDecimal balance) {
		super();
		this.hash = hash;
		this.name = name;
		this.balance = balance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ObjectId getCustomerId() {
		return customerId;
	}

	public void setCustomerId(ObjectId customer) {
		this.customerId = customer;
	}

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
