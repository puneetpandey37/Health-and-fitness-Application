package com.supafit.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supafit.common.exceptions.UserDataExistsException;
import com.supafit.dao.coach.CoachManager;
import com.supafit.dao.coach.model.Coach;
import com.supafit.dao.user.UserManager;
import com.supafit.dao.user.model.User;
@Service
public class SupafitAuthorization {
	private User user;
	private UserManager userManagaer;
	private CoachManager coachManager;
	private Coach coach;
	
	@Autowired
	public void setCoachManager(CoachManager coachManager) {
		this.coachManager = coachManager;
	}
	@Autowired
	public void setUserManagaer(UserManager userManagaer) {
		this.userManagaer = userManagaer;
	}
	
	public void coachEmailValidation(Coach coach) throws UserDataExistsException {
		this.coach = coach;
		if (coachEmailValidation())
			throw new UserDataExistsException("Email already registered!");
	}

	private boolean coachEmailValidation() {
		String email = coach.getEmail();
		Coach coachByEmail = coachManager.getCoachByEmailId(email);
		boolean result = false;
		if(coachByEmail != null) 
			result = true;
		return result;
	}

	public void emailValidation(User user) throws UserDataExistsException {
		this.user = user;
		if (emailValidation())
			throw new UserDataExistsException("Email already registered!");
	}
	
	private boolean emailValidation() {
		String email = user.getEmail();
		User userByEmail = userManagaer.getUserByEmailId(email);
		boolean result = false;
		if(userByEmail != null) 
			result = true;
		return result;
	}
}
