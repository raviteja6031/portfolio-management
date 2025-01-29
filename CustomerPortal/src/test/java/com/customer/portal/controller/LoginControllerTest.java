package com.customer.portal.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.customer.portal.client.UserAuthorizationClient;
import com.customer.portal.vo.JwtRequestVO;
import org.mockito.Mock;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.Matchers.*;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;// autowired in junit 5

	@MockBean
	private UserAuthorizationClient userAuthorizationClient;

	@MockBean
	JwtRequestVO jwtRequestVO;

	@Test
	void testLoginForm() throws Exception {
		mockMvc.perform(get("/loginForm")).andExpect(status().isOk()).andExpect(forwardedUrl("/views/loginForm1.jsp"));
	}

	@Test
	void testLogin() throws Exception {
		String token = "fqiodwclwcnsk";
		JwtRequestVO jwtRequestVO = new JwtRequestVO();
		jwtRequestVO.setPassword("admin");
		jwtRequestVO.setUsername("admin");
		ResponseEntity<String> ct = new ResponseEntity<String>(token, HttpStatus.OK);
		ResponseEntity<Integer> id = new ResponseEntity<Integer>(101, HttpStatus.OK);
		ResponseEntity<String> un = new ResponseEntity<String>("admin", HttpStatus.OK);
		doReturn(ct).when(userAuthorizationClient).createAuthenticationToken(jwtRequestVO);
		doReturn(id).when(userAuthorizationClient).getById("Bearer "+token);
		doReturn(un).when(userAuthorizationClient).getUname("Bearer "+token);
		
		mockMvc.perform(post("/login").flashAttr("JwtRequestVO", jwtRequestVO).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is3xxRedirection());
		verify(userAuthorizationClient).createAuthenticationToken(jwtRequestVO);
	}

	@Test
	void testLoginNegative() throws Exception {
		// HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		String token = "fqiodwclwcnsk";
		JwtRequestVO jwtRequestVO = new JwtRequestVO();
		jwtRequestVO.setPassword("admin");
		jwtRequestVO.setUsername("admin");
		ResponseEntity<String> ct = new ResponseEntity<String>(token, HttpStatus.BAD_REQUEST);
		doReturn(ct).when(userAuthorizationClient).createAuthenticationToken(jwtRequestVO);
		mockMvc.perform(post("/login").flashAttr("JwtRequestVO", jwtRequestVO)).andExpect(status().isOk())
				.andExpect(forwardedUrl("/views/loginForm2.jsp"));
		verify(userAuthorizationClient).createAuthenticationToken(jwtRequestVO);
		// assert
	}

	@Test
	void testLogout() throws Exception {
		mockMvc.perform(get("/logout")).andExpect(status().isOk()).andExpect(forwardedUrl("/views/loginForm1.jsp"));
	}

}
