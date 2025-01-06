package com.mutual.fund.vo;

public class JwtRequestVO {

	private String username;
	private String password;

	public JwtRequestVO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public JwtRequestVO() {
		super();
	}

	@Override
	public String toString() {
		return "JwtRequest [username=" + username + ", password=" + password + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
