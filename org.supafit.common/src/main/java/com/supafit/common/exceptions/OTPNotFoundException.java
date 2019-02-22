package com.supafit.common.exceptions;


public class OTPNotFoundException  extends AuthenticationException{

	public OTPNotFoundException(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exceptionMessage;


	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
