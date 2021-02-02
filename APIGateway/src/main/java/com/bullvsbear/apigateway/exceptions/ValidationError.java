package com.bullvsbear.apigateway.exceptions;

public class ValidationError extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message;

	public ValidationError(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
	
}
