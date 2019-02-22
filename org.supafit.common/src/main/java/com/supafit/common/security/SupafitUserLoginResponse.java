package com.supafit.common.security;

import com.supafit.dao.user.model.User;
import com.wordnik.swagger.annotations.ApiModel;
@ApiModel("SupafitCoachLoginResponse")
public class SupafitUserLoginResponse {

	private String token;
	private User user;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
