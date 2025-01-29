package com.networth.vo;

import java.util.Objects;

public class Share {

	private int shareId;
	private String shareName;
	private double shareValue;

	@Override
	public String toString() {
		return "Share [shareId=" + shareId + ", shareName=" + shareName + ", shareValue=" + shareValue + "]";
	}

	public Share() {
		super();
	}

	public Share(int shareId, String shareName, double shareValue) {
		super();
		this.shareId = shareId;
		this.shareName = shareName;
		this.shareValue = shareValue;
	}

	public int getShareId() {
		return shareId;
	}

	public void setShareId(int shareId) {
		this.shareId = shareId;
	}

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	public double getShareValue() {
		return shareValue;
	}

	public void setShareValue(double shareValue) {
		this.shareValue = shareValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(shareId, shareName, shareValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Share other = (Share) obj;
		return shareId == other.shareId && Objects.equals(shareName, other.shareName)
				&& Double.doubleToLongBits(shareValue) == Double.doubleToLongBits(other.shareValue);
	}
	
}
