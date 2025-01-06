package com.mutual.fund.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.mutual.fund.service.MutualFundService;
import com.mutual.fund.client.UserAuthorizationClient;

@WebMvcTest(MutualFundController.class)
class MutualFundControllerTest {

	@Autowired
	private MockMvc mockMvc;// autowired in junit 5

	@MockBean
	private MutualFundService mutualFundService;

	@MockBean
	UserAuthorizationClient userAuthorizationClient;

	@Test
	void testGetByMutualFundNav() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		mockMvc.perform(get("/mutualFunds/name").param("mutualFundName", "Nippon Value Fund (G)")
				.header("Authorization", token)).andExpect(status().isOk());
		verify(mutualFundService).getByMutualFundName("Nippon Value Fund (G)");
	}

	@Test
	void testGetByMutualFundId() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		mockMvc.perform(get("/mutualFunds/id").param("mutualFundId", "201").header("Authorization", token))
				.andExpect(status().isOk());
		verify(mutualFundService).getByMutualFundId(201);
	}
	
	@Test
	void testGetByMutualFundNavNegative() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(false);
		mockMvc.perform(get("/mutualFunds/name").param("mutualFundName", "Nippon Value Fund (G)")
				.header("Authorization", token)).andExpect(status().isUnauthorized());
		verify(mutualFundService,times(0)).getByMutualFundName("Nippon Value Fund (G)");
	}
	
	@Test
	void testGetByMutualFundIdNegative() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(false);
		mockMvc.perform(get("/mutualFunds/id").param("mutualFundId", "201").header("Authorization", token))
				.andExpect(status().isUnauthorized());
		verify(mutualFundService,times(0)).getByMutualFundId(201);
	}
	

}
