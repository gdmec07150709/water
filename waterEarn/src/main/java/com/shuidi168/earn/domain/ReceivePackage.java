package com.shuidi168.earn.domain;

import java.sql.Timestamp;

/**
 * ReceivePackage entity. @author MyEclipse Persistence Tools
 */

public class ReceivePackage implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private Double money;
	private Timestamp receiveTimestamp;
	private Integer generalUserId;
	private Integer releasePackageId;

	// Constructors

	/** default constructor */
	public ReceivePackage() {
	}

	/** minimal constructor */
	public ReceivePackage(String title, Double money, Integer generalUserId, Integer releasePackageId) {
		this.title = title;
		this.money = money;
		this.generalUserId = generalUserId;
		this.releasePackageId = releasePackageId;
	}

	/** full constructor */
	public ReceivePackage(String title, Double money, Timestamp receiveTimestamp, Integer generalUserId,
			Integer releasePackageId) {
		this.title = title;
		this.money = money;
		this.receiveTimestamp = receiveTimestamp;
		this.generalUserId = generalUserId;
		this.releasePackageId = releasePackageId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Timestamp getReceiveTimestamp() {
		return this.receiveTimestamp;
	}

	public void setReceiveTimestamp(Timestamp receiveTimestamp) {
		this.receiveTimestamp = receiveTimestamp;
	}

	public Integer getGeneralUserId() {
		return this.generalUserId;
	}

	public void setGeneralUserId(Integer generalUserId) {
		this.generalUserId = generalUserId;
	}

	public Integer getReleasePackageId() {
		return this.releasePackageId;
	}

	public void setReleasePackageId(Integer releasePackageId) {
		this.releasePackageId = releasePackageId;
	}

}