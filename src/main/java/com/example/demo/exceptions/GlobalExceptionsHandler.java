package com.example.demo.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
@ControllerAdvice
public class GlobalExceptionsHandler {
	
	private static final Logger log = LogManager.getLogger(GlobalExceptionsHandler.class.getName());
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex)
	{
					 System.out.println("Inside @ExceptionHandler >>>>>>>>>>>>>>>>>> ");
				     Map<String, String> errors = new HashMap<>();
				     
				     ex.getBindingResult().getAllErrors().forEach((error) -> {
				     String fieldName = ((FieldError) error).getField();
				     String errorMessage = error.getDefaultMessage();
				         
				     errors.put(fieldName, errorMessage);
				     });
				     System.out.println(" >>>>>>>>>>>>>>>>>> " +  errors);
				     return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
	}
	
	 @ExceptionHandler(Exception.class)
	    public final ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
		 Map<String,String> error = new HashMap<>();
		 error.put("path",request.getContextPath());
		 error.put("message","Please contact system administrator");
	        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

}
