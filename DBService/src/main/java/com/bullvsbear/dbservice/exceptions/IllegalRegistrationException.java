package com.bullvsbear.dbservice.exceptions;

public class IllegalRegistrationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message;

	public IllegalRegistrationException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
	
}
