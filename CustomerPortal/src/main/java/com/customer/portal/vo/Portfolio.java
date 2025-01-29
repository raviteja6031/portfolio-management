package com.customer.portal.vo;

import java.util.List;

public class Portfolio {

	private int portfolioId;
	private List<ShareDetails> shareDetails;
	private List<MutualFundDetails> mutualFundDetails;

	@Override
	public String toString() {
		return "Portfolio [portfolioId=" + portfolioId + ", shareDetails=" + shareDetails + ", mutualFundDetails="
				+ mutualFundDetails + "]";
	}

	public Portfolio() {
		super();
	}

	public Portfolio(int portfolioId, List<ShareDetails> shareDetails, List<MutualFundDetails> mutualFundDetails) {
		super();
		this.portfolioId = portfolioId;
		this.shareDetails = shareDetails;
		this.mutualFundDetails = mutualFundDetails;
	}

	public int getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public List<ShareDetails> getshareDetails() {
		return shareDetails;
	}

	public void setshareDetails(List<ShareDetails> shareDetails) {
		this.shareDetails = shareDetails;
	}

	public List<MutualFundDetails> getMutualFundDetails() {
		return mutualFundDetails;
	}

	public void setMutualFundDetails(List<MutualFundDetails> mutualFundDetails) {
		this.mutualFundDetails = mutualFundDetails;
	}

}
