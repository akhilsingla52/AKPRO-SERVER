package com.akpro.bo;

import java.io.Serializable;

public class CommonRS<T> extends BaseResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private T data;
	
	public CommonRS() {
	}
	
	public CommonRS(String status, Integer code, String message) {
		super(status, code, message);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
