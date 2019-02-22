package com.supafit.common.security;

import com.supafit.dao.coach.model.Coach;
import com.wordnik.swagger.annotations.ApiModel;
@ApiModel("SupafitCoachLoginResponse")
public class SupafitCoachLoginResponse {

	private String token;
	private Coach coach;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
}
