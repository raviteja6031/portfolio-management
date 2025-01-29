package com.networth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.networth.exceptions.MutualFundWrongDetail;
import com.networth.vo.MutualFund;

@FeignClient(url = "http://localhost:8082/mutualFunds", name = "MUTUAL-FUND-CLIENT")
public interface MutualFundClient {

	@GetMapping("/name")
	public ResponseEntity<MutualFund> getByMutualFundNav(@RequestHeader("Authorization") String authorization,
			@RequestParam String mutualFundName) throws NullPointerException, MutualFundWrongDetail;

	@GetMapping("/id")
	public ResponseEntity<MutualFund> getByMutualFundById(@RequestHeader("Authorization") String authorization,
			@RequestParam int mutualFundId) throws NullPointerException, MutualFundWrongDetail;
}
