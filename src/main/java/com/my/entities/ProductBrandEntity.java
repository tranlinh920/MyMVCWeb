package com.my.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ProductBrands")
public class ProductBrandEntity extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proBrand_id", columnDefinition = "bigint")
	private Long proBrandId;

	@Column(name = "proBrandName", columnDefinition = "nvarchar(256)", nullable = false)
	private String proBrandName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proBrand")
	private List<ProductEntity> products = new ArrayList<>();
}
