package com.my.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Pages")
public class PageEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pag_id", columnDefinition = "bigint")
	private Long pagId;

	@Column(name = "pagName", columnDefinition = "nvarchar(256)", nullable = false)
	private String pagName;

	@Column(name = "pagCode", columnDefinition = "varchar(128)", nullable = false)
	private String pagCode;

//	@Column(name = "proDescribe", columnDefinition = "nvarchar(max)")
	@Column(name = "pagContent", columnDefinition = "text")
	private String pagContent;
}
