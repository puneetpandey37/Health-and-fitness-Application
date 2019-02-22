package com.supafit.common.exceptions;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NoContentException extends RootException{
	
	private static final long serialVersionUID = 1L;
	
	private String exceptionMessage;
	public NoContentException(String exceptionMessage){
		this.exceptionMessage=exceptionMessage;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
}
