package com.supafit.bo.im;

import java.util.ArrayList;
import java.util.List;

import org.igniterealtime.restclient.RestApiClient;
import org.igniterealtime.restclient.entity.AuthenticationToken;
import org.igniterealtime.restclient.entity.RosterEntities;
import org.igniterealtime.restclient.entity.RosterItemEntity;
import org.springframework.stereotype.Component;
@Component
public class OnlineUserIMService {

	AuthenticationToken authenticationToken = new AuthenticationToken(
			"0ha0L19lz1910V61");
	RestApiClient restApiClient = new RestApiClient("http://testdomain.com",
			9090, authenticationToken);

	public RosterEntities getOnlineContacts(String userName) {
		return restApiClient.getRoster(userName);
	}

	public RosterItemEntity addContacts(String email, String nickName,
			int subscriptionType, String userName) {
		// Create a user roster entry (Possible values for subscriptionType are:
		// -1 (remove), 0 (none), 1 (to), 2 (from), 3 (both))
		RosterItemEntity rosterItemEntity = new RosterItemEntity(email,
				nickName, subscriptionType);
		List<String> groups = new ArrayList<String>();
		groups.add("Supporter");
		rosterItemEntity.setGroups(groups);
		restApiClient.addRosterEntry(userName, rosterItemEntity);
		return rosterItemEntity;
	}

	public RosterItemEntity updateContacts(String email, String nickName,
			int subscriptionType, String userName) {
		RosterItemEntity rosterItemEntity = new RosterItemEntity(email,
				nickName, subscriptionType);
		restApiClient.updateRosterEntry(userName, rosterItemEntity);
		return rosterItemEntity;
	}

	public String deleteContacts(String userName, String email) {
		restApiClient.deleteRosterEntry(userName, email);
		return "success";
	}

}
