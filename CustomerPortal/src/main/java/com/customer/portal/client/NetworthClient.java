package com.customer.portal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.customer.portal.exceptions.MutualFundWrongDetail;
import com.customer.portal.exceptions.WrongShareDetailsException;
import com.customer.portal.vo.Portfolio;
import com.customer.portal.vo.AssetSaleResponse;
import com.customer.portal.vo.SellingDetails;

@FeignClient(url = "http://localhost:8085", name = "NETWORTH-CLIENT")
public interface NetworthClient {

	@PostMapping("/calculateNetworth")
	public double calculateNetworth(@RequestHeader("Authorization") String authorization,
			@RequestBody Portfolio portfolio)
			throws NullPointerException, WrongShareDetailsException, MutualFundWrongDetail;
	
	@PostMapping("/sellAssets")
	public AssetSaleResponse sellAssets(@RequestHeader("Authorization") String authorization,
			@RequestBody SellingDetails sellingDetails)
			throws NullPointerException, WrongShareDetailsException, MutualFundWrongDetail;
}
