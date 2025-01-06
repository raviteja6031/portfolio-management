package com.share.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.share.client.UserAuthorizationClient;
import com.share.service.ShareService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ShareController.class)
class ShareControllerTest {
	
	@Autowired
	private MockMvc mockMvc;// autowired in junit 5

	@MockBean
	private ShareService shareService;
	
	@MockBean
	UserAuthorizationClient userAuthorizationClient;

	@Test
	void testDailySharePriceByName() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		mockMvc.perform(get("/shares/name").param("shareName","DSL").header("Authorization", token)).andExpect(status().isOk());
		verify(shareService).getByShareName("DSL");
	}

	@Test
	void testDailySharePriceById() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		mockMvc.perform(get("/shares/id").param("shareId","101").header("Authorization", token)).andExpect(status().isOk());
		verify(shareService).getByShareId(101);
	}
	
	@Test
	void testDailySharePriceByNameNegative() throws Exception {
		String token = "";
		when(userAuthorizationClient.validateToken(token)).thenReturn(false);
		mockMvc.perform(get("/shares/name").param("shareName","DSL").header("Authorization", token)).andExpect(status().isUnauthorized());
		verify(shareService, times(0)).getByShareName("DSL");
	}

	@Test
	void testDailySharePriceByIdNegative() throws Exception {
		String token = "";
		when(userAuthorizationClient.validateToken(token)).thenReturn(false);
		mockMvc.perform(get("/shares/id").param("shareId","101").header("Authorization", token)).andExpect(status().isUnauthorized());
		verify(shareService, times(0)).getByShareId(101);
	}
	
}
