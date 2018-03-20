package com.akpro.bo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDetailsBo {
	@JsonProperty("user_id")
	private Integer userId;

	@JsonProperty("user_name")
	private String userName;
	
	private String email;
	
	private String mobile;
	
	private String role;
	
	@JsonProperty("created_date")
	private String createdDate;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
