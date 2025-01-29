package com.customer.portal.controller;

import javax.servlet.http.HttpSession;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.customer.portal.client.MutualFundDetailsClient;
import com.customer.portal.client.NetworthClient;
import com.customer.portal.client.PortfolioClient;
import com.customer.portal.client.ShareDetailsClient;
import com.customer.portal.client.UserAuthorizationClient;
import com.customer.portal.exceptions.MutualFundWrongDetail;
import com.customer.portal.exceptions.WrongShareDetailsException;
import com.customer.portal.service.CustomerPortalService;
import com.customer.portal.vo.MutualFundDetails;
import com.customer.portal.vo.Portfolio;
import com.customer.portal.vo.SellingDetails;
import com.customer.portal.vo.ShareDetails;

import java.util.HashMap;
import java.util.List;

@Controller
public class HomeController {

	@Autowired
	PortfolioClient portfolioClient;

	@Autowired
	NetworthClient networthClient;

	@Autowired
	ShareDetailsClient shareDetailsClient;

	@Autowired
	MutualFundDetailsClient mutualFundDetailsClient;

	@Autowired
	CustomerPortalService customerPortalService;

	@Autowired
	UserAuthorizationClient userAuthorizationClient;

	private static Logger logger = LoggerFactory.getLogger(HomeController.class);

	@GetMapping("/welcome")
	public String welcome(ModelMap map, HttpSession session)
			throws NullPointerException, WrongShareDetailsException, MutualFundWrongDetail {
		logger.info("Inside WELCOME");

		if (session.getAttribute("token") == null) {
			return "redirect:/loginForm";
		} else {
			String tokener = session.getAttribute("token").toString();
			Portfolio portfolio = portfolioClient.getPortfolioById(tokener, (int) session.getAttribute("uid"))
					.getBody();
			double networth = networthClient.calculateNetworth(tokener, portfolio);
			map.addAttribute("networth", networth);
			logger.info(Double.toString(networth));

			List<ShareDetails> shareDetails = shareDetailsClient.getByShareDetailsByPortfolioId(tokener, portfolio)
					.getBody();
			map.addAttribute("shareDetails", shareDetails);

			List<MutualFundDetails> mutualFundDetails = mutualFundDetailsClient
					.getByMutualFundDetailsByPortfolioId(tokener, portfolio).getBody();
			map.addAttribute("mutualFundDetails", mutualFundDetails);

			return "welcome";
		}
	}

	@GetMapping("/sellAssets")
	public String sellAssetForm(HttpSession session, ModelMap map) {
		if (session.getAttribute("token") == null) {
			return "redirect:/loginForm";
		} else {
			String tokener = session.getAttribute("token").toString();
			logger.info("Inside sellAssetForm");
			// String tokener = session.getAttribute("token").toString();
			Portfolio portfolio = portfolioClient.getPortfolioById(tokener, (int) session.getAttribute("uid"))
					.getBody();
			List<ShareDetails> shareDetails = shareDetailsClient.getByShareDetailsByPortfolioId(tokener, portfolio)
					.getBody();
			map.addAttribute("shareDetails", shareDetails);

			List<MutualFundDetails> mutualFundDetails = mutualFundDetailsClient
					.getByMutualFundDetailsByPortfolioId(tokener, portfolio).getBody();
			map.addAttribute("mutualFundDetails", mutualFundDetails);
			logger.info("Out of sellAssetForm");
			return "sellAssetForm";
		}
	}

	@PostMapping("/soldAssets")
	public String soldAssets(HttpSession session, @RequestParam("selected") String[] name,
			@RequestParam("quantity") String[] count, ModelMap model)
			throws NullPointerException, WrongShareDetailsException, MutualFundWrongDetail {
		logger.info("Inside sellAssetForm's Logic Function Call -> soldAssets");
		String tokener = session.getAttribute("token").toString();
		Portfolio portfolio = portfolioClient.getPortfolioById(tokener, (int) session.getAttribute("uid")).getBody();
		List<ShareDetails> shareDetails = shareDetailsClient.getByShareDetailsByPortfolioId(tokener, portfolio)
				.getBody();
		List<MutualFundDetails> mutualFundDetails = mutualFundDetailsClient
				.getByMutualFundDetailsByPortfolioId(tokener, portfolio).getBody();

		Map<String, String> assetMap = customerPortalService.convertToMap(name, count);
		logger.info(assetMap.toString());
		logger.info(assetMap.get("DLF"));

		Map<String, Integer> sellingShares = new HashMap<>();
		Map<String, Integer> sellingMutualFunds = new HashMap<>();

		for (ShareDetails s : shareDetails) {
			if (assetMap.containsKey(s.getShareName())) {
				sellingShares.put(s.getShareName(), Integer.parseInt(assetMap.get(s.getShareName())));
			}
		}

		for (MutualFundDetails m : mutualFundDetails) {
			if (assetMap.containsKey(m.getMutualFundName())) {
				sellingMutualFunds.put(m.getMutualFundName(), Integer.parseInt(assetMap.get(m.getMutualFundName())));
			}
		}

		SellingDetails sellingDetails = new SellingDetails((int) session.getAttribute("uid"), sellingShares,
				sellingMutualFunds);

		networthClient.sellAssets(tokener, sellingDetails);

		logger.info("Out of sellAssetForm's Logic Function Call -> soldAssets");

		return "redirect:/welcome";
	}

}
