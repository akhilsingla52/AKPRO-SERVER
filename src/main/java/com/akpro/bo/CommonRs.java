package com.akpro.bo;

public class CommonRs<T> extends BaseResponse {
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
