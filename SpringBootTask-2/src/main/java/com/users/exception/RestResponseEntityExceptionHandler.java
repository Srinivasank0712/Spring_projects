package com.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
//
//	@ExceptionHandler(DataValidationException.class)
//	public ResponseEntity<UserResponse> dataValidation(DataValidationException exception,WebRequest request)
//	{
//		UserResponse message=new UserResponse(exception.getMessage(),HttpStatus.OK);
//		return ResponseEntity.status(HttpStatus.OK).body(message);	
//	}
//	
//	@ExceptionHandler(UserNotFoundException.class)
//	public ResponseEntity<UserResponse> userNotFound(UserNotFoundException exception,WebRequest request)
//	{
//		UserResponse message=new UserResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
//	}
}
