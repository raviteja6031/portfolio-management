package com.networth.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.networth.client.MutualFundDetailsClient;
import com.networth.client.PortfolioClient;
import com.networth.client.ShareDetailsClient;
import com.networth.service.CalculateNetWorthService;
import com.networth.vo.MutualFundDetails;
import com.networth.vo.Portfolio;
import com.networth.vo.ShareDetails;
import com.networth.client.UserAuthorizationClient;
import com.networth.model.AssetSaleResponse;
import com.networth.model.SellingDetails;

@WebMvcTest(CalculateNetWorthController.class)
class CalculateNetWorthControllerTest {

	@Autowired
	private MockMvc mockMvc;// autowired in junit 5

	@Autowired
	@InjectMocks
	private CalculateNetWorthController calculateNetWorthController;
//
//	MockMvc mockMvc1 = MockMvcBuilders.standaloneSetup(this.calculateNetWorthController).build();

	@MockBean
	UserAuthorizationClient userAuthorizationClient;

	@MockBean
	CalculateNetWorthService calculateNetWorthService;

	@MockBean
	PortfolioClient portfolioClient;

	@MockBean
	ShareDetailsClient shareDetailsClient;

	@MockBean
	MutualFundDetailsClient mutualFundDetailsClient;

	@Autowired
	ObjectMapper om;

	@Mock
	Portfolio p;

	@Mock
	SellingDetails sellingDetails;

	@Mock
	AssetSaleResponse assetSaleResponse;

	@Test
	void testCalculateNetworth() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		Portfolio p = new Portfolio();
		p.setPortfolioId(101);
		List<ShareDetails> sds = new ArrayList<>();
		when(shareDetailsClient.getByShareDetailsByPortfolioId(token, p))
				.thenReturn(new ResponseEntity<List<ShareDetails>>(sds, HttpStatus.OK));
		List<MutualFundDetails> mfs = new ArrayList<>();
		when(mutualFundDetailsClient.getByMutualFundDetailsByPortfolioId(token, p))
				.thenReturn(new ResponseEntity<List<MutualFundDetails>>(mfs, HttpStatus.OK));
		String json = om.writeValueAsString(p);
		mockMvc.perform(post("/calculateNetworth").header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON).content(json));
		verify(calculateNetWorthService).calculateNetworth(token, sds, mfs);
	}

	@Test
	public void testSellAssets() throws Exception {
		String token = "dbiqmalwpq";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		Portfolio p = new Portfolio();
		p.setPortfolioId(101);

		List<MutualFundDetails> mfs = new ArrayList<>();
		// mfs.add(new MutualFundDetails(201,"Nippon",5));
		List<ShareDetails> sds = new ArrayList<>();
		// sds.add(new ShareDetails(301,"DLF",6));
		Map<String, Integer> mfMap = new HashMap<>();
		mfMap.put("Nippon", 5);
		Map<String, Integer> sdMap = new HashMap<>();
		sdMap.put("DLF", 6);
		SellingDetails sellingDetails = new SellingDetails();
		sellingDetails.setPortfolioId(101);
		sellingDetails.setSellingMutualFunds(mfMap);
		sellingDetails.setSellingShares(sdMap);
		String json = om.writeValueAsString(sellingDetails);
		when(portfolioClient.getPortfolioById(token, 101)).thenReturn(new ResponseEntity<Portfolio>(p, HttpStatus.OK));
		// when(portfolioClient.getPortfolioById(token, 101).getBody()).thenReturn(p);
		when(mutualFundDetailsClient.getByMutualFundDetailsByPortfolioId(token, p))
				.thenReturn(new ResponseEntity<List<MutualFundDetails>>(mfs, HttpStatus.OK));
		when(shareDetailsClient.getByShareDetailsByPortfolioId(token, p))
				.thenReturn(new ResponseEntity<List<ShareDetails>>(sds, HttpStatus.OK));
		mockMvc.perform(post("/sellAssets").header("Authorization", token).contentType(MediaType.APPLICATION_JSON)
				.content(json));
		verify(portfolioClient).getPortfolioById(token, 101);
		verify(calculateNetWorthService).sellMutualFunds(token, sellingDetails, p, mfs);
		verify(calculateNetWorthService).sellShares(token, sellingDetails, p, sds);
	}

	@Test
	public void testSellAssetsNegative() throws Exception {
		String token = "dbiqmalwpq";
		when(userAuthorizationClient.validateToken(token)).thenReturn(false);
		Portfolio p = new Portfolio();
		p.setPortfolioId(101);
		String json = om.writeValueAsString(p);
		// when(portfolioClient.getPortfolioById(token, 101).getBody()).thenReturn(p);
//		assertThrows(NullPointerException.class, () -> {
//			calculateNetWorthService.calculateNetworth(token, null, null);
//		});
//		doThrow(NullPointerException.class)
//		mockMvc.perform(post("/calculateNetworth").header("Authorization", token)
//				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(
//						result -> assertFalse(result.getResolvedException() instanceof NullPointerException));
		ShareDetails sd = new ShareDetails(201, "DLF", 6);
		List<ShareDetails> listSd = new ArrayList<>();
//		listSd.add(sd);
//		p.setshareDetails(listSd);
		MutualFundDetails md = new MutualFundDetails(301, "Nippon", 4);
		List<MutualFundDetails> listMd = new ArrayList<>();
//		listMd.add(md);
//		p.setMutualFundDetails(listMd);
		Map<String, Integer> mfMap = new HashMap<>();
		mfMap.put("Nippon", 5);
		Map<String, Integer> sdMap = new HashMap<>();
		sdMap.put("DLF", 6);
		sellingDetails.setPortfolioId(101);
		sellingDetails.setSellingMutualFunds(mfMap);
		sellingDetails.setSellingShares(sdMap);
		NullPointerException thrown = assertThrows(NullPointerException.class,
				() -> calculateNetWorthController.sellAssets(token, sellingDetails));
		verify(calculateNetWorthService, times(0)).sellMutualFunds(token, sellingDetails, p, listMd);
		verify(calculateNetWorthService, times(0)).sellShares(token, sellingDetails, p, listSd);
	}

	@Test
	public void testCalculateNetworthNegative() throws Exception {
		String token = "dbiqmalwpq";
		when(userAuthorizationClient.validateToken(token)).thenReturn(false);
		Portfolio p = new Portfolio();
		p.setPortfolioId(101);
		String json = om.writeValueAsString(p);
		// when(portfolioClient.getPortfolioById(token, 101).getBody()).thenReturn(p);
//		assertThrows(NullPointerException.class, () -> {
//			calculateNetWorthService.calculateNetworth(token, null, null);
//		});
//		doThrow(NullPointerException.class)
//		mockMvc.perform(post("/calculateNetworth").header("Authorization", token)
//				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(
//						result -> assertFalse(result.getResolvedException() instanceof NullPointerException));
		ShareDetails sd = new ShareDetails(201, "DLF", 6);
		List<ShareDetails> listSd = new ArrayList<>();
//		listSd.add(sd);
//		p.setshareDetails(listSd);
		MutualFundDetails md = new MutualFundDetails(301, "Nippon", 4);
		List<MutualFundDetails> listMd = new ArrayList<>();
//		listMd.add(md);
//		p.setMutualFundDetails(listMd);
		NullPointerException thrown = assertThrows(NullPointerException.class,
				() -> calculateNetWorthController.calculateNetworth(token, p));
		verify(calculateNetWorthService, times(0)).calculateNetworth(token, listSd, listMd);
	}

}

//ProviderNotFoundException thrown =
//Assertions.assertThrows(ProviderNotFoundException.class, () ->
//claimServiceImpl.processSubmitClaim(1, 2, 3, 7, 202, 60000.0, 60000.0,
//"token"));
//	result -> assertFalse(result.getResolvedException() instanceof NullPointerException));

////when(portfolioClient.getPortfolioById(token, 101)).thenReturn(new ResponseEntity<Portfolio>(p,HttpStatus.OK));
//ShareDetails sd = new ShareDetails(201,"DLF",6);
//List<ShareDetails> listSd = new ArrayList<>();
////listSd.add(sd);
////p.setshareDetails(listSd);
//MutualFundDetails md = new MutualFundDetails(301,"Nippon",4);
//List<MutualFundDetails> listMd = new ArrayList<>();
////listMd.add(md);
////p.setMutualFundDetails(listMd);
////System.out.println(shareDetailsClient.getByShareDetailsByPortfolioId(token,p));
//when(shareDetailsClient.getByShareDetailsByPortfolioId(token,p)).thenReturn(null);
//String json = om.writeValueAsString(p);
//mockMvc.perform(post("/calculateNetworth").header("Authorization", token)
//		.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
////mockMvc.perform(post("/shareDetailsByPid").header("Authorization", token)
////		.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
//verify(shareDetailsClient).getByShareDetailsByPortfolioId(token, p);
//}