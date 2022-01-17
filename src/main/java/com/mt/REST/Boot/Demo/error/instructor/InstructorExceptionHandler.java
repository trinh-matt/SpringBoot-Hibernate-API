package com.mt.REST.Boot.Demo.error.instructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InstructorExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<InstructorErrorResponse> handleException(InstructorNotFoundException e) {
		InstructorErrorResponse errorResponse = new InstructorErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<InstructorErrorResponse> handleException(Exception e) {
		InstructorErrorResponse errorResponse = new InstructorErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
