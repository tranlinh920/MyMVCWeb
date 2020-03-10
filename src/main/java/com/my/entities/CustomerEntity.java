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
@Table(name = "Customers")
public class CustomerEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cus_id", columnDefinition = "bigint")
	private Long id;

	@Column(name = "cusFullName", nullable = false, columnDefinition = "nvarchar(256)")
	private String cusFullName;

	@Column(name = "cusEmail", nullable = true, columnDefinition = "varchar(128)")
	private String cusEmail;

	@Column(name = "cusPhoneNumber", nullable = false, columnDefinition = "bigint")
	private Long cusPhoneNumber;

	// @Column(name = "cusAddress", nullable = false, columnDefinition =
	// "nvarchar(max)") // for sqlserver
	@Column(name = "cusAddress", nullable = false, columnDefinition = "text") // for mysql
	private String cusAddress;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bilCus")
	private List<BillEntity> cusBills = new ArrayList<>();

}
