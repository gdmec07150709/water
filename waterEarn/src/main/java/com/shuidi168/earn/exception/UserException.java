package com.shuidi168.earn.exception;

/**
 * 用于给用户看到的异常处理，比如 手机号错误(统一错误码：10001)
 * 
 * @author Administrator
 *
 */
public class UserException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private int errCode;

	public UserException(String errMsg, int errCode) {
		super(errMsg);
		this.errCode = errCode;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

}
