package com.networth.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.networth.client.MutualFundClient;
import com.networth.client.MutualFundDetailsClient;
import com.networth.client.ShareClient;
import com.networth.client.ShareDetailsClient;
import com.networth.client.UserAuthorizationClient;
import com.networth.exceptions.MutualFundWrongDetail;
import com.networth.exceptions.WrongShareDetailsException;
import com.networth.model.MutualFundDetailsDummy;
import com.networth.model.SellingDetails;
import com.networth.model.ShareDetailsDummy;
import com.networth.vo.MutualFund;
import com.networth.vo.MutualFundDetails;
import com.networth.vo.Portfolio;
import com.networth.vo.Share;
import com.networth.vo.ShareDetails;

@Service
public class CalculateNetWorthService {

	@Autowired
	ShareClient shareClient;

	@Autowired
	MutualFundClient mutualFundClient;

	@Autowired
	UserAuthorizationClient userAuthorizationClient;

	@Autowired
	ShareDetailsClient shareDetailsClient;

	@Autowired
	MutualFundDetailsClient mutualFundDetailsClient;

	private static Logger logger = LoggerFactory.getLogger(CalculateNetWorthService.class);

	public double calculateNetworth(String authorization, List<ShareDetails> shareDetailsList,
			List<MutualFundDetails> mutualFundDetailsList)
			throws WrongShareDetailsException, NullPointerException, MutualFundWrongDetail {
		if (!userAuthorizationClient.validateToken(authorization)) {
			logger.info("AUTHORIZATION FAILED!!\nCLASS: CalculateNetWorthService : calculateNetworth");
			return 0.0;
		} else {
			logger.info("AUTHORIZATION SUCCESS FOR CalculateNetWorthService: calculateNetworth");
			double networth = 0;
			for (ShareDetails count : shareDetailsList) {
				Share share = shareClient.dailySharePriceByName(authorization, count.getShareName()).getBody();
				if (share == null) {
					throw new NullPointerException();
				}
				networth = networth + (count.getCount() * share.getShareValue());
				logger.info("CalculateNetWorthService: calculateNetworth -> Share Detail calcn SUCCESS");
			}
			for (MutualFundDetails count : mutualFundDetailsList) {
				MutualFund mutualFund = mutualFundClient.getByMutualFundNav(authorization, count.getMutualFundName())
						.getBody();
				if (mutualFund == null) {
					throw new NullPointerException();
				}
				networth = networth + (count.getCount() * mutualFund.getMutualFundValue());
				logger.info("CalculateNetWorthService: calculateNetworth -> Mutual Fund Detail calcn SUCCESS");
			}
			return networth;
		}
	}

	public boolean sellShares(String authorization, SellingDetails sellingDetails, Portfolio portfolio,
			List<ShareDetails> shareDetailsForParticularPid) throws NullPointerException {
		boolean shareFlag = false;
		for (ShareDetails s : shareDetailsForParticularPid) {
			if (sellingDetails.getSellingShares().containsKey(s.getShareName())) {
				ShareDetails sUpdated = s;
				sUpdated.setCount(s.getCount() - sellingDetails.getSellingShares().get(s.getShareName()));
				if (sUpdated.getCount() == 0) {
					shareDetailsClient.deleteByPortfolio(authorization, s.getShareDetailsId());
					shareFlag = true;
				} else {
					ShareDetailsDummy shareDetailsDummy = new ShareDetailsDummy();
					shareDetailsDummy.setCount(sUpdated.getCount());
					shareDetailsDummy.setPortfolio(portfolio);
					shareDetailsDummy.setShareDetailsId(sUpdated.getShareDetailsId());
					shareDetailsDummy.setShareName(sUpdated.getShareName());
					logger.info(shareDetailsDummy.toString());
					shareDetailsClient.update(authorization, shareDetailsDummy);
					shareFlag = true;
				}
			}
		}
		return shareFlag;
	}

	public boolean sellMutualFunds(String authorization, SellingDetails sellingDetails, Portfolio portfolio,
			List<MutualFundDetails> mutualFundDetailsForParticularPid) {
		boolean mutualFundFlag = false;
		for (MutualFundDetails m : mutualFundDetailsForParticularPid) {
			if (sellingDetails.getSellingMutualFunds().containsKey(m.getMutualFundName())) {
				MutualFundDetails mUpdated = m;
				logger.info(mUpdated.toString());
				mUpdated.setCount(m.getCount() - sellingDetails.getSellingMutualFunds().get(m.getMutualFundName()));
				if (mUpdated.getCount() == 0) {
					logger.info(Integer.toString(m.getMfDetailsId()));
					mutualFundDetailsClient.deleteByMutualFundDetailId(authorization, m.getMfDetailsId());
					mutualFundFlag = true;
				} else {
					MutualFundDetailsDummy mutualFundDetailsDummy = new MutualFundDetailsDummy();
					mutualFundDetailsDummy.setCount(mUpdated.getCount());
					logger.info(Integer.toString(mUpdated.getMfDetailsId()));
					mutualFundDetailsDummy.setMfDetailsId(mUpdated.getMfDetailsId());
					logger.info(Integer.toString(mutualFundDetailsDummy.getMfDetailsId()));
					mutualFundDetailsDummy.setMutualFundName(mUpdated.getMutualFundName());
					mutualFundDetailsDummy.setPortfolio(portfolio);
					mutualFundDetailsClient.update(authorization, mutualFundDetailsDummy);
					mutualFundFlag = true;
				}
			}
		}
		return mutualFundFlag;
	}

}
