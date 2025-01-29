package com.networth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.networth.client.MutualFundClient;
import com.networth.client.MutualFundDetailsClient;
import com.networth.client.ShareClient;
import com.networth.client.ShareDetailsClient;
import com.networth.client.UserAuthorizationClient;
import com.networth.exceptions.WrongShareDetailsException;
import com.networth.model.MutualFundDetailsDummy;
import com.networth.model.SellingDetails;
import com.networth.model.ShareDetailsDummy;
import com.networth.vo.MutualFund;
import com.networth.vo.MutualFundDetails;
import com.networth.vo.Portfolio;
import com.networth.vo.Share;
import com.networth.vo.ShareDetails;

@ExtendWith(MockitoExtension.class)
class CalculateNetWorthServiceTest {

	@InjectMocks
	private CalculateNetWorthService calculateNetWorthService;

	@Autowired
	@Mock
	private ShareClient shareClient;

	@Autowired
	@Mock
	private MutualFundClient mutualFundClient;

	@Mock
	UserAuthorizationClient userAuthorizationClient;

	@Mock
	ShareDetailsClient shareDetailsClient;

	@Mock
	MutualFundDetailsClient mutualFundDetailsClient;

	@Mock
	Share share;

	@Mock
	MutualFund mutualFund;

	@Mock
	ShareDetails shareDetails;

	@Mock
	SellingDetails sellingDetails;

	@Mock
	Portfolio portfolio;

	@Mock
	ShareDetailsDummy shareDetailsDummy;

	@Test
	void testCalculateNetworth() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		when(userAuthorizationClient.validateToken(token)).thenReturn(true);
		List<ShareDetails> shareDetailsList = new ArrayList<ShareDetails>();
		shareDetailsList.add(new ShareDetails(301, "DLF", 6));
		List<MutualFundDetails> mutualFundDetailsList = new ArrayList<MutualFundDetails>();
		mutualFundDetailsList.add(new MutualFundDetails(201, "Nippon", 5));
		Share share = new Share(201, "DLF", 210.79);

		MutualFund mutualFund = new MutualFund("Nippon", 500.00);
		when(shareClient.dailySharePriceByName(token, "DLF"))
				.thenReturn(new ResponseEntity<Share>(share, HttpStatus.OK));
		when(mutualFundClient.getByMutualFundNav(token, "Nippon"))
				.thenReturn(new ResponseEntity<MutualFund>(mutualFund, HttpStatus.OK));
		calculateNetWorthService.calculateNetworth(token, shareDetailsList, mutualFundDetailsList);
		for (ShareDetails s : shareDetailsList) {
			verify(shareClient, times(shareDetailsList.size())).dailySharePriceByName(token, s.getShareName());
		}
		for (MutualFundDetails m : mutualFundDetailsList) {
			verify(mutualFundClient, times(mutualFundDetailsList.size())).getByMutualFundNav(token,
					m.getMutualFundName());
		}
	}

	@Test
	void testSellAssets() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		// SellingDetails sellingDetails =
		// org.mockito.Mockito.mock(SellingDetails.class);
		SellingDetails sellingDetails = new SellingDetails();
		sellingDetails.setPortfolioId(101);

		Map<String, Integer> sellingShares = new HashMap<>();
		sellingShares.put("DLF", 6);

		Map<String, Integer> mdShares = new HashMap<>();
		mdShares.put("Nippon", 10);

		sellingDetails.setSellingMutualFunds(mdShares);
		sellingDetails.setSellingShares(sellingShares);

		Portfolio portfolio = mock(Portfolio.class);
		portfolio.setPortfolioId(101);

		List<ShareDetails> shareDetailsForParticularPid = new ArrayList<>();
		shareDetailsForParticularPid.add(new ShareDetails(301, "DLF", 6));

		calculateNetWorthService.sellShares(token, sellingDetails, portfolio, shareDetailsForParticularPid);

		for (ShareDetails s : shareDetailsForParticularPid) {
			if (sellingDetails.getSellingShares().containsKey(s.getShareName())) {
				ShareDetails sUpdated = org.mockito.Mockito.mock(ShareDetails.class);
				sUpdated = s;
				sUpdated.setCount(s.getCount() - sellingDetails.getSellingShares().get(s.getShareName()));
				if (sUpdated.getCount() == 0) {
					verify(shareDetailsClient, times(1)).deleteByPortfolio(token, s.getShareDetailsId());
				}
			}
		}
	}

	@Test
	void testSellAssets1() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		// SellingDetails sellingDetails =
		// org.mockito.Mockito.mock(SellingDetails.class);
		SellingDetails sellingDetails = new SellingDetails();
		sellingDetails.setPortfolioId(101);

		Map<String, Integer> sellingShares = new HashMap<>();
		sellingShares.put("DLF", 7);

		Map<String, Integer> mdShares = new HashMap<>();
		mdShares.put("Nippon", 10);

		sellingDetails.setSellingMutualFunds(mdShares);
		sellingDetails.setSellingShares(sellingShares);
		System.out.println(sellingDetails);
		Portfolio portfolio = mock(Portfolio.class);
		portfolio.setPortfolioId(101);

		List<ShareDetails> shareDetailsForParticularPid = new ArrayList<>();
		shareDetailsForParticularPid.add(new ShareDetails(301, "DLF", 12));
		System.out.println(shareDetailsForParticularPid);
		calculateNetWorthService.sellShares(token, sellingDetails, portfolio, shareDetailsForParticularPid);
		for (ShareDetails s : shareDetailsForParticularPid) {
			System.out.println("S initially:"+s);
			if (sellingDetails.getSellingShares().containsKey(s.getShareName())) {
				System.out.println("map waala share:"+sellingDetails.getSellingShares());
				System.out.println("iterating object:"+s.getShareName());
				// ShareDetails sUpdated = org.mockito.Mockito.mock(ShareDetails.class);
				ShareDetails sUpdated = s;
				System.out.println("SUPDATED:"+sUpdated);
				sUpdated.setCount(s.getCount() - sellingDetails.getSellingShares().get(s.getShareName()));
			//	System.out.println(s);
				if (sUpdated.getCount() != 0) {
					ShareDetailsDummy shareDetailsDummy = new ShareDetailsDummy();
					shareDetailsDummy.setCount(sUpdated.getCount()+sellingDetails.getSellingShares().get(s.getShareName()));
					shareDetailsDummy.setPortfolio(portfolio);
					shareDetailsDummy.setShareDetailsId(sUpdated.getShareDetailsId());
					shareDetailsDummy.setShareName(sUpdated.getShareName());
					verify(shareDetailsClient, times(1)).update(token, shareDetailsDummy);
					//	LOGIC : WHEN calculateNetworthService IS GETTING CALLED, IT IS CHANGING "S", SO WE HAVE TO ADD IT NOW
				}
			}
		}

	}

	@Test
	void testSellAssetsMutualFunds() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		// SellingDetails sellingDetails =
		// org.mockito.Mockito.mock(SellingDetails.class);
		SellingDetails sellingDetails = new SellingDetails();
		sellingDetails.setPortfolioId(101);

		Map<String, Integer> sellingShares = new HashMap<>();
		sellingShares.put("DLF", 6);

		Map<String, Integer> mdShares = new HashMap<>();
		mdShares.put("Nippon", 10);

		sellingDetails.setSellingMutualFunds(mdShares);
		sellingDetails.setSellingShares(sellingShares);

		Portfolio portfolio = mock(Portfolio.class);
		portfolio.setPortfolioId(101);

		List<MutualFundDetails> mutualFundDetailsForParticularPid = new ArrayList<>();
		mutualFundDetailsForParticularPid.add(new MutualFundDetails(301, "Nippon", 10));

		calculateNetWorthService.sellMutualFunds(token, sellingDetails, portfolio, mutualFundDetailsForParticularPid);

		for (MutualFundDetails m : mutualFundDetailsForParticularPid) {
			if (sellingDetails.getSellingMutualFunds().containsKey(m.getMutualFundName())) {
				MutualFundDetails mUpdated = org.mockito.Mockito.mock(MutualFundDetails.class);
				mUpdated = m;
				mUpdated.setCount(m.getCount() - sellingDetails.getSellingMutualFunds().get(m.getMutualFundName()));
				if (mUpdated.getCount() == 0) {
					verify(mutualFundDetailsClient, times(1)).deleteByMutualFundDetailId(token, m.getMfDetailsId());
				}
			}
		}
	}

	@Test
	void testSellAssetsMutualFunds2() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		// SellingDetails sellingDetails =
		// org.mockito.Mockito.mock(SellingDetails.class);
		SellingDetails sellingDetails = new SellingDetails();
		sellingDetails.setPortfolioId(101);

		Map<String, Integer> sellingShares = new HashMap<>();
		sellingShares.put("DLF", 4);

		Map<String, Integer> mdShares = new HashMap<>();
		mdShares.put("Nippon", 1);

		sellingDetails.setSellingMutualFunds(mdShares);
		sellingDetails.setSellingShares(sellingShares);

		Portfolio portfolio = mock(Portfolio.class);
		portfolio.setPortfolioId(101);

		List<MutualFundDetails> mutualFundDetailsForParticularPid = new ArrayList<>();
		mutualFundDetailsForParticularPid.add(new MutualFundDetails(301, "Nippon", 6));

		calculateNetWorthService.sellMutualFunds(token, sellingDetails, portfolio, mutualFundDetailsForParticularPid);

		for (MutualFundDetails m : mutualFundDetailsForParticularPid) {
			if (sellingDetails.getSellingMutualFunds().containsKey(m.getMutualFundName())) {
				MutualFundDetails mUpdated = org.mockito.Mockito.mock(MutualFundDetails.class);
				mUpdated = m;
				mUpdated.setCount(m.getCount() - sellingDetails.getSellingMutualFunds().get(m.getMutualFundName()));
				if (mUpdated.getCount() != 0) {
					MutualFundDetailsDummy mutualFundDetailsDummy = new MutualFundDetailsDummy();
					mutualFundDetailsDummy.setCount(mUpdated.getCount()+sellingDetails.getSellingMutualFunds().get(m.getMutualFundName()));
					mutualFundDetailsDummy.setPortfolio(portfolio);
					mutualFundDetailsDummy.setMfDetailsId(mUpdated.getMfDetailsId());
					mutualFundDetailsDummy.setMutualFundName(mUpdated.getMutualFundName());
					verify(mutualFundDetailsClient, times(1)).update(token, mutualFundDetailsDummy);
					//		LOGIC : WHEN calculateNetworthService IS GETTING CALLED, IT IS CHANGING "M", SO WE HAVE TO ADD IT NOW
				}
			}
		}

	}
	
	@Test
	void testCalculateNetworthNegative() throws Exception {
		String token = "eimeo";
		when(userAuthorizationClient.validateToken(token)).thenReturn(false);
		List<ShareDetails> shareDetailsList = new ArrayList<ShareDetails>();
		shareDetailsList.add(new ShareDetails(301, "DLF", 6));
		List<MutualFundDetails> mutualFundDetailsList = new ArrayList<MutualFundDetails>();
		mutualFundDetailsList.add(new MutualFundDetails(201, "Nippon", 5));
		assertEquals(calculateNetWorthService.calculateNetworth(token,shareDetailsList, mutualFundDetailsList), 0.0);
	}

	@Test
	void testSellAssetsNegative() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		// SellingDetails sellingDetails =
		// org.mockito.Mockito.mock(SellingDetails.class);
		SellingDetails sellingDetails = new SellingDetails();
		sellingDetails.setPortfolioId(101);

		Map<String, Integer> sellingShares = new HashMap<>();
		sellingShares.put("RELIANCE", 6);

		Map<String, Integer> mdShares = new HashMap<>();
		mdShares.put("Nippon", 10);

		sellingDetails.setSellingMutualFunds(mdShares);
		sellingDetails.setSellingShares(sellingShares);

		Portfolio portfolio = mock(Portfolio.class);
		portfolio.setPortfolioId(101);

		List<ShareDetails> shareDetailsForParticularPid = new ArrayList<>();
		shareDetailsForParticularPid.add(new ShareDetails(301, "DLF", 6));

		calculateNetWorthService.sellShares(token, sellingDetails, portfolio, shareDetailsForParticularPid);
		boolean shareFlag = false;
		for (ShareDetails s : shareDetailsForParticularPid) {
			if (sellingDetails.getSellingShares().containsKey(s.getShareName())) {
				ShareDetails sUpdated = org.mockito.Mockito.mock(ShareDetails.class);
				sUpdated = s;
				sUpdated.setCount(s.getCount() - sellingDetails.getSellingShares().get(s.getShareName()));
				if (sUpdated.getCount() == 0) {
					shareFlag = true;
					verify(shareDetailsClient, times(0)).deleteByPortfolio(token, s.getShareDetailsId());
				}
				else {
					shareFlag = true;
					ShareDetailsDummy shareDetailsDummy = new ShareDetailsDummy();
					shareDetailsDummy.setCount(sUpdated.getCount()+sellingDetails.getSellingShares().get(s.getShareName()));
					shareDetailsDummy.setPortfolio(portfolio);
					shareDetailsDummy.setShareDetailsId(sUpdated.getShareDetailsId());
					shareDetailsDummy.setShareName(sUpdated.getShareName());
					verify(shareDetailsClient, times(0)).update(token, shareDetailsDummy);
				}
			}
		}
		assertFalse(shareFlag);
	}
	
	@Test
	void testSellAssetsMutualFundsNegative() throws Exception {
		String token = "udeklwl12ddjwk22cj";
		// SellingDetails sellingDetails =
		// org.mockito.Mockito.mock(SellingDetails.class);
		SellingDetails sellingDetails = new SellingDetails();
		sellingDetails.setPortfolioId(101);

		Map<String, Integer> sellingShares = new HashMap<>();
		sellingShares.put("DLF", 6);

		Map<String, Integer> mdShares = new HashMap<>();
		mdShares.put("Nippon", 10);

		sellingDetails.setSellingMutualFunds(mdShares);
		sellingDetails.setSellingShares(sellingShares);

		Portfolio portfolio = mock(Portfolio.class);
		portfolio.setPortfolioId(101);

		List<MutualFundDetails> mutualFundDetailsForParticularPid = new ArrayList<>();
		mutualFundDetailsForParticularPid.add(new MutualFundDetails(301, "DLF", 6));

		calculateNetWorthService.sellMutualFunds(token, sellingDetails, portfolio, mutualFundDetailsForParticularPid);
		boolean mutualFundFlag = false;
		for (MutualFundDetails m : mutualFundDetailsForParticularPid) {
			if (sellingDetails.getSellingMutualFunds().containsKey(m.getMutualFundName())) {
				MutualFundDetails mUpdated = org.mockito.Mockito.mock(MutualFundDetails.class);
				mUpdated = m;
				mUpdated.setCount(m.getCount() - sellingDetails.getSellingMutualFunds().get(m.getMutualFundName()));
				if (mUpdated.getCount() == 0) {
					mutualFundFlag = true;
					verify(mutualFundDetailsClient, times(0)).deleteByMutualFundDetailId(token, m.getMfDetailsId());
				}
				else {
					mutualFundFlag = true;
					MutualFundDetailsDummy mutualFundDetailsDummy = new MutualFundDetailsDummy();
					mutualFundDetailsDummy.setCount(mUpdated.getCount()+sellingDetails.getSellingMutualFunds().get(m.getMutualFundName()));
					mutualFundDetailsDummy.setPortfolio(portfolio);
					mutualFundDetailsDummy.setMfDetailsId(mUpdated.getMfDetailsId());
					mutualFundDetailsDummy.setMutualFundName(mUpdated.getMutualFundName());
					verify(mutualFundDetailsClient, times(0)).update(token, mutualFundDetailsDummy);
				}
			}
		}
		assertFalse(mutualFundFlag);
	}

	
}
	