package com.akpro.bo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExamDetailsBo {
	private Integer id;
	
	@JsonProperty("user_id")
	private Integer userId;
	
	private String name;
	
	private String email;
	
	@JsonProperty("category_id")
	private Integer categoryId;
	
	private Integer attempt;
	
	private Integer right;
	
	private Integer marks;
	
	private String date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAttempt() {
		return attempt;
	}

	public void setAttempt(Integer attempt) {
		this.attempt = attempt;
	}

	public Integer getRight() {
		return right;
	}

	public void setRight(Integer right) {
		this.right = right;
	}

	public Integer getMarks() {
		return marks;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
