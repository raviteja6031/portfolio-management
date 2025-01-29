package com.networth.model;

public class AssetSaleResponse {

	private boolean saleResponseStatus;
	private double balance;

	@Override
	public String toString() {
		return "AssetSaleResponse [saleResponseStatus=" + saleResponseStatus + ", balance=" + balance + "]";
	}

	public AssetSaleResponse() {
		super();
	}

	public AssetSaleResponse(boolean saleResponseStatus, double balance) {
		super();
		this.saleResponseStatus = saleResponseStatus;
		this.balance = balance;
	}

	public boolean isSaleResponseStatus() {
		return saleResponseStatus;
	}

	public void setSaleResponseStatus(boolean saleResponseStatus) {
		this.saleResponseStatus = saleResponseStatus;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double d) {
		this.balance = d;
	}

}
