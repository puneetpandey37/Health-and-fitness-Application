package com.supafit.common.exceptions;

/**
 * Exception thrown when a code exchange has failed.
 */
public class GetCredentialsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String authorizationUrl;

	/**
	 * Construct a GetCredentialsException.
	 *
	 * @param authorizationUrl
	 *            The authorization URL to redirect the user to.
	 */
	public GetCredentialsException(String authorizationUrl) {
		this.authorizationUrl = authorizationUrl;
	}

	/**
	 * Set the authorization URL.
	 */
	public void setAuthorizationUrl(String authorizationUrl) {
		this.authorizationUrl = authorizationUrl;
	}

	/**
	 * @return the authorizationUrl
	 */
	public String getAuthorizationUrl() {
		return authorizationUrl;
	}
}