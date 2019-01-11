package com.shuidi168.earn.domain;

import java.sql.Timestamp;

/**
 * Draw entity. @author MyEclipse Persistence Tools
 */

public class Draw implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer money;
	private Timestamp timestamp;
	private Integer status;
	private Integer generalUserId;

	// Constructors

	/** default constructor */
	public Draw() {
	}

	/** minimal constructor */
	public Draw(Integer money, Integer generalUserId) {
		this.money = money;
		this.generalUserId = generalUserId;
	}

	/** full constructor */
	public Draw(Integer money, Timestamp timestamp, Integer status, Integer generalUserId) {
		this.money = money;
		this.timestamp = timestamp;
		this.status = status;
		this.generalUserId = generalUserId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMoney() {
		return this.money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getGeneralUserId() {
		return this.generalUserId;
	}

	public void setGeneralUserId(Integer generalUserId) {
		this.generalUserId = generalUserId;
	}

}