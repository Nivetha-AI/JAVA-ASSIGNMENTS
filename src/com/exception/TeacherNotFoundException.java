package com.exception;

public class TeacherNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
    public TeacherNotFoundException(String message) {
    	this.message = message;
    }
	
	
    public String getMessage() {
	
	return message;
    }

}
