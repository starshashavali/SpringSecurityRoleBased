package com.org.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {
	
	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<?> handleDuplicateEmailException(DuplicateEmailException ex){
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	@ExceptionHandler(UserInActiveException.class)
	public ResponseEntity<?> handleUserInActiveException(UserInActiveException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<?> handleEmailNotFoundException(EmailNotFoundException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<?> handleUserIdNotFoundException(UserIdNotFoundException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	//DuplicateCarException
	@ExceptionHandler(DuplicateCarException.class)
	public ResponseEntity<?> handleDuplicateCarException(DuplicateCarException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
}
