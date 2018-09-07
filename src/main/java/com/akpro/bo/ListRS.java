package com.akpro.bo;

import java.io.Serializable;
import java.util.List;

public class ListRS<T> extends BaseResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<T> data;
	
	private Long count;
	
	private Integer pageCount;
	
	public ListRS() {
	}
	
	public ListRS(String status, Integer code, String message) {
		super(status, code, message);
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
}
