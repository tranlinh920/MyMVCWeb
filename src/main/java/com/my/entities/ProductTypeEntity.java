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
@Table(name = "ProductTypes")
public class ProductTypeEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proType_id", columnDefinition = "bigint")
	private Long proTypeid;

	@Column(name = "proTypeName", columnDefinition = "nvarchar(256)", nullable = false)
	private String proTypeName;

	@Column(name = "proTypeCode", columnDefinition = "varchar(128)", nullable = false)
	private String proTypeCode;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proType")
	private List<ProductEntity> products = new ArrayList<>();
}
