package com.my.models;

import lombok.Data;

@Data
public class Result<T> {

	private int statusCode;
	private T data;
	private String massage;
	private Paging paging;

	public Result(int statusCode, T data) {
		this.statusCode = statusCode;
		this.data = data;
	}

	public Result(int statusCode, String massage) {
		this.statusCode = statusCode;
		this.massage = massage;
	}

	public Result(int statusCode, T data, Paging paging) {
		this.statusCode = statusCode;
		this.data = data;
		this.paging = paging;
	}

	public Result(int statusCode, T data, String massage) {
		this.statusCode = statusCode;
		this.data = data;
		this.massage = massage;
	}

	public Result(int statusCode, T data, String massage, Paging paging) {
		this.statusCode = statusCode;
		this.data = data;
		this.massage = massage;
		this.paging = paging;
	}

}
