package com.akpro.bo;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String status;
	
	private Integer statusCode;
	
	private String message;
	
	private String developerMessage;
	
	public BaseResponse() {
	}

	public BaseResponse(String status, Integer statusCode, String message) {
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
}
