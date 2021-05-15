package com.wallet.manager.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6990607543207059651L;

	public NotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

}