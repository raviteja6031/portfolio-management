package com.share.controller;

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

import com.share.client.UserAuthorizationClient;
import com.share.exceptions.WrongShareDetailsException;
import com.share.model.Share;
import com.share.service.ShareService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/shares")
public class ShareController {

	@Autowired
	ShareService shareService;

	@Autowired
	UserAuthorizationClient userAuthorizationClient;

	private static Logger logger = LoggerFactory.getLogger(ShareController.class);

	@ApiOperation(value="Gets Daily Share Prices by Share Name",
		    notes="Enter Share Name",
		    response = Share.class)
	@GetMapping("/name")
	public ResponseEntity<Share> dailySharePriceByName(@RequestHeader("Authorization") String authorization,
			@ApiParam(name = "Share Details Name") @RequestParam String shareName) throws WrongShareDetailsException {
		if (!userAuthorizationClient.validateToken(authorization)) {
			logger.info("Unauthorized Access");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		else {
			logger.info("Authorized to get Share by Name");
			return new ResponseEntity<>(shareService.getByShareName(shareName), HttpStatus.OK);
		}
	}

	@ApiOperation(value="Gets Daily Share Prices by Share Id",
		    notes="Enter Share Id",
		    response = Share.class)
	@GetMapping("/id")
	public ResponseEntity<Share> dailySharePriceById(@RequestHeader("Authorization") String authorization,
			@ApiParam(name = "Share Details Id") @RequestParam int shareId) throws WrongShareDetailsException {
		if (!userAuthorizationClient.validateToken(authorization)) {
			logger.info("Unauthorized Access");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		else {
			logger.info("Authorized to get Share by Id");
			return new ResponseEntity<>(shareService.getByShareId(shareId), HttpStatus.OK);
		}
	}
}
