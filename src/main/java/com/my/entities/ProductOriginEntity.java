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
@Table(name = "ProductOrigins")
public class ProductOriginEntity extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proOrigin_id", columnDefinition = "bigint")
	private Long proNationId;

	@Column(name = "proOriginName", columnDefinition = "nvarchar(256)", nullable = false)
	private String proOriginName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proOrigin")
	private List<ProductEntity> products = new ArrayList<>();
}
