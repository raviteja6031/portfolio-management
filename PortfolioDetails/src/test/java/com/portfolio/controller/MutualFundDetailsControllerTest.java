package com.portfolio.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.client.UserAuthorizationClient;
import com.portfolio.model.MutualFundDetails;
import com.portfolio.model.Portfolio;
import com.portfolio.model.ShareDetails;
import com.portfolio.service.MutualFundDetailsService;

@WebMvcTest(MutualFundDetailsController.class)
class MutualFundDetailsControllerTest {

	@Autowired
	private MockMvc mockMvc;// autowired in junit 5

	@MockBean
	UserAuthorizationClient userAuthorizationClient;

	@MockBean
	MutualFundDetailsService mutualFundDetailsService;

	@MockBean
	Portfolio portfolio;

	@Autowired
	@MockBean
	MutualFundDetails mutualFundDetails;

	@Autowired
	@MockBean
	ShareDetails shareDetails;

	@Autowired
	ObjectMapper om;

	@Test
	void testGetByMutualFundDetailsId() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		mockMvc.perform(get("/mutualFundDetails").param("mfDetailsId", "301").header("Authorization", token))
				.andExpect(status().isOk());
		verify(mutualFundDetailsService).getById(301);
	}
	
	@Test
	void testGetByMutualFundDetailsIdNegative() throws Exception {
		String token = "udeklwl12ddjwk22cnwcddvfcj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(false);
		mockMvc.perform(get("/mutualFundDetails").param("mfDetailsId", "301").header("Authorization", token))
				.andExpect(status().isUnauthorized());
		verify(mutualFundDetailsService,times(0)).getById(301);
	}

	@Test
	void testGetByMutualFundDetailsByPortfolioId() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		Portfolio p = new Portfolio();
		p.setPortfolioId(101);
		String json = om.writeValueAsString(p);
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		mockMvc.perform(post("/mutualFundDetailsByPid").header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
		//verify(mutualFundDetailsService,times(1)).getByPid(p);	-> requires hashCode and Equals
	}
	
	@Test
	void testGetByMutualFundDetailsByPortfolioIdNegative() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		Portfolio p = new Portfolio();
		p.setPortfolioId(101);
		String json = om.writeValueAsString(p);
		when(userAuthorizationClient.validateToken(token)).thenReturn(false);
		mockMvc.perform(post("/mutualFundDetailsByPid").header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isUnauthorized());
		verify(mutualFundDetailsService,times(0)).getByPid(p);
	}
	
	@Test
	void testDeleteByMutualFundDetailId() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		int mutualFundDetailId = 301;
		String json = om.writeValueAsString(mutualFundDetailId);
		mockMvc.perform(post("/mutualFundDeletion").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
		verify(mutualFundDetailsService).deleteById(301);
	}
	
	@Test
	void testDeleteByMutualFundDetailIdNegative() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(false);
		int mutualFundDetailId = 301;
		String json = om.writeValueAsString(mutualFundDetailId);
		mockMvc.perform(post("/mutualFundDeletion").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isUnauthorized());
		verify(mutualFundDetailsService,times(0)).deleteById(301);
	}
	
	@Test
	void testUpdate() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		MutualFundDetails mutualFundDetails = new MutualFundDetails();
		mutualFundDetails.setMfDetailsId(301);
		mutualFundDetails.setMutualFundName("DSP Government Securities Fund - Direct Plan - Growth");
		mutualFundDetails.setCount(3);
		String json = om.writeValueAsString(mutualFundDetails);
		mockMvc.perform(post("/mutualFundDetailsUpdate").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isAccepted());
		verify(mutualFundDetailsService).update(mutualFundDetails);
	}
	
	@Test
	void testUpdateNegative() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(false);
		MutualFundDetails mutualFundDetails = new MutualFundDetails();
		mutualFundDetails.setMfDetailsId(301);
		mutualFundDetails.setMutualFundName("DSP Government Securities Fund - Direct Plan - Growth");
		mutualFundDetails.setCount(3);
		String json = om.writeValueAsString(mutualFundDetails);
		mockMvc.perform(post("/mutualFundDetailsUpdate").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isUnauthorized());
		verify(mutualFundDetailsService,times(0)).update(mutualFundDetails);
	}

}

