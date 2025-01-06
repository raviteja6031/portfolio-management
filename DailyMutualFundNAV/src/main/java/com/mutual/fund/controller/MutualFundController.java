package com.mutual.fund.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mutual.fund.client.UserAuthorizationClient;
import com.mutual.fund.exceptions.MutualFundWrongDetail;
import com.mutual.fund.model.MutualFund;
import com.mutual.fund.service.MutualFundService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/mutualFunds")
public class MutualFundController {

	@Autowired
	MutualFundService mutualFundService;

	@Autowired
	UserAuthorizationClient userAuthorizationClient;

	private static Logger logger = LoggerFactory.getLogger(MutualFundController.class);

	@ApiOperation(value="Returns Mutual Fund's details from Mutual Fund Name",
		    notes="Provide Mutual Fund Name",
		    response = MutualFund.class)
	@GetMapping("/name")
	public ResponseEntity<MutualFund> getByMutualFundNav(@RequestHeader("Authorization") String authorization,
			  @ApiParam(name = "Mutual Fund Name") @RequestParam String mutualFundName) throws NullPointerException, MutualFundWrongDetail {
		if (!userAuthorizationClient.validateToken(authorization)) {
			logger.info("Token validation for Mutual Fund NAV");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else {
			MutualFund mutualFund = mutualFundService.getByMutualFundName(mutualFundName);
			logger.info("Token validated for Mutual Fund NAV. Finding MutualFund Details by Name.");
			return new ResponseEntity<>(mutualFund, HttpStatus.OK);
		}
	}

	@ApiOperation(value="Returns Mutual Fund's details from Mutual Fund Id",
		    notes="Provide Mutual Fund Id",
		    response = MutualFund.class)
	@GetMapping("/id")
	public ResponseEntity<MutualFund> getByMutualFundById(@RequestHeader("Authorization") String authorization,
			  @ApiParam(name = "Mutual Fund Id") @RequestParam int mutualFundId) throws NullPointerException, MutualFundWrongDetail {
		if (!userAuthorizationClient.validateToken(authorization)) {
			logger.info("Token validation for Mutual Fund NAV");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}else {
			MutualFund mutualFund = mutualFundService.getByMutualFundId(mutualFundId);
			logger.info("Token validated for Mutual Fund NAV. Finding MutualFund Details by ID.");
			return new ResponseEntity<>(mutualFund, HttpStatus.OK);
		}
	}
}
