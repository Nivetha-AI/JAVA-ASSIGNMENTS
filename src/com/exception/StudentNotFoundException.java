package com.exception;

public class StudentNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
		private String message;
	    public StudentNotFoundException(String message) {
	    	this.message = message;
	    }
		
		
	    @Override
	   public String getMessage() {
		
		return message;
	}

}
