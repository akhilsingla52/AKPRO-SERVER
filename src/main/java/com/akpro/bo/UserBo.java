package com.akpro.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserBo implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("user_name")
	private String userName;
	
	private String email;
	
	private String password;
	
	private String role;

	public UserBo() {}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
