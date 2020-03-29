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
@Table(name = "BillStatuses")
public class BillStatusEnitity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bs_id", columnDefinition = "bigint")
	private Long bsId;

	@Column(name = "bsCode", nullable = false, columnDefinition = "varchar(128)")
	private String bsCode;

	@Column(name = "bsName", nullable = false, columnDefinition = "nvarchar(256)")
	private String bsName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bilStatus")
	private List<BillEntity> bsbills = new ArrayList<>();

}
