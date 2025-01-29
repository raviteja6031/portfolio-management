package com.customer.portal.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.customer.portal.vo.*;

@FeignClient(url = "http://localhost:8084", name = "MF-DETAILS-CLIENT")
public interface MutualFundDetailsClient {

	@GetMapping("/mutualFundDetails")
	public ResponseEntity<MutualFundDetails> getByMutualFundDetailsId(@RequestHeader("Authorization") String authorization, @RequestParam int mfDetailsId);
	
	@PostMapping("/mutualFundDetailsByPid")
	public ResponseEntity<List<MutualFundDetails>> getByMutualFundDetailsByPortfolioId(@RequestHeader("Authorization") String authorization, @RequestBody Portfolio portfolioId);

	@PostMapping("/mutualFundDeletion")
	public HttpEntity<Object> deleteByMutualFundDetailId(@RequestHeader("Authorization") String authorization, int mutualFundDetailId);
	
	@PostMapping("/mutualFundDetailsUpdate")
	public ResponseEntity<MutualFundDetails> update(@RequestHeader("Authorization") String authorization,
			@RequestBody MutualFundDetailsDummy mutualFundDetails);
}
