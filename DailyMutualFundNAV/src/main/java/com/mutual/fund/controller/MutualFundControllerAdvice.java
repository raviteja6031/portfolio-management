package com.mutual.fund.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mutual.fund.exceptions.MutualFundWrongDetail;

@RestControllerAdvice
public class MutualFundControllerAdvice {

	@ExceptionHandler
	public ResponseEntity<String> handleError(NullPointerException e){
		return new ResponseEntity<> ("You are not an authorized user.",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleError(MutualFundWrongDetail e){
		return new ResponseEntity<> ("Mutual Fund with this name does not exist.",HttpStatus.EXPECTATION_FAILED);
	}
}
