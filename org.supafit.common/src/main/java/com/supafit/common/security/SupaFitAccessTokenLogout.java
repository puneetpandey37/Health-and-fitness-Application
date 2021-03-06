package com.supafit.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//import org.springframework.security.oauth2.provider.token.InMemoryTokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


/**
 * @author puneet
 *
 */
public class SupaFitAccessTokenLogout implements LogoutSuccessHandler  {
	
	JdbcTokenStore  tokenstore;
	public JdbcTokenStore getTokenstore() {
		return tokenstore;
	}
	public void setTokenstore(JdbcTokenStore tokenstore) {
		this.tokenstore = tokenstore;
	}


	public void onLogoutSuccess(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse,
			Authentication paramAuthentication) throws IOException,
			ServletException {
		removeaccess(paramHttpServletRequest);
		paramHttpServletResponse.getOutputStream().write("\n\tYou Have Logged Out successfully.".getBytes());
		
	}
	
	
	public void removeaccess(HttpServletRequest req){
		
		String tokens=req.getHeader("Authorization");
		System.out.println(tokens);
		String value=tokens.substring(tokens.indexOf(" ")).trim(); 
		DefaultOAuth2AccessToken token= new DefaultOAuth2AccessToken(value);
		System.out.println(token);
		tokenstore.removeAccessToken(value);
		System.out.println("\n\tAccess Token Removed Successfully!!!!!!!!");
		
	}

}
