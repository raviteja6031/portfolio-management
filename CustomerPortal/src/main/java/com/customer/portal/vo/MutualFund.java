package com.customer.portal.vo;

public class MutualFund {

	private int mutualFundId;
	private double mutualFundValue;
	private String mutualFundName;

	public MutualFund() {
		super();
	}

	public MutualFund(int mutualFundId, String mutualFundName, double mutualFundValue) {
		super();
		this.mutualFundId = mutualFundId;
		this.mutualFundName = mutualFundName;
		this.mutualFundValue = mutualFundValue;
	}

	@Override
	public String toString() {
		return "MutualFund [mutualFundId=" + mutualFundId + ", mutualFundName=" + mutualFundName + ", mutualFundValue="
				+ mutualFundValue + "]";
	}

	public int getMutualFundId() {
		return mutualFundId;
	}

	public void setMutualFundId(int mutualFundId) {
		this.mutualFundId = mutualFundId;
	}

	public String getMutualFundName() {
		return mutualFundName;
	}

	public void setMutualFundName(String mutualFundName) {
		this.mutualFundName = mutualFundName;
	}

	public double getMutualFundValue() {
		return mutualFundValue;
	}

	public void setMutualFundValue(double mutualFundValue) {
		this.mutualFundValue = mutualFundValue;
	}

	public MutualFund(String mutualFundName, double mutualFundValue) {
		super();
		this.mutualFundName = mutualFundName;
		this.mutualFundValue = mutualFundValue;
	}
}
