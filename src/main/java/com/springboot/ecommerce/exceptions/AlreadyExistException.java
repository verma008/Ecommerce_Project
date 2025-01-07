package com.springboot.ecommerce.exceptions;

public class AlreadyExistException extends RuntimeException{
	
	public AlreadyExistException(String message) {
		super(message);   //calling parent class constructor
	}

}
