package com.portfolio.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="share_details")
public class ShareDetails {

	@Id
	@Column(name = "share_details_id", nullable = false)
	private int shareDetailsId;
	@ManyToOne
	@JoinColumn(name = "portfolio_id")
	private Portfolio portfolio;
	@Column(name = "share_name")
	private String shareName;
	@Column(name = "count")
	private int count;

	@Override
	public String toString() {
		return "ShareDetails [shareName=" + shareName + ", count=" + count + "]";
	}

	public ShareDetails() {
		super();
	}

	public ShareDetails(String shareName, int count) {
		super();
		this.shareName = shareName;
		this.count = count;
	}

	public ShareDetails(int shareDetailsId, Portfolio portfolio, String shareName, int count) {
		super();
		this.shareDetailsId = shareDetailsId;
		this.portfolio = portfolio;
		this.shareName = shareName;
		this.count = count;
	}

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getShareDetailsId() {
		return shareDetailsId;
	}

	public void setShareDetailsId(int shareDetailsId) {
		this.shareDetailsId = shareDetailsId;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(count, portfolio, shareDetailsId, shareName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShareDetails other = (ShareDetails) obj;
		return count == other.count && Objects.equals(portfolio, other.portfolio)
				&& shareDetailsId == other.shareDetailsId && Objects.equals(shareName, other.shareName);
	}

}

//public Portfolio getPortfolio() {	return portfolio; }
