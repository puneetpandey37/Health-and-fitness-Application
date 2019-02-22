package com.supafit.bo.im;

import java.util.HashMap;
import java.util.Map;

import org.igniterealtime.restclient.RestApiClient;
import org.igniterealtime.restclient.entity.AuthenticationToken;
import org.igniterealtime.restclient.entity.UserEntities;
import org.igniterealtime.restclient.entity.UserEntity;
import org.igniterealtime.restclient.entity.UserGroupsEntity;
import org.springframework.stereotype.Component;
@Component
public class UserIMService {

	AuthenticationToken authenticationToken = new AuthenticationToken(
			"0ha0L19lz1910V61");
	RestApiClient restApiClient = new RestApiClient("localhost",
			9090, authenticationToken);
	

	public UserEntities getUsers() {
		UserEntities users = restApiClient.getUsers();
		return users;
	}

	public UserEntity getUser(String userName) {
		UserEntity user = restApiClient.getUser(userName);
		return user;
	}

	public UserEntities searchUsers(String userName) {
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("search", userName);
		UserEntities users = restApiClient.getUsers(querys);
		return users;
	}

	public UserEntity createUser(UserEntity user) {
		restApiClient.createUser(user);
		return user;
	}

	public UserEntity updateUser(UserEntity user) {
		restApiClient.updateUser(user);
		return user;
	}

	public String deleteUser(String userName) {
		restApiClient.deleteUser(userName);
		return userName;
	}

	public UserGroupsEntity getUserGroups(String userName) {
		return restApiClient.getUserGroups(userName);
	}

	public String addUserToGroup(String userName, String groupName) {
		restApiClient.addUserToGroup(userName, groupName);
		return userName;
	}

	public String deleteUserFromGroup(String userName, String groupName) {
		restApiClient.deleteUserFromGroup(userName, groupName);
		return userName;
	}
}
