package com.supafit.common.exceptions;


public class CodeExchangeException extends GetCredentialsException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Construct a CodeExchangeException.
     *
     * @param authorizationUrl The authorization URL to redirect the user to.
     */
    public CodeExchangeException(String authorizationUrl) {
      super(authorizationUrl);
    }
}
