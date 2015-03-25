package com.ethanaa.essential.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ethanaa.essential.service.exception.InvalidResourceException;
import com.ethanaa.essential.service.exception.OilNotFoundException;
import com.ethanaa.essential.web.rest.resource.error.ErrorResource;
import com.ethanaa.essential.web.rest.resource.error.FieldErrorResource;

@ControllerAdvice
public class RestExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

	private static final HttpHeaders headers;
	
	static {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	}	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ErrorResource error = new ErrorResource(status, ex, (NativeWebRequest) request);		
		
		return handleExceptionInternal(ex, error, headers, status, request);				
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<?> handleConstraintViolationException(DataIntegrityViolationException e, NativeWebRequest request) {
		
		HttpStatus status = HttpStatus.CONFLICT;		
		
		ErrorResource error = new ErrorResource(status, e.getRootCause(), request);
		
		return handleExceptionInternal(e, error, headers, status, request);
	}
	
	@ExceptionHandler(InvalidResourceException.class)
	protected ResponseEntity<?> handleInvalidRequestException(InvalidResourceException e, NativeWebRequest request) {
		
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		
        List<FieldErrorResource> fieldErrorResources = new ArrayList<>();

        List<FieldError> fieldErrors = e.getErrors().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
        	
            FieldErrorResource fieldErrorResource = new FieldErrorResource();
            
            fieldErrorResource.setResource(fieldError.getObjectName());
            fieldErrorResource.setField(fieldError.getField());
            fieldErrorResource.setCode(fieldError.getCode());
            fieldErrorResource.setMessage(fieldError.getDefaultMessage());
            
            fieldErrorResources.add(fieldErrorResource);
        }
        
        ErrorResource error = new ErrorResource(status, e, request);
        error.setFieldErrors(fieldErrorResources);
        
        return handleExceptionInternal(e, error, headers, status, request);
	}
	
	@ExceptionHandler(OilNotFoundException.class)
	protected ResponseEntity<?> handleOilNotFoundException(OilNotFoundException e, NativeWebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ErrorResource error = new ErrorResource(status, e, request);
		
		return handleExceptionInternal(e, error, headers, status, request);
	}	
	
}
