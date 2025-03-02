package com.authorization.jwt;

public class JwtRequest {
	private String username;
	private String password;

	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public JwtRequest() {
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