package com.my.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.my.models.ProductUpload;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Products")
public class ProductEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pro_id", columnDefinition = "bigint")
	private Long proId;

	@Column(name = "proName", columnDefinition = "nvarchar(256)", nullable = false)
	private String proName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proType_id", nullable = false, columnDefinition = "bigint")
	private ProductTypeEntity proType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proBrand_id", nullable = true, columnDefinition = "bigint")
	private ProductBrandEntity proBrand;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proOrigin_id", nullable = true, columnDefinition = "bigint")
	private ProductOriginEntity proOrigin;

	@Column(name = "proPrice", nullable = false, columnDefinition = "int")
	private int proPrice;

	@Column(name = "proIsDiscount", nullable = false)
	private boolean proIsDiscount;

	@Column(name = "proDiscountRatio")
	private double proDiscountRatio;

	@Column(name = "proAmount", nullable = false, columnDefinition = "bigint")
	private int proAmount;

	@Column(name = "proIsScarcity", nullable = false) // khan hiếm: hệ thống sẽ đọc biến này để quyết định việc hiển thị
														// số lượng
	private boolean proIsScarcity;

//	@Column(name = "proDescribe", columnDefinition = "nvarchar(max)")
	@Column(name = "proDescribe", columnDefinition = "text")
	private String proDescribe;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<ProductImageEntity> proImages = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pbProduct")
	private List<ProductBillEntity> proBills = new ArrayList<>();

	public ProductEntity() {
	}

	public ProductEntity(Long proId) {
		this.proId = proId;
	}

	public void update(ProductUpload proUp) {
		this.proName = proUp.getProName();
		this.proPrice = proUp.getProPrice();
		this.proIsDiscount = proUp.isProIsDiscount();
		this.proDiscountRatio = proUp.getProDiscountRatio();
		this.proAmount = proUp.getProAmount();
		this.proIsScarcity = proUp.isProIsScarcity();
		this.proDescribe = proUp.getProDescribe();
	}

	public static boolean isExitField(String sortParam) {
		try {
			ProductEntity.class.getDeclaredField(sortParam);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// @Column(name="pincode", columnDefinition="Number(10) default '34562'")

}
