package com.wallet.manager.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wallet.manager.exception.RestException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(RestException.class)
	public ResponseEntity<Object> customHandle(RestException ex) {
		String logMessage = "Exception: " + ex.getMessage();
		log.error(logMessage);
		HttpStatus errorCode = HttpStatus.resolve(ex.getStatusCode());
		return new ResponseEntity<>(ex.getMessage(), errorCode != null ? errorCode : HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> unexpectedException(Exception ex, WebRequest request) {
		String logMessage = "Exception: " + ex.getMessage();
		log.error(logMessage);
		return new ResponseEntity<>("Unexpected exception", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}