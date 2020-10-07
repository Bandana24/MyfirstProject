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
import org.springframework.web.context.request.ServletWebRequest;
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
				     
				     ResponseWrapper response = new ResponseWrapper();
				     response.setMsg_failure(errors.toString());
				     
				     System.out.println(" >>>>>>>>>>>>>>>>>> " +  errors);
				     return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}
	
	 @ExceptionHandler(Exception.class)
	    public final ResponseEntity<?> handleAllExceptions(Exception ex, ServletWebRequest request) {
		 Map<String,String> error = new HashMap<>();
		 
		 String url = request.toString();
		 
		 error.put("path",url);
		 error.put("message","Please contact system administrator: "+ex.getMessage());
		 ResponseWrapper response = new ResponseWrapper();
	     response.setMsg_failure(error.toString());
	        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	    }

}
