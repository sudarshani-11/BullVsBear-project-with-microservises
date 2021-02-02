package com.bullvsbear.apigateway.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bullvsbear.apigateway.exceptions.NoSuchUserFoundException;
import com.bullvsbear.apigateway.exceptions.ValidationError;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(value = ValidationError.class)
	public ResponseEntity<String> validationErrorHandler(ValidationError error){
		return ResponseEntity.badRequest().body(error.getMessage());
	}
	
	public ResponseEntity<String> noSuchUserFoundException(NoSuchUserFoundException exception){
		return ResponseEntity.badRequest().body(exception.getMessage());
	}
}
