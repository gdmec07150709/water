package com.shuidi168.earn.util;

/**
 * 用户信息基本参数校验工具类
 * @author chenyumin
 *
 */
public class Validate {

	// 私有静态断言类对象
	private static AssertUtil instance = AssertUtil.instance();

	/**
	 * 手机号码验证
	 * @param phone
	 */
	public static void validatePhone(String phone) {
		instance.isNotNull(phone, "手机号不能为空", RegisterErrCode.PHONE_NULL);
		instance.isMobile(phone, "手机号格式不对", RegisterErrCode.INVALID_PHONE);
		instance.isLength(phone, RegisterConstants.LENGTH_PHONE, "手机号长度必须为" + RegisterConstants.LENGTH_PHONE + "位",
				RegisterErrCode.PHONE_NULL, RegisterErrCode.INVALID_PHONE_LENGTH);
	}
	/**
	 * 密码基本校验
	 * @param password
	 */
	public static void validatePassword(String password){
		instance.isNotNull(password, "密码不能为空", RegisterErrCode.PASSWORD_NULL);
		instance.isRangeLength(password, RegisterConstants.MIN_LENGTH_PASSWORD, RegisterConstants.MAX_LENGTH_PASSWORD,
				"密码的长度在" + RegisterConstants.MIN_LENGTH_PASSWORD + "-" + RegisterConstants.MAX_LENGTH_PASSWORD + "之间",
				RegisterErrCode.PASSWORD_NULL, RegisterErrCode.INVALID_PASSWORD_LENGTH);
	}

	/**
	 * 验证码基本校验
	 * 
	 * @param smsCode
	 */
	public static void validateSmsCode(String smsCode) {
		instance.isNotNull(smsCode, "验证码不能为空", RegisterErrCode.SMS_CODE_NULL);
		instance.isLength(smsCode, RegisterConstants.LENGH_VERIFYCODE,
				"验证码长度必须为" + RegisterConstants.LENGH_VERIFYCODE + "位", RegisterErrCode.SMS_CODE_NULL,
				RegisterErrCode.SMS_CODE_LENGTH);
	}

	/**
	 * 邀请码基本校验
	 * 
	 * @param inviteCode
	 */
	public static void validateInviteCode(String inviteCode) {
		instance.isLength(inviteCode, RegisterConstants.LENGH_INVITECODE,
				"邀请码长度必须为" + RegisterConstants.LENGH_INVITECODE + "位", 0, RegisterErrCode.INVALID_INVITECODE_LENGTH);
	}

}
