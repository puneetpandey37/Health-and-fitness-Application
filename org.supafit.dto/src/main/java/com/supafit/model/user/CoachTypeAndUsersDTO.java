package com.supafit.model.user;

import java.util.List;

public class CoachTypeAndUsersDTO {

	private String type;
	private List<UserDTO> users;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
}
