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
@Table(name = "ProductBills")
public class ProductBillEntity extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pb_id", columnDefinition = "bigint")
	private Long pbId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pro_id", nullable = false, columnDefinition = "bigint")
	private ProductEntity pbProduct;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bil_id", nullable = false, columnDefinition = "bigint")
	private BillEntity pbBill;
	
	@Column(name = "pbAmount", nullable = false, columnDefinition = "int")
	private int pbAmount;

	@Column(name = "pbPrice", nullable = false, columnDefinition = "int")
	private int pbPrice;
}
