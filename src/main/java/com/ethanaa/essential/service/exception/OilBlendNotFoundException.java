package com.ethanaa.essential.service.exception;

public class OilBlendNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public OilBlendNotFoundException() {
		super();
	}
	
	public OilBlendNotFoundException(Long id) {		
		super("An oil blend with id [" + id + "] could not be found");
	}
	
	public OilBlendNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public OilBlendNotFoundException(String message) {
		super(message);
	}
	
	public OilBlendNotFoundException(Throwable cause) {
		super(cause);
	}	

}
