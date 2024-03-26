package com.exception;

public class DuplicateEnrollmentException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String message;
    public  DuplicateEnrollmentException (String message) {
    	this.message = message;
    }
	
	
    @Override
   public String getMessage() {
	
	return message;
}
}
