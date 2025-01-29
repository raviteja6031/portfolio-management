package com.networth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.networth.vo.Portfolio;

@FeignClient(url = "http://localhost:8084", name = "PORTFOLIO-CLIENT")
public interface PortfolioClient {

	@PostMapping("/portfolio")
	public ResponseEntity<Portfolio> getPortfolioById(@RequestHeader("Authorization") String authorization, @RequestParam int portfolioId);
	
}
