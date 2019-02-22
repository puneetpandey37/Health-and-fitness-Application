package com.supafit.common.exceptions;

public class InvalidRequestException extends RootException{

	
	/**
	 * 
	 */
	private String exceptionMessage;
	private static final long serialVersionUID = 1L;
	public InvalidRequestException(String exceptionMessage){
		this.exceptionMessage=exceptionMessage;
	}
	@Override
	String getExceptionMessage() {
		return exceptionMessage;
	}

	@Override
	void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
