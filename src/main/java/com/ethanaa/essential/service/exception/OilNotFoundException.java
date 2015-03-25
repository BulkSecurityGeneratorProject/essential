package com.ethanaa.essential.service.exception;

public class OilNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public OilNotFoundException() {
		super();
	}
	
	public OilNotFoundException(Long id) {		
		super("An oil with id [" + id + "] could not be found");
	}
	
	public OilNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public OilNotFoundException(String message) {
		super(message);
	}
	
	public OilNotFoundException(Throwable cause) {
		super(cause);
	}	

}
