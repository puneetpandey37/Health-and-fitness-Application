package com.supafit.common.exceptions;

/**
 * @author PPY Exception thrown when no refresh token has been found.
 *
 */
public class NoRefreshTokenException extends GetCredentialsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param authorizationUrl
	 *            Construct a NoRefreshTokenException.
	 * @param authorizationUrl
	 *            The authorization URL to redirect the user to.
	 */
	public NoRefreshTokenException(String authorizationUrl) {
		super(authorizationUrl);
	}

}
