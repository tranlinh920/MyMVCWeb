package com.my.models;

import lombok.Data;

@Data
public class Paging {
	private int page;
	private int totalPages;
	private int totalItem;
	private int visiblePages;

	public Paging(int page, int totalPages, int totalItem, int visiblePages) {
		this.page = page;
		this.totalPages = totalPages;
		this.totalItem = totalItem;
		this.visiblePages = visiblePages;
	}
}