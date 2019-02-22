package com.supafit.controller.coaches;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.supafit.bo.coach.CoachService;
import com.supafit.bo.user.UserService;
import com.supafit.common.exceptions.InvalidRequestException;
import com.supafit.common.parser.CoachParser;
import com.supafit.common.parser.UserParser;
import com.supafit.dao.coach.model.Coach;
import com.supafit.dao.coach.model.CoachMessages;
import com.supafit.dao.coach.model.CoachType;
import com.supafit.dao.user.model.User;
import com.supafit.model.coach.CoachDTO;
import com.supafit.model.coach.CoachMessagesDTO;
import com.supafit.model.coach.CoachTypeDTO;
import com.supafit.model.user.UserDTO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/coaches")
@Api(value = "coaches", description = "Operations pertaining to Coaches")
@CrossOrigin
public class CoachController {

	private CoachService coachService;
	private UserService userService;
	private CoachParser coachParser;
	private UserParser userParser;
	@Autowired
	public void setCoachParser(CoachParser coachParser) {
		this.coachParser = coachParser;
	}
	@Autowired
	public void setUserParser(UserParser userParser) {
		this.userParser = userParser;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setCoachService(CoachService coachService) {
		this.coachService = coachService;
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update Coach details", notes = "Update Coach details", response = CoachDTO.class)
	public ResponseEntity<Void> updateCoachDetail(
			@RequestBody CoachDTO coachDTO, UriComponentsBuilder uriComponentsBuilder) throws Exception {
		Coach coach = coachParser.convertToEntity(coachDTO);
		Coach coachResponse = coachService.updateCoachDetail(coach);
		coachParser.convertToDto(coachResponse);
		long coachId = coachDTO.getId();
		UriComponents uriComponents = 
				uriComponentsBuilder.path("/coaches/{coachId}").buildAndExpand(coachId);
		HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(uriComponents.toUri());
	    return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get all coaches", notes = "Get all coaches", response = CoachDTO.class)
	public ResponseEntity<List<CoachDTO>> getAllCoaches() throws Exception {
		List<Coach> coaches = coachService.getAllCoaches();
		List<CoachDTO> response = coaches.stream()
				.map(coach -> coachParser.convertToDto(coach))
				.collect(Collectors.toList());
		return new ResponseEntity<List<CoachDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{coachId}/trainees", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get all Users under this Coach", notes = "Get all Users under this Coach", response = UserDTO.class)
	public ResponseEntity<List<UserDTO>> getAllUsersUnderThisCoach(
			@PathVariable("coachId") Long coachId,
			@RequestParam(value = "no_of_days", required = false) Integer days)
			throws Exception {
		List<User> users = coachService.getUsersUnderThisCoach(coachId, days);
		List<UserDTO> response = users.stream()
				.map(user -> userParser.convertToDto(user))
				.collect(Collectors.toList());
		return new ResponseEntity<List<UserDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/types", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get all coache types", notes = "Get all coache types", response = CoachTypeDTO.class)
	public ResponseEntity<List<CoachTypeDTO>> getAllCoachTypes()
			throws Exception {
		List<CoachType> coachTypes = coachService.getCoachTypes();
		List<CoachTypeDTO> response = coachTypes.stream()
				.map(coachType -> coachParser.convertToDto(coachType))
				.collect(Collectors.toList());
		return new ResponseEntity<List<CoachTypeDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/types/{coachTypeId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get all coaches by the typeid", notes = "Get all coaches by the typeid", response = CoachDTO.class)
	public ResponseEntity<List<CoachDTO>> getAllCoachByType(
			@PathVariable("coachTypeId") Long coachTypeId) throws Exception {
		List<Coach> coaches = coachService.getCoachesByType(coachTypeId);
		List<CoachDTO> response = coaches.stream()
				.map(coach -> coachParser.convertToDto(coach))
				.collect(Collectors.toList());
		return new ResponseEntity<List<CoachDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{coachId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get details of a coach", notes = "Get details of a coach", response = CoachDTO.class)
	public ResponseEntity<CoachDTO> getCoachDetail(
			@PathVariable("coachId") Long coachId) throws Exception {
		Coach coach = coachService.getCoachesById(coachId);
		CoachDTO response = coachParser.convertToDto(coach);
		return new ResponseEntity<CoachDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "assign", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Assign coach to a User", notes = "Assign coach to a User", response = UserDTO.class)
	@CrossOrigin
	public ResponseEntity<UserDTO> assignCoachToUser(
			@RequestParam("coach_id") Long coachId,
			@RequestParam("user_id") Long userId,
			@RequestParam("coach_type_id") Long coachTypeId)
			throws InvalidRequestException {
		User user = userService.assignCoachToUser(coachId, userId, coachTypeId);
		UserDTO response = userParser.convertToDto(user);
		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "messages", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Create Coach message", notes = "Create Coach message", response = CoachMessagesDTO.class)
	public ResponseEntity<CoachMessagesDTO> createCoachMessages(
			@RequestBody CoachMessagesDTO coachMessagesRequest)
			throws Exception {
		CoachMessages message = coachParser.convertToEntity(coachMessagesRequest);
		CoachMessages messageResponse = coachService
				.createCoachMessages(message);
		CoachMessagesDTO response = coachParser.convertToDto(messageResponse);
		return new ResponseEntity<CoachMessagesDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/messages/trianees/{userId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get Coach messages by user id", notes = "Get Coach messages by user id", response = CoachMessagesDTO.class)
	public ResponseEntity<List<CoachMessagesDTO>> getCoachMessages(
			@PathVariable("userId") Long userId) throws Exception {
		List<CoachMessages> messages = coachService.getCoachMessages(userId);
		List<CoachMessagesDTO> response = messages.stream()
				.map(message -> coachParser.convertToDto(message))
				.collect(Collectors.toList());
		return new ResponseEntity<List<CoachMessagesDTO>>(response,
				HttpStatus.OK);
	}

	@RequestMapping(value = "messages", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update Coach message", notes = "Update Coach message", response = CoachMessagesDTO.class)
	public ResponseEntity<CoachMessagesDTO> updateCoachMessages(
			@RequestBody CoachMessagesDTO coachMessagesRequest)
			throws Exception {
		CoachMessages coachMessage = coachParser
				.convertToEntity(coachMessagesRequest);
		CoachMessages coachMessagesResponse = coachService
				.updateCoachMessages(coachMessage);
		CoachMessagesDTO response = coachParser.convertToDto(coachMessagesResponse);
		return new ResponseEntity<CoachMessagesDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "messages/{messageId}", method = RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "Delete Coach message", notes = "Delete Coach message", response = String.class)
	public String deleteCoachMessages(@PathVariable("messageId") Long messageId)
			throws Exception {
		return coachService.deleteCoachMessages(messageId);
	}
}
