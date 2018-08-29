package com.akpro.bo;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String status;
	
	private Integer code;
	
	private String message;
	
	private T data;
	
	public BaseResponse() {
	}

	public BaseResponse(String status) {
		this.status = status;
	}

	public BaseResponse(String status, Integer code) {
		this.status = status;
		this.code = code;
	}

	public BaseResponse(T data, String message) {
		this.data = data;
		this.message = message;
	}

	public BaseResponse(String status, Integer code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public BaseResponse(T data, String status, Integer code, String message) {
		this.data = data;
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
