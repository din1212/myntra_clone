package com.clone.myntra.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseEntityExecptionHandlerClass extends ResponseEntityExceptionHandler{


	//deal with only Runtime exception 
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException exception){
		
		return new  ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
		
	}
	
	
}
