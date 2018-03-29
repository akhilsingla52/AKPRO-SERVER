package com.akpro.bo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryBo {
	private Integer id;

	@JsonProperty("category_name")
	private String categoryName;
	
	@JsonProperty("created_date")
	private String createdDate;
	
	@JsonProperty("modified_date")
	private String modifiedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
