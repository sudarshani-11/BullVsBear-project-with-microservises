package com.bullvsbear.dbservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bullvsbear.dbservice.exceptions.IllegalRegistrationException;
import com.bullvsbear.dbservice.exceptions.NoSuchUserFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(value = IllegalRegistrationException.class)
	public ResponseEntity<String> catchIllegalRegistrationException(IllegalRegistrationException exception){
		return ResponseEntity.badRequest().body(exception.getMessage());
	}
	
	@ExceptionHandler(value = NoSuchUserFoundException.class)
	public ResponseEntity<String> catchNoSuchUserFoundException(NoSuchUserFoundException exception){
		return ResponseEntity.status(404).body(exception.getMessage());
	}
}
