package com.mt.REST.Boot.Demo.error.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mt.REST.Boot.Demo.error.ErrorResponse;

@ControllerAdvice
public class CourseExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(CourseNotFoundException e) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
