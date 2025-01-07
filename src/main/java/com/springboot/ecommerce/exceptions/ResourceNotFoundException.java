package com.springboot.ecommerce.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String resourceName, String fieldName, int fieldValue) {
		super(resourceName+"not found with"+fieldName+" : "+fieldValue);
		
	}
}

