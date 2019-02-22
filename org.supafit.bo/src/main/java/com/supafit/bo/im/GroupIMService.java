package com.supafit.bo.im;

import org.igniterealtime.restclient.RestApiClient;
import org.igniterealtime.restclient.entity.AuthenticationToken;
import org.igniterealtime.restclient.entity.GroupEntities;
import org.igniterealtime.restclient.entity.GroupEntity;
import org.springframework.stereotype.Component;

@Component
public class GroupIMService {

	AuthenticationToken authenticationToken = new AuthenticationToken(
			"0ha0L19lz1910V61");
	RestApiClient restApiClient = new RestApiClient("http://testdomain.com",
			9090, authenticationToken);

	public GroupEntities getGroups() {
		return restApiClient.getGroups();
	}

	public GroupEntity getGroup(String groupName) {
		return restApiClient.getGroup(groupName);
	}

	public GroupEntity createGroup(String name, String description) {
		GroupEntity groupEntity = new GroupEntity(name, description);
		restApiClient.createGroup(groupEntity);
		return groupEntity;
	}

	public GroupEntity updateGroup(String name, String description) {
		GroupEntity groupEntity = new GroupEntity(name, description);
	    restApiClient.updateGroup(groupEntity);
	    return groupEntity;
	}

	public String deleteGroup(String groupName) {
		restApiClient.deleteGroup(groupName);
		return "success";
	}
}
