package com.authorization.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.authorization.model.User;

public class MyUserDetails implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private User user;

	public MyUserDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPasswd();
	}

	@Override
	public String getUsername() {
		return user.getUname();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
}