package com.my.models;

import java.util.List;

import lombok.Data;

@Data
public class Navigation {

	private String navName;
	private String navLink;
	private List<Category> navCategories;

	public Navigation() {
		
	}

	public Navigation(String navName, String navLink) {
		this.navName = navName;
		this.navLink = navLink;
	}

	public Navigation(String navName, String navLink, List<Category> navCategories) {
		this.navName = navName;
		this.navLink = navLink;
		this.navCategories = navCategories;
	}

}
