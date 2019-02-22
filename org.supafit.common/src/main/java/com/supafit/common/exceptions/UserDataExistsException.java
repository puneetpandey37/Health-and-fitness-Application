package com.supafit.common.exceptions;

public class UserDataExistsException extends RootException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String error;

	public UserDataExistsException(String error) {
		super();
		this.error = error;
	}

	@Override
	public String getExceptionMessage() {
		return error;
	}

	@Override
	public void setExceptionMessage(String error) {
		this.error = error;
	}

}
