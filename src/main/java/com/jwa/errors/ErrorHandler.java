package com.jwa.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@CrossOrigin
public class ErrorHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(NotFoundApi e){
		
		ErrorResponse response = new ErrorResponse();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage(e.getMessage());
		response.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception e){
		
		ErrorResponse response = new ErrorResponse();
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessage("Not a valid request type for id");
		response.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}

}
