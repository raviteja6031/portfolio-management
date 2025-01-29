package com.customer.portal.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.customer.portal.client.MutualFundDetailsClient;
import com.customer.portal.client.NetworthClient;
import com.customer.portal.client.PortfolioClient;
import com.customer.portal.client.ShareDetailsClient;
import com.customer.portal.client.UserAuthorizationClient;
import com.customer.portal.service.CustomerPortalService;
import com.customer.portal.vo.AssetSaleResponse;
import com.customer.portal.vo.MutualFundDetails;
import com.customer.portal.vo.Portfolio;
import com.customer.portal.vo.SellingDetails;
import com.customer.portal.vo.ShareDetails;

import org.hamcrest.Matchers;

//@AutoConfigureWebMvc
@WebMvcTest(HomeController.class)
class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;// autowired in junit 5

	@MockBean
	UserAuthorizationClient userAuthorizationClient;

	@MockBean
	NetworthClient networthClient;

	@MockBean
	MutualFundDetailsClient mutualFundDetailsClient;

	@MockBean
	ShareDetailsClient shareDetailsClient;

	@MockBean
	@Autowired
	CustomerPortalService customerPortalService;

//	@Mock
//	HttpSession session;

	@MockBean
	PortfolioClient portfolioClient;

	@MockBean
	Portfolio portfolio;

	@Test
	void testWelcomeNegative() throws Exception {
//		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
//		sessionattr.put("token", null);
//		String token = null;
//		Object obj = null;
//		session.setAttribute("token", token);
		// when(session.getAttribute("token")).thenReturn(obj);
		mockMvc.perform(get("/welcome")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/loginForm"));
	}

	@Test
	void testWelcomePositive() throws Exception {
		String token = "fqiodwclwcnsk";
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("token", token);
		sessionattr.put("uid", 101);

		Portfolio portfolio = new Portfolio();
		portfolio.setPortfolioId(101);
		when(portfolioClient.getPortfolioById(token, 101))
				.thenReturn(new ResponseEntity<Portfolio>(portfolio, HttpStatus.OK));

		when(networthClient.calculateNetworth(token, portfolio)).thenReturn(500.84);

		List<ShareDetails> sds = new ArrayList<>();
		when(shareDetailsClient.getByShareDetailsByPortfolioId(token, portfolio))
				.thenReturn(new ResponseEntity<List<ShareDetails>>(sds, HttpStatus.OK));
		List<MutualFundDetails> mds = new ArrayList<>();
		when(mutualFundDetailsClient.getByMutualFundDetailsByPortfolioId(token, portfolio))
				.thenReturn(new ResponseEntity<List<MutualFundDetails>>(mds, HttpStatus.OK));

		mockMvc.perform(get("/welcome").sessionAttrs(sessionattr)).andExpect(status().isOk())
				.andExpect(content().string("")).andExpect(forwardedUrl("/views/welcome.jsp"));
	}

	private ResultMatcher forwadedUrl(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
	void testSellAssetForm() throws Exception {
		String token = "fqiodwclwcnsk";
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("token", token);
		sessionattr.put("uid", 101);
		
		Portfolio portfolio = new Portfolio();
		portfolio.setPortfolioId(101);
		when(portfolioClient.getPortfolioById(token, 101))
				.thenReturn(new ResponseEntity<Portfolio>(portfolio, HttpStatus.OK));

		List<ShareDetails> sds = new ArrayList<>();
		when(shareDetailsClient.getByShareDetailsByPortfolioId(token, portfolio))
				.thenReturn(new ResponseEntity<List<ShareDetails>>(sds, HttpStatus.OK));
		List<MutualFundDetails> mds = new ArrayList<>();
		when(mutualFundDetailsClient.getByMutualFundDetailsByPortfolioId(token, portfolio))
		.thenReturn(new ResponseEntity<List<MutualFundDetails>>(mds, HttpStatus.OK));
		
		mockMvc.perform(get("/sellAssets").sessionAttrs(sessionattr)).andExpect(status().isOk()).andExpect(forwardedUrl("/views/sellAssetForm.jsp"));
	}
	

	@Test
	void testSellAssetFunction() throws Exception {
		String token = "fqiodwclwcnsk";
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("token", token);
		sessionattr.put("uid", 101);
		
		Portfolio portfolio = new Portfolio();
		portfolio.setPortfolioId(101);
		when(portfolioClient.getPortfolioById(token, 101))
				.thenReturn(new ResponseEntity<Portfolio>(portfolio, HttpStatus.OK));
		

		List<ShareDetails> sds = new ArrayList<>();
		when(shareDetailsClient.getByShareDetailsByPortfolioId(token, portfolio))
				.thenReturn(new ResponseEntity<List<ShareDetails>>(sds, HttpStatus.OK));
		List<MutualFundDetails> mds = new ArrayList<>();
		when(mutualFundDetailsClient.getByMutualFundDetailsByPortfolioId(token, portfolio))
		.thenReturn(new ResponseEntity<List<MutualFundDetails>>(mds, HttpStatus.OK));
		
		SellingDetails sellingDetails = new SellingDetails(101, null, null);
		
		when(networthClient.sellAssets(token, sellingDetails)).thenReturn(new AssetSaleResponse(true,500.08));
		
		String[] name = {"DLF"};
		String[] count = {"6"};
				
		mockMvc.perform(post("/soldAssets").sessionAttrs(sessionattr).param("selected", name).param("quantity", count)).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/welcome"));
	}

}

//HashMap<String, Object> sessionattr = new HashMap<String, Object>();
//sessionattr.put("token", null);
//Object obj = null;
//session.setAttribute("token", token);
//when(session.getAttribute("token")).thenReturn(obj);