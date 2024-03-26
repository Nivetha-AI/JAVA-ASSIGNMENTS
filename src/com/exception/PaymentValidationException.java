package com.exception;

public class PaymentValidationException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
    public PaymentValidationException(String message) {
    	this.message = message;
    }
	
	
    public String getMessage() {
	
	return message;
    }
}
