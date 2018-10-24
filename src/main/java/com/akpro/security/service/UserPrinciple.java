package com.akpro.security.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.akpro.bean.Users;

public class UserPrinciple implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private Long id;
	
	private String token;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserPrinciple() { }

	public UserPrinciple(String userName, Long id, String token, List<GrantedAuthority> grantedAuthorities) {
		this.username = userName;
		this.id = id;
		this.token = token;
		this.authorities = grantedAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return username;
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

	public Long getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	public static UserDetails build(Users user) {
		if(user!=null)
			return new UserPrinciple(user.getUserName(), user.getId(), user.getToken(), null);
		return null;
	}

}
