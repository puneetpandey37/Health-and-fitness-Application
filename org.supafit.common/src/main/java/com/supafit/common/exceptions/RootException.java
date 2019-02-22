package com.supafit.common.exceptions;

public abstract class RootException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	abstract String getExceptionMessage();
	abstract void setExceptionMessage(String exceptionMessage);
	
}
