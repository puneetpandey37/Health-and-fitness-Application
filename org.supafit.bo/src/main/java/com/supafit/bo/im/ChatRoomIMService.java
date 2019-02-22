package com.supafit.bo.im;

import org.igniterealtime.restclient.RestApiClient;
import org.igniterealtime.restclient.entity.AuthenticationToken;
import org.igniterealtime.restclient.entity.MUCRoomEntity;
import org.igniterealtime.restclient.entity.ParticipantEntities;
import org.springframework.stereotype.Component;

@Component
public class ChatRoomIMService {

	AuthenticationToken authenticationToken = new AuthenticationToken(
			"0ha0L19lz1910V61");
	RestApiClient restApiClient = new RestApiClient("localhost", 9090,
			authenticationToken);

	public MUCRoomEntity addChatRoom(String roomName, String naturalName,
			String description) {

		MUCRoomEntity chatRoom = new MUCRoomEntity(roomName, naturalName,
				description);
		restApiClient.createChatRoom(chatRoom);
		return chatRoom;
	}

	public MUCRoomEntity updateChatRoom(String roomName, String naturalName,
			String description) {

		MUCRoomEntity chatRoom = new MUCRoomEntity(roomName, naturalName,
				description);
		restApiClient.updateChatRoom(chatRoom);
		return chatRoom;
	}

	public String deleteChatRoom(String roomName) {
		restApiClient.deleteChatRoom(roomName);
		return "success";
	}

	public String addOwnerRoleToChatRoom(String chatRoom, String userName) {
		restApiClient.addOwner(chatRoom, userName);
		return "success";
	}

	public String addAdminRoleToChatRoom(String chatRoom, String userName) {
		restApiClient.addAdmin(chatRoom, userName);
		return "success";
	}

	public String addMemberToChatRoom(String chatRoom, String userName) {
		restApiClient.addMember(chatRoom, userName);
		return "success";
	}

	public ParticipantEntities getChatRoomParticipants(String chatRoom) {
		return restApiClient.getChatRoomParticipants(chatRoom);
	}
}
