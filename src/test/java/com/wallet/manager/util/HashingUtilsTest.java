package com.wallet.manager.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.NoSuchAlgorithmException;


public class HashingUtilsTest {
	
	@Test
	public void testGenerateRandomHash_Success() throws NoSuchAlgorithmException {
		assertThat(HashingUtils.generateRandomHash()).isNotNull().hasSize(64);
	}

}
