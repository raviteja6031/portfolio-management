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
import com.portfolio.model.MutualFundDetails;
import com.portfolio.model.Portfolio;
import com.portfolio.service.MutualFundDetailsService;

@RestController
public class MutualFundDetailsController {

	@Autowired
	MutualFundDetailsService mutualFundDetailsService;

	@Autowired
	UserAuthorizationClient userAuthorizationClient;

	private static Logger logger = LoggerFactory.getLogger(MutualFundDetailsController.class);

	@GetMapping("/mutualFundDetails")
	public ResponseEntity<MutualFundDetails> getByMutualFundDetailsId(
			@RequestHeader("Authorization") String authorization, @RequestParam int mfDetailsId) {
		if (!userAuthorizationClient.validateToken(authorization)) {
			logger.info("AUTHORIZATION FAILED!!\nCLASS: MutualFundDetailsController : getByMutualFundDetailsId");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else {
			MutualFundDetails mutualFundDetails = mutualFundDetailsService.getById(mfDetailsId);
			logger.info("AUTHORIZATION SUCCESS!!\nCLASS: MutualFundDetailsController : getByMutualFundDetailsId");
			return new ResponseEntity<>(mutualFundDetails, HttpStatus.OK);
		}
	}

	@PostMapping("/mutualFundDetailsByPid")
	public ResponseEntity<List<MutualFundDetails>> getByMutualFundDetailsByPortfolioId(
			@RequestHeader("Authorization") String authorization, @RequestBody Portfolio portfolioId) {
		if (!userAuthorizationClient.validateToken(authorization)) {
			logger.info(
					"AUTHORIZATION FAILED!!\nCLASS: MutualFundDetailsController : getByMutualFundDetailsByPortfolioId");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else {
			logger.info(
					"AUTHORIZATION SUCCESS!!\nCLASS: MutualFundDetailsController : getByMutualFundDetailsByPortfolioId");
			List<MutualFundDetails> mutualFundDetails = mutualFundDetailsService.getByPid(portfolioId);
			logger.info(mutualFundDetails.toString());
			return new ResponseEntity<>(mutualFundDetails, HttpStatus.OK);
		}
	}

	@PostMapping("/mutualFundDeletion")
	public HttpEntity<Object> deleteByMutualFundDetailId(@RequestHeader("Authorization") String authorization,
			@RequestBody int mutualFundDetailId) {
		if (!userAuthorizationClient.validateToken(authorization)) {
			logger.info("AUTHORIZATION FAILED!!\nCLASS: MutualFundDetailsController : deleteByMutualFundDetailId");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else {
			logger.info("AUTHORIZATION SUCCESS!!\nCLASS: MutualFundDetailsController : deleteByMutualFundDetailId");
			mutualFundDetailsService.deleteById(mutualFundDetailId);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
	}

	@PostMapping("/mutualFundDetailsUpdate")
	public ResponseEntity<MutualFundDetails> update(@RequestHeader("Authorization") String authorization,
			@RequestBody MutualFundDetails mutualFundDetails) {
		logger.info(mutualFundDetails.toString());
		if (!userAuthorizationClient.validateToken(authorization)) {
			logger.info("AUTHORIZATION FAILED!!\nCLASS: MutualFundDetailsController : update MutualFundDetails");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else {
			logger.info("AUTHORIZATION SUCCESS!!\nCLASS: MutualFundDetailsController : update");
			return new ResponseEntity<>(mutualFundDetailsService.update(mutualFundDetails), HttpStatus.ACCEPTED);
		}
	}

}
