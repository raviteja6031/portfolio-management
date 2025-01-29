package com.portfolio.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.client.UserAuthorizationClient;
import com.portfolio.model.Portfolio;
import com.portfolio.model.ShareDetails;
import com.portfolio.service.PortfolioService;
import com.portfolio.service.ShareDetailsService;

@RestController
public class ShareDetailsController {

	@Autowired
	ShareDetailsService shareDetailsService;

	@Autowired
	PortfolioService portfolioService;

	@Autowired
	UserAuthorizationClient userAuthorizationClient;

	private static Logger logger = LoggerFactory.getLogger(ShareDetailsController.class);

	@GetMapping("/shareDetails")
	public ResponseEntity<ShareDetails> getByShareDetailsId(@RequestHeader("Authorization") String authorization,
			@RequestParam int shareDetailsId) {
		if (!userAuthorizationClient.validateToken(authorization)) {
			logger.info("AUTHORIZATION FAILED!!\nCLASS: ShareDetailsController : getByShareDetailsId");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else {
			logger.info("AUTHORIZATION SUCCESS!!\nCLASS: ShareDetailsController : getByShareDetailsId");
			ShareDetails shareDetails = shareDetailsService.getById(shareDetailsId);
			if (shareDetails == null) {
				logger.info("shareDetails is NULL : getByShareDetailsId");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			} else {
				logger.info("shareDetails is NOT NULL. STATUS=OK : getByShareDetailsId");
				return new ResponseEntity<>(shareDetails, HttpStatus.OK);
			}
		}
	}

	@PostMapping("/shareDetailsByPid")
	public ResponseEntity<List<ShareDetails>> getByShareDetailsByPortfolioId(
			@RequestHeader("Authorization") String authorization, @RequestBody Portfolio portfolioId) {
		if (!userAuthorizationClient.validateToken(authorization)) {
			logger.info("AUTHORIZATION FAILED!!\nCLASS: ShareDetailsController : getByShareDetailsByPortfolioId");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else {
			logger.info("AUTHORIZATION SUCCESS!!\nCLASS: ShareDetailsController : getByShareDetailsByPortfolioId");
			List<ShareDetails> shareDetails = shareDetailsService.getByPortfolioId(portfolioId);
			if (shareDetails == null) {
				logger.info("shareDetails is NULL : getByShareDetailsByPortfolioId");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			} else {
				logger.info("shareDetails is NOT NULL. STATUS=OK : getByShareDetailsByPortfolioI");
				return new ResponseEntity<>(shareDetails, HttpStatus.OK);
			}
		}
	}

	@PostMapping("/shareDeletion")
	public HttpEntity<Object> deleteByPortfolio(@RequestHeader("Authorization") String authorization,
			@RequestBody int shareDetailsId) {
		if (!userAuthorizationClient.validateToken(authorization)) {
			logger.info("AUTHORIZATION FAILED!!\nCLASS: ShareDetailsController: deleteByPortfolio");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else {
			logger.info("AUTHORIZATION SUCCESS!!\nCLASS: ShareDetailsController: deleteByPortfolio -> STATUS is OK");
			shareDetailsService.deleteById(shareDetailsId);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
	}

	@PostMapping("/shareDetailsUpdate")
	public ResponseEntity<ShareDetails> update(@RequestHeader("Authorization") String authorization,
			@RequestBody ShareDetails shareDetails) {
		logger.info(shareDetails.toString());
		if (!userAuthorizationClient.validateToken(authorization)) {
			logger.info("AUTHORIZATION FAILED!!\nCLASS: ShareDetailsController: shareDetailsUpdate");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else {
			logger.info("AUTHORIZATION SUCCESS!!\nCLASS: ShareDetailsController: shareDetailsUpdate -> STATUS is ACCEPTED");
			return new ResponseEntity<>(shareDetailsService.update(shareDetails), HttpStatus.ACCEPTED);
		}
	}
}
