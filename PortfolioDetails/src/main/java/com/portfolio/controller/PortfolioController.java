package com.portfolio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.client.UserAuthorizationClient;
import com.portfolio.model.Portfolio;
import com.portfolio.service.PortfolioService;

@RestController
@RequestMapping
public class PortfolioController {

	@Autowired
	PortfolioService portfolioService;

	@Autowired
	UserAuthorizationClient userAuthorizationClient;

	private static Logger logger = LoggerFactory.getLogger(PortfolioController.class);

	@PostMapping("/portfolio")
	public ResponseEntity<Portfolio> getPortfolioById(@RequestHeader("Authorization") String authorization,
			@RequestParam int portfolioId) {
		if (!userAuthorizationClient.validateToken(authorization)) {
			logger.info("AUTHORIZATION FAILED!!\nCLASS: PortfolioController");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else {
			logger.info("Authorization Success!!\nInside CLASS: PortfolioController -> Get Portfolio by ID");
			return new ResponseEntity<>(portfolioService.getById(portfolioId), HttpStatus.OK);
		}
	}
	
}
