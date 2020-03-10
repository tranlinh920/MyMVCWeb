package com.my.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ProductImages")
public class ProductImageEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proImage_id", columnDefinition = "bigint")
	private Long proImageId;

	@Column(name = "proImageName", columnDefinition = "varchar(28)", nullable = false)
	private String proImageName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pro_id", nullable = true, columnDefinition = "bigint")
	private ProductEntity product;

	public ProductImageEntity() {
	}

	public ProductImageEntity(String proImageName, ProductEntity product) {
		this.proImageName = proImageName;
		this.product = product;
	}

}
