package com.shuidi168.earn.domain;

/**
 * SystemInfo entity. @author MyEclipse Persistence Tools
 */

public class SystemInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String key;
	private String value;

	// Constructors

	/** default constructor */
	public SystemInfo() {
	}

	/** full constructor */
	public SystemInfo(String key, String value) {
		this.key = key;
		this.value = value;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}