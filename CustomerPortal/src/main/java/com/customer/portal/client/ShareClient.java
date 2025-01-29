package com.customer.portal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.customer.portal.vo.*;
import com.customer.portal.exceptions.*;

@FeignClient(url = "http://localhost:8083/shares", name = "SHARE-CLIENT")
public interface ShareClient {

	@GetMapping("/name")
	public ResponseEntity<Share> dailySharePriceByName(@RequestHeader("Authorization") String authorization,
			@RequestParam String shareName) throws WrongShareDetailsException;

	@GetMapping("/id")
	public ResponseEntity<Share> dailySharePriceById(@RequestHeader("Authorization") String authorization,
			@RequestParam int shareId) throws WrongShareDetailsException;

}
