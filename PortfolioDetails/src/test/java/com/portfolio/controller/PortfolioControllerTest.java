package com.portfolio.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.portfolio.client.UserAuthorizationClient;
import com.portfolio.service.PortfolioService;

@WebMvcTest(PortfolioController.class)
class PortfolioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;// autowired in junit 5

	@MockBean
	UserAuthorizationClient userAuthorizationClient;

	@MockBean
	PortfolioService portfolioService;

	@Test
	void testGetPortfolioById() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		mockMvc.perform(post("/portfolio").param("portfolioId", "101").header("Authorization", token))
		.andExpect(status().isOk());
		verify(portfolioService).getById(101);
	}
	
	@Test
	void testGetPortfolioByIdNegative() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(false);
		mockMvc.perform(post("/portfolio").param("portfolioId", "101").header("Authorization", token))
		.andExpect(status().isUnauthorized());
		verify(portfolioService,times(0)).getById(101);
	}


}
