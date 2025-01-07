package com.springboot.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AlreadyExistException.class)
	public ResponseEntity<String> alreadyExistExceptionHandler(AlreadyExistException exception){
	String message = exception.getMessage();
	return new ResponseEntity<String>(message,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> resouceNotFoundExceptionHandler(ResourceNotFoundException exception)
	{
		String message = exception.getMessage();
		return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
	}
}
