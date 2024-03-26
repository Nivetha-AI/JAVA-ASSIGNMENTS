package com.exception;

public class InvalidTeacherDataException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
    public InvalidTeacherDataException(String message) {
    	this.message = message;
    }
	
	
    @Override
   public String getMessage() {
	
	return message;
}
}
