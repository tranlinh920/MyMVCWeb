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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Bills")
public class BillEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bil_id", columnDefinition = "bigint")
	private Long bilId;

	@Column(name = "bilAmount", nullable = false, columnDefinition = "int")
	private int bilAmount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bs_id", nullable = false, columnDefinition = "bigint")
	private BillStatusEnitity bilStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = true, columnDefinition = "bigint")
	private UserEntity bilUser;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cus_id", nullable = true, columnDefinition = "bigint")
	private CustomerEntity bilCus;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pbBill")
	private List<ProductBillEntity> bilProducts = new ArrayList<>();

	public BillEntity() {
	};

	public BillEntity(Long bilId) {
		this.bilId = bilId;
	}

	public static boolean isExitField(String sortParam) {
		try {
			BillEntity.class.getDeclaredField(sortParam);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
