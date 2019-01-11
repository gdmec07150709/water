package com.shuidi168.earn.domain;

/**
 * Commission entity. @author MyEclipse Persistence Tools
 */

public class Commission implements java.io.Serializable {

	// Fields

	private Integer id;
	private Double money;
	private Integer type;
	private String desc;
	private Integer generalUserId;

	// Constructors

	/** default constructor */
	public Commission() {
	}

	/** minimal constructor */
	public Commission(Double money, Integer type, Integer generalUserId) {
		this.money = money;
		this.type = type;
		this.generalUserId = generalUserId;
	}

	/** full constructor */
	public Commission(Double money, Integer type, String desc, Integer generalUserId) {
		this.money = money;
		this.type = type;
		this.desc = desc;
		this.generalUserId = generalUserId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getGeneralUserId() {
		return this.generalUserId;
	}

	public void setGeneralUserId(Integer generalUserId) {
		this.generalUserId = generalUserId;
	}

}