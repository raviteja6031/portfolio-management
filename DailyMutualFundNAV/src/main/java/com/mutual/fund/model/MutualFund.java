package com.mutual.fund.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "mutual_fund")
public class MutualFund {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mutual_fund_id")
	private int mutualFundId;
	@Column(name = "mutual_fund_name", unique = true, nullable = false)
	private String mutualFundName;
	@Column(name = "mutual_fund_value", unique = true, nullable = false)
	private double mutualFundValue;

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
