package com.share.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "share")
public class Share {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "share_id")
	private int shareId;
	@Column(name = "share_name", unique = true, nullable = false)
	private String shareName;
	@Column(name = "share_value", unique = true, nullable = false)
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
	
}
