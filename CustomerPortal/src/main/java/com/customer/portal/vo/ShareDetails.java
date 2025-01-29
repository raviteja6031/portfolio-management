package com.customer.portal.vo;

public class ShareDetails {

	private int shareDetailsId;
	private String shareName;
	private int count;

	@Override
	public String toString() {
		return "ShareDetails [shareDetailsId=" + shareDetailsId + ", shareName=" + shareName + ", count=" + count + "]";
	}

	public ShareDetails() {
		super();
	}

	public ShareDetails(int shareDetailsId, String shareName, int count) {
		super();
		this.shareDetailsId = shareDetailsId;
		this.shareName = shareName;
		this.count = count;
	}

	public int getShareDetailsId() {
		return shareDetailsId;
	}

	public void setShareDetailsId(int shareDetailsId) {
		this.shareDetailsId = shareDetailsId;
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
