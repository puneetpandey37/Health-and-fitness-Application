package com.supafit.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.user.UserManager;

@Component
public class LoggedInUserUtil {

	private UserManager userManager;
	public UserManager getUserManager() {
		return userManager;
	}
	@Autowired
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	/*public User getLoggedInUser() {
		String userName = getLoggedInUserName();
		User user = null;
		if (userName != null) {
			user = userManager.getUserByEmailId(userName);
		}
		return user;
	}
	
	public String getLoggedInUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = null;
		if(authentication != null)
			userName = (String)authentication.getPrincipal();
		return userName;
	}
	
	public Long getLoggedInUserId() {
		String userName = null;
		User user = null;
		Long result = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			userName = (String)authentication.getPrincipal();
			user = userManager.getUserByEmailId(userName);
			if(user != null)
				result = user.getId();
		}
		return result;
	}
	
	public String getNameOfLoggedInUser() {
		User loggedInUser = getLoggedInUser();
		String result = null;
		if(loggedInUser != null) {
			result = loggedInUser.getName();
		}
		return result;
	}*/
	
}
