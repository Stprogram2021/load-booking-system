package com.example.loadbooking.exception;

public class ResourceNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8123478343296451929L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
