package com.wallet.manager.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashingUtils {
	
	public static String generateRandomHash() throws NoSuchAlgorithmException {
		 byte[] array = new byte[32]; 
		 new SecureRandom().nextBytes(array);
		 String generatedString = new String(array, Charset.forName("UTF-8"));
		 
		 MessageDigest digest = MessageDigest.getInstance("SHA3-256");
		 byte[] hashbytes = digest.digest(generatedString.getBytes(StandardCharsets.UTF_8));
		 return bytesToHex(hashbytes);
	}
	
   private static String bytesToHex(byte[] hash) {
       StringBuilder hexString = new StringBuilder(2 * hash.length);
       for (byte h : hash) {
           String hex = Integer.toHexString(0xff & h);
           if (hex.length() == 1)
               hexString.append('0');
           hexString.append(hex);
       }
       return hexString.toString();
   }

}
