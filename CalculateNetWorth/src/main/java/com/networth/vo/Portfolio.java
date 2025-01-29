package com.networth.vo;

import java.util.List;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(mutualFundDetails, portfolioId, shareDetails);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Portfolio other = (Portfolio) obj;
		return Objects.equals(mutualFundDetails, other.mutualFundDetails) && portfolioId == other.portfolioId
				&& Objects.equals(shareDetails, other.shareDetails);
	}

}
