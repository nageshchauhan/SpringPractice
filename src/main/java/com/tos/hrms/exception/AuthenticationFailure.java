package com.tos.hrms.exception;

@SuppressWarnings("serial")
public class AuthenticationFailure extends Exception {

	private String errorMsg;

	public AuthenticationFailure(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

}
