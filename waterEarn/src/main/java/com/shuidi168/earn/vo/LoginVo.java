package com.shuidi168.earn.vo;

public class LoginVo {

	// 微信登录需要绑定的手机号
	private String phone;

	// 登录后需要返回给客户端的jwtToken
	private String jwtToken;

	// 绑定微信状态；true表示已绑
	private boolean bindWeChat;
	// wxopenid
	private String wxOpenId;
	// smsCode
	private String smsCode;

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWxOpenId() {
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public boolean isBindWeChat() {
		return bindWeChat;
	}

	public void setBindWeChat(boolean bindWeChat) {
		this.bindWeChat = bindWeChat;
	}

}
