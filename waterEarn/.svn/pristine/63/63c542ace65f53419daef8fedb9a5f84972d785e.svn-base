package com.shuidi168.earn.util;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.shuidi168.earn.exception.UserException;

/**
 * 断言工具，进行参数的基本判断
 */
public class AssertUtil {

	/**
	 * 手机号验证码规则
	 */
	private static final String REGEX_MOBILE = "^((13[0-9])|(15[0-9])|(18[0,0-9]))\\d{8}$";

	private static final AssertUtil instance = new AssertUtil();

	private AssertUtil() {

	}

	public static AssertUtil instance() {
		return instance;
	}

	/**
	 * 断言字符串不为空
	 */
	public AssertUtil isNotNull(String str, String errMsg,int errCode) {
		if (!StringUtils.hasLength(str)) {
			throw new UserException(errMsg,errCode);
		}
		return this;
	}

	/**
	 * 断言对象不为空
	 */
	public AssertUtil isNotNull(Object obj, String errMsg,int errCode) {
		if (obj == null) {
			throw new UserException(errMsg,errCode);
		}
		return this;
	}

	/**
	 * 断言对象为空
	 */
	public AssertUtil isNull(Object obj, String errMsg,int errCode) {
		if (obj != null) {
			throw new UserException(errMsg,errCode);
		}
		return this;
	}

	/**
	 * 断言字符串必须满足长度
	 */
	public AssertUtil isLength(String str, int length, String errMsg,int errCode1,int errCode) {
		isNotNull(str, errMsg,errCode1);
		if (str.length() != length) {
			throw new UserException(errMsg,errCode);
		}
		return this;
	}

	/**
	 * 断言手机号
	 */
	public AssertUtil isMobile(String mobile, String errMsg,int errCode) {
		if (!Pattern.matches(REGEX_MOBILE, mobile)) {
			throw new UserException(errMsg,errCode);
		}
		return this;
	}

	/**
	 * 断言false，如果b为true，抛出异常
	 */
	public AssertUtil isFalse(boolean b, String errMsg,int errCode) {
		if (b) {
			throw new UserException(errMsg, errCode);
		}
		return this;
	}

	/**
	 * 断言字符串范围长度
	 */
	public AssertUtil isRangeLength(String str, int minLength, int maxLength, String errMsg,int errCode1,int errCode) {
		isNotNull(str, errMsg, errCode1);
		if (str.length() < minLength || str.length() > maxLength) {
			throw new UserException(errMsg, errCode);
		}
		return this;
	}

	/**
	 * 断言两个字符串一致
	 */
	public AssertUtil isEquals(String str1, String str2, String errMsg,int errCode1,int errCode) {
		isNotNull(str1, errMsg, errCode1);
		isNotNull(str2, errMsg, errCode1);
		if (!str1.equals(str2)) {
			throw new UserException(errMsg, errCode);
		}
		return this;
	}
}
