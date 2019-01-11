package com.shuidi168.earn.domain;

/**
 * Advertise entity. @author MyEclipse Persistence Tools
 */

public class Advertise implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer type;
	private String url;
	private String title;
	private Integer level;

	// Constructors

	/** default constructor */
	public Advertise() {
	}

	/** minimal constructor */
	public Advertise(Integer type, String url) {
		this.type = type;
		this.url = url;
	}

	/** full constructor */
	public Advertise(Integer type, String url, String title, Integer level) {
		this.type = type;
		this.url = url;
		this.title = title;
		this.level = level;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}