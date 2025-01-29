package com.networth.vo;

public class UserVO {

	private int uid;
	private String uname;
	private String passwd;

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", passwd=" + passwd + "]";
	}

	public UserVO() {
		super();
	}

	public UserVO(int uid, String uname, String passwd) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.passwd = passwd;
	}
	
	public UserVO(String uname, String passwd) {
		super();
		this.uname = uname;
		this.passwd = passwd;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
