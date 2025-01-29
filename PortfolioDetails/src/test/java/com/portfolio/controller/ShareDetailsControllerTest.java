package com.portfolio.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.client.UserAuthorizationClient;
import com.portfolio.model.MutualFundDetails;
import com.portfolio.model.Portfolio;
import com.portfolio.model.ShareDetails;
import com.portfolio.service.PortfolioService;
import com.portfolio.service.ShareDetailsService;

@WebMvcTest(ShareDetailsController.class)
class ShareDetailsControllerTest {

	@Autowired
	private MockMvc mockMvc;// autowired in junit 5

	@MockBean
	UserAuthorizationClient userAuthorizationClient;

	@MockBean
	Portfolio portfolio;
	
	@MockBean
	PortfolioService portfolioService;
	
	@Autowired
	@MockBean
	MutualFundDetails mutualFundDetails;

	@Autowired
	@MockBean
	ShareDetails shareDetails;

	@Autowired
	ObjectMapper om;

	@MockBean
	ShareDetailsService shareDetailsService;

	@Test
	void testGetByShareDetailsId() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		Portfolio p = new Portfolio();
		p.setPortfolioId(101);
		ShareDetails sh = new ShareDetails(101,p,"DLF",6);
//		System.out.println(sh);
//		sh.setShareDetailsId(201);
//		sh.setCount(6);
//		sh.setShareName("DLF");
		when(shareDetailsService.getById(201)).thenReturn(sh);
		mockMvc.perform(get("/shareDetails").param("shareDetailsId", "201").header("Authorization", token))
				.andExpect(status().isOk());
		verify(shareDetailsService).getById(201);
	}
	
	@Test
	void testGetByShareDetailsIdNegative() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(false);
		mockMvc.perform(get("/shareDetails").param("shareDetailsId", "201").header("Authorization", token))
				.andExpect(status().isUnauthorized());
		verify(shareDetailsService,times(0)).getById(201);
	}
	
	@Test
	void testGetByShareDetailsIdNegative2() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		Portfolio p = new Portfolio();
		p.setPortfolioId(101);
		when(shareDetailsService.getById(201)).thenReturn(null);
		mockMvc.perform(get("/shareDetails").param("shareDetailsId", "201").header("Authorization", token))
				.andExpect(status().isBadRequest());
		verify(shareDetailsService).getById(201);
	}
	
	@Test
	void testGetByShareDetailsByPortfolioId() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		Portfolio p = new Portfolio();
		p.setPortfolioId(101);
		List<ShareDetails> shares = new ArrayList<>();
		ShareDetails sh = new ShareDetails(101,p,"DLF",6);
		System.out.println(sh);
		sh.setShareDetailsId(201);
		sh.setCount(6);
		sh.setShareName("DLF");
		shares.add(sh);
		String json = om.writeValueAsString(p);
		when(shareDetailsService.getByPortfolioId(p)).thenReturn(shares);
		mockMvc.perform(post("/shareDetailsByPid").header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
		verify(shareDetailsService).getByPortfolioId(p);
	}
	
	@Test
	void testGetByShareDetailsByPortfolioIdNegative1() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		Portfolio p = new Portfolio();
		p.setPortfolioId(101);
		List<ShareDetails> shares = null;
		System.out.println(shares);
		String json = om.writeValueAsString(p);
		when(shareDetailsService.getByPortfolioId(p)).thenReturn(shares);
		mockMvc.perform(post("/shareDetailsByPid").header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest());
		verify(shareDetailsService).getByPortfolioId(p);
	}
	
	@Test
	void testGetByShareDetailsByPortfolioIdNegative2() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(false);
		Portfolio p = new Portfolio();
		p.setPortfolioId(101);
		List<ShareDetails> shares = null;
		System.out.println(shares);
		String json = om.writeValueAsString(p);
		mockMvc.perform(post("/shareDetailsByPid").header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isUnauthorized());
		verify(shareDetailsService,times(0)).getByPortfolioId(p);
	}
	
	
	@Test
	void testDeleteByPortfolio() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		int shareDetailId = 201;
		String json = om.writeValueAsString(shareDetailId);
		mockMvc.perform(post("/shareDeletion").header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
	}
	
	@Test
	void testDeleteByPortfolioNegative() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(false);
		int shareDetailId = 201;
		String json = om.writeValueAsString(shareDetailId);
		mockMvc.perform(post("/shareDeletion").header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isUnauthorized());
	}
	
	@Test
	void testUpdate() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		ShareDetails sh = new ShareDetails();
		sh.setShareDetailsId(201);
		sh.setCount(6);
		sh.setShareName("DLF");
		String json = om.writeValueAsString(sh);
		mockMvc.perform(post("/shareDetailsUpdate").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isAccepted());
		verify(shareDetailsService).update(sh);
	}
	
	@Test
	void testUpdateNegative() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(false);
		ShareDetails sh = new ShareDetails();
		sh.setShareDetailsId(201);
		sh.setCount(6);
		sh.setShareName("DLF");
		String json = om.writeValueAsString(sh);
		mockMvc.perform(post("/shareDetailsUpdate").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isUnauthorized());
		verify(shareDetailsService,times(0)).update(sh);
	}

}
