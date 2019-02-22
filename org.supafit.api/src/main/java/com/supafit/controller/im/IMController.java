package com.supafit.controller.im;

import org.igniterealtime.restclient.entity.GroupEntity;
import org.igniterealtime.restclient.entity.MUCRoomEntity;
import org.igniterealtime.restclient.entity.ParticipantEntities;
import org.igniterealtime.restclient.entity.RosterEntities;
import org.igniterealtime.restclient.entity.RosterItemEntity;
import org.igniterealtime.restclient.entity.UserEntities;
import org.igniterealtime.restclient.entity.UserEntity;
import org.igniterealtime.restclient.entity.UserGroupsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supafit.bo.im.ChatRoomIMService;
import com.supafit.bo.im.GroupIMService;
import com.supafit.bo.im.OnlineUserIMService;
import com.supafit.bo.im.UserIMService;

@Controller
@RequestMapping("/im")
@CrossOrigin
public class IMController {

	ChatRoomIMService chatRoomIMService;
	GroupIMService groupIMService;
	UserIMService userIMService;
	OnlineUserIMService onlineuserIMService;

	@Autowired
	public void setchatRoomIMService(
			ChatRoomIMService chatRoomIMService) {
		this.chatRoomIMService = chatRoomIMService;
	}

	@Autowired
	public void setgroupIMService(
			GroupIMService groupIMService) {
		this.groupIMService = groupIMService;
	}

	@Autowired
	public void setuserIMService(UserIMService userIMService) {
		this.userIMService = userIMService;
	}

	@Autowired
	public void setonlineuserIMService(
			OnlineUserIMService onlineuserIMService) {
		this.onlineuserIMService = onlineuserIMService;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public UserEntities getUsers() {
		return userIMService.getUsers();
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public UserEntity getUser(@RequestParam("userName") String userName) {
		return userIMService.getUser(userName);
	}

	@RequestMapping(value = "/users/search", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public UserEntities searchUsers(@RequestParam("userName") String userName) {
		return userIMService.searchUsers(userName);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public UserEntity createUser(@RequestBody UserEntity user) {
		return userIMService.createUser(user);
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public UserEntity updateUser(@RequestBody UserEntity user) {
		return userIMService.updateUser(user);
	}

	@RequestMapping(value = "/users", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteUser(@RequestParam("userName") String userName) {
		return userIMService.deleteUser(userName);
	}

	@RequestMapping(value = "/users/groups", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public UserGroupsEntity getUserGroups(
			@RequestParam("userName") String userName) {
		return userIMService.getUserGroups(userName);
	}

	@RequestMapping(value = "/users/groups", method = RequestMethod.POST)
	@ResponseBody
	public String addUserToGroup(@RequestParam("userName") String userName,
			@RequestParam("groupName") String groupName) {
		return userIMService.addUserToGroup(userName, groupName);
	}

	@RequestMapping(value = "/users/groups", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteUserToGroup(@RequestParam("userName") String userName,
			@RequestParam("groupName") String groupName) {
		return userIMService.deleteUserFromGroup(userName, groupName);
	}

	@RequestMapping(value = "/groups", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public GroupEntity getGroup(@RequestParam("groupName") String groupName) {
		return groupIMService.getGroup(groupName);
	}

	@RequestMapping(value = "/groups", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public GroupEntity createGroup(@RequestParam("name") String name,
			@RequestParam("description") String description) {
		GroupEntity groupEntity = groupIMService.createGroup(name,
				description);
		return groupEntity;
	}

	@RequestMapping(value = "/groups", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public GroupEntity updateGroup(String name, String description) {
		return groupIMService.updateGroup(name, description);
	}

	@RequestMapping(value = "/groups", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteGroup(String groupName) {
		return groupIMService.deleteGroup(groupName);
	}

	@RequestMapping(value = "/online/contacts", method = RequestMethod.GET)
	@ResponseBody
	public RosterEntities getOnlineContacts(
			@RequestParam("userName") String userName) {
		return onlineuserIMService.getOnlineContacts(userName);
	}

	@RequestMapping(value = "/contacts", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RosterItemEntity addContacts(@RequestParam("email") String email,
			@RequestParam("nickName") String nickName,
			@RequestParam("subscriptionType") Integer subscriptionType,
			@RequestParam("userName") String userName) {
		return onlineuserIMService.addContacts(email, nickName,
				subscriptionType, userName);
	}

	@RequestMapping(value = "/contacts", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public RosterItemEntity updateContacts(String email, String nickName,
			int subscriptionType, String userName) {
		return onlineuserIMService.updateContacts(email, nickName,
				subscriptionType, userName);
	}

	@RequestMapping(value = "/contacts", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteContacts(@RequestParam("userName") String userName,
			@RequestParam("email") String email) {
		return onlineuserIMService.deleteContacts(userName, email);
	}

	@RequestMapping(value = "/chatroom", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public MUCRoomEntity addChatRoom(@RequestParam("roomName") String roomName,
			@RequestParam("naturalName") String naturalName,
			@RequestParam("description") String description) {
		return chatRoomIMService.addChatRoom(roomName, naturalName,
				description);
	}

	@RequestMapping(value = "/chatroom", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public MUCRoomEntity updateChatRoom(
			@RequestParam("roomName") String roomName,
			@RequestParam("naturalName") String naturalName,
			@RequestParam("description") String description) {
		return chatRoomIMService.updateChatRoom(roomName, naturalName,
				description);
	}

	@RequestMapping(value = "/chatroom", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteChatRoom(@RequestParam("roomName") String roomName) {
		return chatRoomIMService.deleteChatRoom(roomName);
	}

	@RequestMapping(value = "/chatroom/owner", method = RequestMethod.POST)
	@ResponseBody
	public String addOwnerRoleToChatRoom(
			@RequestParam("chatRoom") String chatRoom,
			@RequestParam("userName") String userName) {
		return chatRoomIMService.addOwnerRoleToChatRoom(chatRoom,
				userName);
	}

	@RequestMapping(value = "/chatroom/admin", method = RequestMethod.POST)
	@ResponseBody
	public String addAdminRoleToChatRoom(
			@RequestParam("chatRoom") String chatRoom,
			@RequestParam("userName") String userName) {
		return chatRoomIMService.addAdminRoleToChatRoom(chatRoom,
				userName);
	}

	@RequestMapping(value = "/chatroom/member", method = RequestMethod.POST)
	@ResponseBody
	public String addMemberToChatRoom(
			@RequestParam("chatRoom") String chatRoom,
			@RequestParam("userName") String userName) {
		return chatRoomIMService.addMemberToChatRoom(chatRoom, userName);
	}

	@RequestMapping(value = "/chatroom/participants", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ParticipantEntities getChatRoomParticipants(
			@RequestParam("chatRoom") String chatRoom) {
		return chatRoomIMService.getChatRoomParticipants(chatRoom);
	}
}
