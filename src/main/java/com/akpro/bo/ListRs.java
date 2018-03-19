package com.akpro.bo;

import java.util.List;

public class ListRs<T> extends BaseResponse {
	private List<T> data;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
