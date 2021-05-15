package com.wallet.manager.exception;
import org.springframework.http.HttpStatus;

public class BadRequestException extends RestException {

	private static final long serialVersionUID = -6990607543207059651L;

	public BadRequestException(String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}

	
}