package com.networth.model;

import java.util.Objects;

import com.networth.vo.Portfolio;

public class ShareDetailsDummy {

	@Override
	public int hashCode() {
		return Objects.hash(count, portfolio, shareDetailsId, shareName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ShareDetailsDummy)) {
			return false;
		}
		ShareDetailsDummy other = (ShareDetailsDummy) obj;
		return count == other.count && Objects.equals(portfolio, other.portfolio)
				&& shareDetailsId == other.shareDetailsId && Objects.equals(shareName, other.shareName);
	}

	private int shareDetailsId;
	private Portfolio portfolio;
	private String shareName;
	private int count;

	@Override
	public String toString() {
		return "ShareDetailsDummy [shareDetailsId=" + shareDetailsId + ", portfolio=" + portfolio + ", shareName="
				+ shareName + ", count=" + count + "]";
	}

	public ShareDetailsDummy() {
		super();
	}

	public ShareDetailsDummy(int shareDetailsId, Portfolio portfolio, String shareName, int count) {
		super();
		this.shareDetailsId = shareDetailsId;
		this.portfolio = portfolio;
		this.shareName = shareName;
		this.count = count;
	}

	public int getShareDetailsId() {
		return shareDetailsId;
	}

	public void setShareDetailsId(int shareDetailsId) {
		this.shareDetailsId = shareDetailsId;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
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

}
