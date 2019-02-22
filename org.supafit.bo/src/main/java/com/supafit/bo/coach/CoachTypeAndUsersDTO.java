package com.supafit.bo.coach;

import java.util.List;

import com.supafit.dao.user.model.User;

public class CoachTypeAndUsersDTO {

	private String type;
	private List<User> users;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
