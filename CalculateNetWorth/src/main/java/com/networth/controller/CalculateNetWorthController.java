package com.networth.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.networth.client.*;
import com.networth.exceptions.MutualFundWrongDetail;
import com.networth.exceptions.WrongShareDetailsException;
import com.networth.model.AssetSaleResponse;
import com.networth.model.SellingDetails;
import com.networth.service.CalculateNetWorthService;
import com.networth.vo.*;

@RestController
@RequestMapping
public class CalculateNetWorthController {

	@Autowired
	UserAuthorizationClient userAuthorizationClient;

	@Autowired
	CalculateNetWorthService calculateNetWorthService;

	@Autowired
	PortfolioClient portfolioClient;

	@Autowired
	ShareDetailsClient shareDetailsClient;

	@Autowired
	MutualFundDetailsClient mutualFundDetailsClient;

	private static Logger logger = LoggerFactory.getLogger(CalculateNetWorthController.class);

	@PostMapping("/calculateNetworth")
	public double calculateNetworth(@RequestHeader("Authorization") String authorization,
			@RequestBody Portfolio portfolio)
			throws NullPointerException, WrongShareDetailsException, MutualFundWrongDetail {
		logger.info(authorization);
		logger.info(portfolio.toString());
		if (!userAuthorizationClient.validateToken(authorization)) {
			logger.info("AUTHORIZATION FAILED!!\nCLASS: CalculateNetWorthController : calculateNetworth");
			throw new NullPointerException();
		} else {
			List<ShareDetails> shareDetails = shareDetailsClient
					.getByShareDetailsByPortfolioId(authorization, portfolio).getBody();
			List<MutualFundDetails> mutualFundDetails = mutualFundDetailsClient
					.getByMutualFundDetailsByPortfolioId(authorization, portfolio).getBody();
			logger.info(authorization);
			logger.info(shareDetails.toString());
			logger.info(mutualFundDetails.toString());
			return (double) Math.round(
					calculateNetWorthService.calculateNetworth(authorization, shareDetails, mutualFundDetails) * 100)
					/ 100;
		}
	}

	@PostMapping("/sellAssets")
	public AssetSaleResponse sellAssets(@RequestHeader("Authorization") String authorization,
			@RequestBody SellingDetails sellingDetails)
			throws NullPointerException, WrongShareDetailsException, MutualFundWrongDetail {
		if (!userAuthorizationClient.validateToken(authorization)) {
			logger.info("AUTHORIZATION FAILED!!\nCLASS: CalculateNetWorthController : calculateNetworth");
			throw new NullPointerException();
		} else {
			Portfolio portfolio = portfolioClient.getPortfolioById(authorization, sellingDetails.getPortfolioId())
					.getBody();
			List<ShareDetails> shareDetailsForParticularPid = shareDetailsClient
					.getByShareDetailsByPortfolioId(authorization, portfolio).getBody();
			List<MutualFundDetails> mutualFundDetailsForParticularPid = mutualFundDetailsClient
					.getByMutualFundDetailsByPortfolioId(authorization, portfolio).getBody();
			logger.info(mutualFundDetailsForParticularPid.toString());

			AssetSaleResponse assetSaleResponse = new AssetSaleResponse();
			boolean shareFlag = false;
			boolean mutualFundFlag = false;

			shareFlag = calculateNetWorthService.sellShares(authorization, sellingDetails, portfolio, shareDetailsForParticularPid);
			mutualFundFlag = calculateNetWorthService.sellMutualFunds(authorization, sellingDetails, portfolio, mutualFundDetailsForParticularPid);

			if (shareFlag && mutualFundFlag) {
				assetSaleResponse.setSaleResponseStatus(true);
				assetSaleResponse.setBalance(calculateNetworth(authorization, portfolio));
			}
			return assetSaleResponse;
		}
	}
}

//{
//    "portfolioId": 101,
//    "shareDetails": [
//        {
//            "shareName": "DLF",
//            "count": 6
//        },
//        {
//            "shareName": "Reliance",
//            "count": 2
//        },
//        {
//            "shareName": "Zee Entertain",
//            "count": 8
//        }
//    ],
//    "mutualFundDetails": [
//        {
//            "mutualFundName": "ICICI Prudential Technology Fund - Direct Plan - Growth",
//            "count": 5
//        },
//        {
//            "mutualFundName": "DSP Government Securities Fund - Direct Plan - Growth",
//            "count": 20
//        }
//    ]
//}