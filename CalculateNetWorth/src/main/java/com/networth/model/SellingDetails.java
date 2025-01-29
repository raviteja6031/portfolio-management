package com.networth.model;

import java.util.Map;
import java.util.Objects;

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

//	@Override
//	public int hashCode() {
//		return Objects.hash(portfolioId, sellingMutualFunds, sellingShares);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		SellingDetails other = (SellingDetails) obj;
//		return portfolioId == other.portfolioId && Objects.equals(sellingMutualFunds, other.sellingMutualFunds)
//				&& Objects.equals(sellingShares, other.sellingShares);
//	}

}
