package com.shuidi168.earn.domain;

import java.sql.Timestamp;

import org.hibernate.annotations.Immutable;

/**
 * GeneralUser entity. @author MyEclipse Persistence Tools
 */
public class GeneralUser implements java.io.Serializable {

	// Fields
	private Integer id;
	private String phone;
	private String password;
	private Double money;
	private Double frozenMoney;
	private String inviteCode;
	private String wxOpenId;
	private String wxUnionId;
	private String nickname;
	private String country;
	private String province;
	private String city;
	private Integer sex;
	private Timestamp regTimestamp;
	private Timestamp lastLoginTimestamp;
	private String jwtToken;
	private Integer isLock;
	private Integer lockCount;
	private Timestamp unlockTimestamp;
	private Integer lockTypeId;
	private Integer devicesId;
	private Integer parentId;
	private String smsCode;
	
	// Constructors

	/** default constructor */
	public GeneralUser() {
	}
	public GeneralUser(Integer id) {
		this.id = id;
	}

	/** minimal constructor */
	public GeneralUser(Integer lockTypeId, Integer devicesId) {
		this.lockTypeId = lockTypeId;
		this.devicesId = devicesId;
	}

	/** full constructor */
	public GeneralUser(Integer id, String phone, String password, Double money, Double frozenMoney, String inviteCode,
			String wxOpenId, String wxUnionId, String nickname, String country, String province, String city,
			Integer sex, Timestamp regTimestamp, Timestamp lastLoginTimestamp, String jwtToken, Integer isLock,
			Integer lockCount, Timestamp unlockTimestamp, Integer lockTypeId, Integer devicesId, String smsCode) {
		super();
		this.id = id;
		this.phone = phone;
		this.password = password;
		this.money = money;
		this.frozenMoney = frozenMoney;
		this.inviteCode = inviteCode;
		this.wxOpenId = wxOpenId;
		this.wxUnionId = wxUnionId;
		this.nickname = nickname;
		this.country = country;
		this.province = province;
		this.city = city;
		this.sex = sex;
		this.regTimestamp = regTimestamp;
		this.lastLoginTimestamp = lastLoginTimestamp;
		this.jwtToken = jwtToken;
		this.isLock = isLock;
		this.lockCount = lockCount;
		this.unlockTimestamp = unlockTimestamp;
		this.lockTypeId = lockTypeId;
		this.devicesId = devicesId;
		this.smsCode = smsCode;
	}


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getFrozenMoney() {
		return this.frozenMoney;
	}

	public void setFrozenMoney(Double frozenMoney) {
		this.frozenMoney = frozenMoney;
	}
	

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getWxOpenId() {
		return this.wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	public String getWxUnionId() {
		return this.wxUnionId;
	}

	public void setWxUnionId(String wxUnionId) {
		this.wxUnionId = wxUnionId;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Timestamp getRegTimestamp() {
		return this.regTimestamp;
	}

	public void setRegTimestamp(Timestamp regTimestamp) {
		this.regTimestamp = regTimestamp;
	}

	public Timestamp getLastLoginTimestamp() {
		return this.lastLoginTimestamp;
	}

	public void setLastLoginTimestamp(Timestamp lastLoginTimestamp) {
		this.lastLoginTimestamp = lastLoginTimestamp;
	}

	public String getJwtToken() {
		return this.jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public Integer getIsLock() {
		return this.isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public Integer getLockCount() {
		return this.lockCount;
	}

	public void setLockCount(Integer lockCount) {
		this.lockCount = lockCount;
	}

	public Timestamp getUnlockTimestamp() {
		return this.unlockTimestamp;
	}

	public void setUnlockTimestamp(Timestamp unlockTimestamp) {
		this.unlockTimestamp = unlockTimestamp;
	}

	public Integer getLockTypeId() {
		return this.lockTypeId;
	}

	public void setLockTypeId(Integer lockTypeId) {
		this.lockTypeId = lockTypeId;
	}

	public Integer getDevicesId() {
		return this.devicesId;
	}

	public void setDevicesId(Integer devicesId) {
		this.devicesId = devicesId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	@Override
	public String toString() {
		return "GeneralUser [id=" + id + ", phone=" + phone + ", password=" + password + ", money=" + money
				+ ", frozenMoney=" + frozenMoney + ", inviteCode=" + inviteCode + ", wxOpenId=" + wxOpenId
				+ ", wxUnionId=" + wxUnionId + ", nickname=" + nickname + ", country=" + country + ", province="
				+ province + ", city=" + city + ", sex=" + sex + ", regTimestamp=" + regTimestamp
				+ ", lastLoginTimestamp=" + lastLoginTimestamp + ", jwtToken=" + jwtToken + ", isLock=" + isLock
				+ ", lockCount=" + lockCount + ", unlockTimestamp=" + unlockTimestamp + ", lockTypeId=" + lockTypeId
				+ ", devicesId=" + devicesId + ", parentId=" + parentId + ", smsCode=" + smsCode + "]";
	}
}