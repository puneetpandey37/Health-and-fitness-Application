package com.supafit.common.exceptions;


public class UserNotExistsException extends AuthenticationException{

	public UserNotExistsException(String exceptionMessage) {
		this.exceptionMessage=exceptionMessage;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exceptionMessage;
	String getExceptionMessage() {
		return exceptionMessage;
	}

	void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
