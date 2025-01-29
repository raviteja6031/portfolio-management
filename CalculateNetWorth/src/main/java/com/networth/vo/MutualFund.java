package com.networth.vo;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(mutualFundId, mutualFundName, mutualFundValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MutualFund other = (MutualFund) obj;
		return mutualFundId == other.mutualFundId && Objects.equals(mutualFundName, other.mutualFundName)
				&& Double.doubleToLongBits(mutualFundValue) == Double.doubleToLongBits(other.mutualFundValue);
	}
}
