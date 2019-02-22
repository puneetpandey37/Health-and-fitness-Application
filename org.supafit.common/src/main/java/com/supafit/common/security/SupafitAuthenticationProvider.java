package com.supafit.common.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.supafit.common.exceptions.BadCredentialsException;
import com.supafit.dao.coach.CoachManager;
import com.supafit.dao.coach.model.Coach;
import com.supafit.dao.user.UserManager;
import com.supafit.dao.user.model.User;

/**
 * @author Puneet Pandey
 *
 */
@Component
public class SupafitAuthenticationProvider implements AuthenticationProvider {

	private UserManager userManager;
	private CoachManager coachManager;

	@Autowired
	public void setCoachManager(CoachManager coachManager) {
		this.coachManager = coachManager;
	}

	@Autowired
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		PasswordAuthenticationToken userPassAuthToken = null;

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		String userNameInput = (String) authentication.getPrincipal();
		String passwordInput = (String) authentication.getCredentials();
		passwordInput = passwordInput.replaceAll(" " , "+");
		User user = userManager.getUserByUserId(userNameInput);
		if (user == null)
			user = userManager.getUserByEmailId(userNameInput);
		if (user != null) {
			String userNameId = user.getUserId();
			String userNameEmail = user.getEmail();
			String password = user.getPassword();
			if ((userNameInput.equals(userNameId) || userNameInput.equals(userNameEmail))
					&& passwordInput.equals(password)) {
				userPassAuthToken = new PasswordAuthenticationToken(
						userNameInput, passwordInput, grantedAuthorities);
			} else {
				throw new BadCredentialsException("Bad User Credentials.");
			}
		} else {
			Coach coach = coachManager.getUserByUserId(userNameInput);
			if(coach != null) {
				String userNameId = coach.getCoachId();
				String userNameEmail = coach.getEmail();
				String password = coach.getPassword();
				if ((userNameInput.equals(userNameId) || userNameInput.equals(userNameEmail))
						&& passwordInput.equals(password)) {
					userPassAuthToken = new PasswordAuthenticationToken(
							userNameInput, passwordInput, grantedAuthorities);
				} else {
					throw new BadCredentialsException("Bad User Credentials.");
				}
			} else {
				throw new BadCredentialsException("Bad User Credentials.");
			}
		}
		return userPassAuthToken;
	}

	public boolean supports(Class<? extends Object> authentication) {
		return true;
	}

}
