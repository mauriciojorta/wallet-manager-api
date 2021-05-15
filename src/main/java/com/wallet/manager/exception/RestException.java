package com.wallet.manager.exception;
import org.springframework.http.HttpStatus;

public class RestException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private final int statusCode;
	private final String message;
	
	public int getStatusCode() {
		return statusCode;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public RestException(HttpStatus status, String message) {
		super();
		this.statusCode = status.value();
		this.message = message;
	}
	
	public RestException(HttpStatus status) {
		super();
		this.statusCode = status.value();
		this.message = status.getReasonPhrase();
	}
}
