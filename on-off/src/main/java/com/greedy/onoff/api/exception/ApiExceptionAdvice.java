package com.greedy.onoff.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.greedy.onoff.api.dto.ApiExceptionDto;
import com.greedy.onoff.member.exception.DuplicateMemberEmailException;
import com.greedy.onoff.member.exception.LoginFailedException;
import com.greedy.onoff.member.exception.TokenException;


@RestControllerAdvice
public class ApiExceptionAdvice {
	
	@ExceptionHandler(DuplicateMemberEmailException.class)
	public ResponseEntity<ApiExceptionDto> exceptionHandler(DuplicateMemberEmailException e){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ApiExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage()));
	}
	
	@ExceptionHandler(LoginFailedException.class)
	public ResponseEntity<ApiExceptionDto> exceptionHandler(LoginFailedException e){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ApiExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage()));
	}
	
	@ExceptionHandler(TokenException.class)
	public ResponseEntity<ApiExceptionDto> exceptionHandler(TokenException e){
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED) 
				.body(new ApiExceptionDto(HttpStatus.UNAUTHORIZED, e.getMessage())); 
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiExceptionDto> exceptionHandler(RuntimeException e){
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR) 
				.body(new ApiExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())); 
	}
	
}
