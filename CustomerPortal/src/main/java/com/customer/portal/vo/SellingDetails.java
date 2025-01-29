package com.customer.portal.vo;

import java.util.Map;

public class SellingDetails {

	private int portfolioId;
	private Map<String, Integer> sellingShares;
	private Map<String, Integer> sellingMutualFunds;

	@Override
	public String toString() {
		return "SellingDetails [portfolioId=" + portfolioId + ", sellingShares=" + sellingShares
				+ ", sellingMutualFunds=" + sellingMutualFunds + "]";
	}

	public SellingDetails() {
		super();
	}

	public SellingDetails(int portfolioId, Map<String, Integer> sellingShares,
			Map<String, Integer> sellingMutualFunds) {
		super();
		this.portfolioId = portfolioId;
		this.sellingShares = sellingShares;
		this.sellingMutualFunds = sellingMutualFunds;
	}

	public int getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public Map<String, Integer> getSellingShares() {
		return sellingShares;
	}

	public void setSellingShares(Map<String, Integer> sellingShares) {
		this.sellingShares = sellingShares;
	}

	public Map<String, Integer> getSellingMutualFunds() {
		return sellingMutualFunds;
	}

	public void setSellingMutualFunds(Map<String, Integer> sellingMutualFunds) {
		this.sellingMutualFunds = sellingMutualFunds;
	}

}
