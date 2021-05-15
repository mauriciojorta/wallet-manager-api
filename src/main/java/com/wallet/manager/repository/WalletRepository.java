package com.wallet.manager.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.wallet.manager.model.Wallet;

public interface WalletRepository  extends MongoRepository<Wallet, String> {
	
	List<Wallet> findByCustomerId(ObjectId customer);
	
	Wallet findByHash(String hash);
}
