package com.networth.vo;

import java.util.Objects;

public class MutualFundDetails {

	private int mfDetailsId;
	private String mutualFundName;
	private int count;

	public int getMfDetailsId() {
		return mfDetailsId;
	}

	public void setMfDetailsId(int mfDetailsId) {
		this.mfDetailsId = mfDetailsId;
	}

	public MutualFundDetails() {
		super();
	}

	public MutualFundDetails(int mfDetailsId, String mutualFundName, int count) {
		super();
		this.mfDetailsId = mfDetailsId;
		this.mutualFundName = mutualFundName;
		this.count = count;
	}

	@Override
	public String toString() {
		return "MutualFundDetails [mfDetailsId=" + mfDetailsId + ", mutualFundName=" + mutualFundName + ", count="
				+ count + "]";
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

	@Override
	public int hashCode() {
		return Objects.hash(count, mfDetailsId, mutualFundName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MutualFundDetails other = (MutualFundDetails) obj;
		return count == other.count && mfDetailsId == other.mfDetailsId
				&& Objects.equals(mutualFundName, other.mutualFundName);
	}

}
