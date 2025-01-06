package com.share.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.share.exceptions.WrongShareDetailsException;

@RestControllerAdvice
public class ShareControllerAdvice {

	@ExceptionHandler
	public ResponseEntity<String> handleError(NullPointerException e){
		return new ResponseEntity<> ("You are not an authorized user.",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleError(WrongShareDetailsException e){
		return new ResponseEntity<> ("Share with this name does not exist.",HttpStatus.EXPECTATION_FAILED);
	}
}
