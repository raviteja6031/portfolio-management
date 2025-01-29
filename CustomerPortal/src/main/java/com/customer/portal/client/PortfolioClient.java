package com.customer.portal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.customer.portal.vo.*;

@FeignClient(url = "http://localhost:8084", name = "PORTFOLIO-CLIENT")
public interface PortfolioClient {

	@PostMapping("/portfolio")
	public ResponseEntity<Portfolio> getPortfolioById(@RequestHeader("Authorization") String authorization, @RequestParam int portfolioId);
	
}
