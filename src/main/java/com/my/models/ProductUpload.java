package com.my.models;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductUpload {
	private Long proId;
	private String proName;
	private String proTypeName;
	private String proBrandName;
	private String proOriginname;
	private int proPrice;
	private boolean proIsDiscount;
	private double proDiscountRatio;
	private int proAmount;
	private boolean proIsScarcity;
	private String proDescribe;
	private MultipartFile[] proFiles;
	private String[] proImages;
}
