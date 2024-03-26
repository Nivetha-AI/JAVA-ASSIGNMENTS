package com.exception;

public class InvalidCourseDataException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
    public InvalidCourseDataException(String message) {
    	this.message = message;
    }
	
	
    public String getMessage() {
	
	return message;
    }
}
