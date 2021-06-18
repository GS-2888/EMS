package com.ems.infrastructure.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ems.domain.model.ErrorMessage;



@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
		
		String errorMessageDescrption  = ex.getLocalizedMessage();
		
		Map<String,String> errors = new HashMap<String,String>();
		
		if(errorMessageDescrption == null) errorMessageDescrption = ex.toString();
		
		errors.put("Error", errorMessageDescrption);
		ErrorMessage errorMessage= new ErrorMessage(new Date(), errors);
	
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		Map<String,String> errors = new HashMap<String,String>();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		ErrorMessage errorMessage= new ErrorMessage(new Date(), errors);
		return new ResponseEntity<>(errorMessage,headers, HttpStatus.BAD_REQUEST);
	}
	

	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
	    Map<String, String> errors = new HashMap<>();
	     
	    ex.getConstraintViolations().forEach(cv -> {
	        errors.put(cv.getPropertyPath().toString(), cv.getMessage());	       
	    }); 
	    ErrorMessage errorMessage= new ErrorMessage(new Date(), errors);
	     return new ResponseEntity<>(errorMessage,new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
