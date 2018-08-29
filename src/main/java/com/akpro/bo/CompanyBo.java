package com.akpro.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyBo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@JsonProperty("image_url")
	private String imageUrl;
	
	@JsonProperty("image_data")
	private String imageData;
	
	@JsonProperty("company_name")
	private String companyName;
	
	private String website;
	
	private String description;
	
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
