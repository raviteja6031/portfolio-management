package com.authorization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private int uid;
	@Column(name = "uname", unique = true, nullable = false)
	private String uname;
	@Column(name = "passwd", nullable = false)
	private String passwd;

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", passwd=" + passwd + "]";
	}

	public User() {
		super();
	}

	public User(int uid, String uname, String passwd) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.passwd = passwd;
	}
	
	public User(String uname, String passwd) {
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
