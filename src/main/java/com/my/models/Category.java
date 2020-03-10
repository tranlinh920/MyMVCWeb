package com.my.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Category {
	private Long catId;
	private String catName;
	private String catLink;
	private List<Category> catCategories = new ArrayList<>();

	public Category() {

	}

	public Category(String catName, String catLink) {
		this.catName = catName;
		this.catLink = catLink;
	}

	public Category(String catName, String catLink, List<Category> catCategories) {
		this.catName = catName;
		this.catLink = catLink;
		this.catCategories = catCategories;
	}

}
