package com.supafit.common.exceptions;


public class BadCredentialsException  extends AuthenticationException{

	private static final long serialVersionUID = 1L;

	private String exceptionMessage;
	public BadCredentialsException(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	/**
	 * 
	 */
	String getExceptionMessage() {
		return exceptionMessage;
	}

	void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
}
