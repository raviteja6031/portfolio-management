package com.networth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CalculateNetworthControllerAdvice {

	@ExceptionHandler
	public ResponseEntity<String> handleError(NullPointerException e){
		return new ResponseEntity<> ("You are not an authorized user.",HttpStatus.BAD_REQUEST);
	}
}
