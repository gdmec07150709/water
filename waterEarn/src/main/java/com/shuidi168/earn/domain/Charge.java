package com.shuidi168.earn.domain;

import java.sql.Timestamp;

/**
 * Charge entity. @author MyEclipse Persistence Tools
 */

public class Charge implements java.io.Serializable {

	// Fields

	private Integer id;
	private Double money;
	private String tradeNo;
	private Timestamp timestamp;
	private Double realMoney;
	private Integer status;
	private Integer agentUserId;

	// Constructors

	/** default constructor */
	public Charge() {
	}

	/** minimal constructor */
	public Charge(Double money, String tradeNo, Integer agentUserId) {
		this.money = money;
		this.tradeNo = tradeNo;
		this.agentUserId = agentUserId;
	}

	/** full constructor */
	public Charge(Double money, String tradeNo, Timestamp timestamp, Double realMoney, Integer status,
			Integer agentUserId) {
		this.money = money;
		this.tradeNo = tradeNo;
		this.timestamp = timestamp;
		this.realMoney = realMoney;
		this.status = status;
		this.agentUserId = agentUserId;
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

	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Double getRealMoney() {
		return this.realMoney;
	}

	public void setRealMoney(Double realMoney) {
		this.realMoney = realMoney;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAgentUserId() {
		return this.agentUserId;
	}

	public void setAgentUserId(Integer agentUserId) {
		this.agentUserId = agentUserId;
	}

}