package com.customer.portal.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.customer.portal.vo.*;

@FeignClient(url = "http://localhost:8084", name = "SHARE-DETAILS-CLIENT")
public interface ShareDetailsClient {

	@GetMapping("/shareDetails")
	public ResponseEntity<ShareDetails> getByShareDetailsId(@RequestHeader("Authorization") String authorization, @RequestParam int shareDetailsId);
	
	@PostMapping("/shareDetailsByPid")
	public ResponseEntity<List<ShareDetails>> getByShareDetailsByPortfolioId(@RequestHeader("Authorization") String authorization, @RequestBody Portfolio portfolioId);

	@PostMapping("/shareDeletion")
	public void deleteByPortfolio(@RequestHeader("Authorization") String authorization, @RequestBody int shareDetailsId);

	@PostMapping("/shareDetailsUpdate")
	public ShareDetails update(@RequestHeader("Authorization") String authorization, @RequestBody ShareDetailsDummy shareDetails);
}
