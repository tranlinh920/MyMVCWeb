package com.my.sevices.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.my.models.Category;

@Service
public class MenuServiceImpl {

	String baseUrl = "http://localhost:8080/";

	public List<Category> getNavigationBars() {
		List<Category> navs = new ArrayList<>();

		Category trangChu = new Category("Trang chủ", "");
		Category danhMucSP = new Category("Danh mục sản phẩm", "");
		Category dichVu = new Category("Dịch vụ", "");
		Category lienHe = new Category("Liên hệ", "");
		navs.add(trangChu);
		navs.add(danhMucSP);
		navs.add(dichVu);
		navs.add(lienHe);

		// create category for danhMucSP
		Category doChoi = new Category("Đồ chơi", "");
		Category phuTung = new Category("Phụ tùng", "");
		Category nhot = new Category("Nhớt", "");
		danhMucSP.getCatCategories().add(doChoi);
		danhMucSP.getCatCategories().add(phuTung);
		danhMucSP.getCatCategories().add(nhot);

		// create category for doChoi
		Category oc = new Category("Ốc", baseUrl + "san-pham/oc");
		doChoi.getCatCategories().add(oc);

		// create category for phuTung
		Category denTroSang = new Category("Đèn trợ sáng", baseUrl + "san-pham/den-tro-sang");
		phuTung.getCatCategories().add(denTroSang);

		return navs;
	}

	public List<Category> getSideBars() {
		List<Category> sideBars = new ArrayList<>();

		Category loaiSP = new Category("Loại sản phẩm", "");
		Category nhot = new Category("Nhớt", "");
		sideBars.add(loaiSP);
		sideBars.add(nhot);
		
		Category oc = new Category("Ốc", baseUrl + "san-pham/oc");
		loaiSP.getCatCategories().add(oc);
		
		Category motul = new Category("Motul", baseUrl + "");
		Category repsol = new Category("Repsol", baseUrl + "");
		Category castrol = new Category("Castrol", baseUrl + "");
		Category total = new Category("Total", baseUrl + "");
		nhot.getCatCategories().add(motul);
		nhot.getCatCategories().add(repsol);
		nhot.getCatCategories().add(castrol);
		nhot.getCatCategories().add(total);

		return sideBars;
	}

//	public void setCategory(List<NavigationCategory> parents) {
//		List<NavigationCategory> childs;
//		for (NavigationCategory parent : parents) {
//			childs = menuRepositoy.findByParentId(parent.getCatId());
//			parent.setCatCategories(childs);
//			setCategory(childs);
//		} 
//	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new MenuServiceImpl().getNavigationBars().toArray()));
	}
	
//	Danh Mục Sản Phẩm
//	🏍️ Đồ chơi xe máy
//	- kính chiếu hậu
//	- Đĩa xe máy
//	- Bao tay + gù
//	-Đèn led
//	👉 Phụ tùng thay thế
//	 Giống nó tự tìm
//	👉 Vỏ xe máy
//	- vỏ xe Michelin
//	- vỏ xe maxxis
//	👉 Nhớt xe máy
//	- Motul
//	- Repsol
//	- Castrol
//	- total
//	👉 Phụ kiện biker " Đéo có"
//	👉Phụ tùng theo xe
//	- Honda
//	- Yamaha
//	- Sym

}
