package com.networth.model;

import java.util.Objects;

import com.networth.vo.Portfolio;

public class MutualFundDetailsDummy {

	@Override
	public int hashCode() {
		return Objects.hash(count, mfDetailsId, mutualFundName, portfolio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MutualFundDetailsDummy)) {
			return false;
		}
		MutualFundDetailsDummy other = (MutualFundDetailsDummy) obj;
		return count == other.count && mfDetailsId == other.mfDetailsId
				&& Objects.equals(mutualFundName, other.mutualFundName) && Objects.equals(portfolio, other.portfolio);
	}

	private int mfDetailsId;
	private Portfolio portfolio;
	private String mutualFundName;
	private int count;

	public MutualFundDetailsDummy() {
		super();
	}

	@Override
	public String toString() {
		return "MutualFundDetailsDummy [mfDetailsId=" + mfDetailsId + ", portfolio=" + portfolio + ", mutualFundName="
				+ mutualFundName + ", count=" + count + "]";
	}

	public MutualFundDetailsDummy(int mfDetailsId, Portfolio portfolio, String mutualFundName, int count) {
		super();
		this.mfDetailsId = mfDetailsId;
		this.portfolio = portfolio;
		this.mutualFundName = mutualFundName;
		this.count = count;
	}

	public int getMfDetailsId() {
		return mfDetailsId;
	}

	public void setMfDetailsId(int mfDetailsId) {
		this.mfDetailsId = mfDetailsId;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public String getMutualFundName() {
		return mutualFundName;
	}

	public void setMutualFundName(String mutualFundName) {
		this.mutualFundName = mutualFundName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}