package com.portfolio.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mutual_fund_details")
public class MutualFundDetails {

	@Id
	@Column(name = "mf_details_id", nullable = false)
	private int mfDetailsId;
	@ManyToOne
	@JoinColumn(name = "portfolio_id")
	private Portfolio portfolio;
	@Column(name = "mutual_fund_name")
	private String mutualFundName;
	@Column(name = "count")
	private int count;

	@Override
	public String toString() {
		return "MutualFundDetails [mfDetailsId=" + mfDetailsId + ", mutualFundName=" + mutualFundName + ", count="
				+ count + "]";
	}

	public int getMfDetailsId() {
		return mfDetailsId;
	}

	public void setMfDetailsId(int mfDetailsId) {
		this.mfDetailsId = mfDetailsId;
	}

	public MutualFundDetails(int mfDetailsId, Portfolio portfolio, String mutualFundName, int count) {
		super();
		this.mfDetailsId = mfDetailsId;
		this.portfolio = portfolio;
		this.mutualFundName = mutualFundName;
		this.count = count;
	}

	public MutualFundDetails() {
		super();
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

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
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
