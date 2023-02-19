package com.rahul.blog.app.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rahul.blog.app.payload.ApiResponce;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFoundException(ResourceNotFoundException exception) {

		ApiResponce responce = new ApiResponce();
		responce.setMessage(exception.getMessage());
		responce.setSuccess(false);
		return new ResponseEntity<ApiResponce>(responce, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(
			MethodArgumentNotValidException exception) {

		Map<String, String> error =new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((err) ->{
			String fieldName=((FieldError)err).getField();
			String message=err.getDefaultMessage();
			error.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String,String>>(error,HttpStatus.BAD_REQUEST);
 	}
	
	
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponce> apiExceptionExceptionHandler(
			ApiException exception) {

		ApiResponce responce = new ApiResponce();
		responce.setMessage(exception.getMessage());
		responce.setSuccess(false);
		return new ResponseEntity<ApiResponce>(responce, HttpStatus.BAD_REQUEST);
		
		
 	}
}
