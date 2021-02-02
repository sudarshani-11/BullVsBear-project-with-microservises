package com.bullvsbear.apigateway.exceptions;

public class NoSuchUserFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message;

	public NoSuchUserFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
