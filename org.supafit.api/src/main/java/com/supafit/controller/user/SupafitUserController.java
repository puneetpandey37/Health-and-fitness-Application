package com.supafit.controller.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.supafit.bo.user.UserService;
import com.supafit.common.exceptions.InvalidRequestException;
import com.supafit.common.parser.FoodParser;
import com.supafit.common.parser.GoalsParser;
import com.supafit.common.parser.MedicalConditionParser;
import com.supafit.common.parser.ProgramParser;
import com.supafit.common.parser.UserParser;
import com.supafit.dao.food.model.FoodPreferences;
import com.supafit.dao.goal.model.FitnessGoal;
import com.supafit.dao.medicalcondition.model.MedicalCondition;
import com.supafit.dao.program.model.ProgramSubscription;
import com.supafit.dao.user.model.User;
import com.supafit.model.food.FoodPreferencesDTO;
import com.supafit.model.goal.FitnessGoalDTO;
import com.supafit.model.medicalcondition.MedicalConditionDTO;
import com.supafit.model.program.ProgramSubscriptionDTO;
import com.supafit.model.user.UserDTO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/users")
@Api(value = "users", description = "Operations pertaining to users")
@CrossOrigin
public class SupafitUserController {

	private UserService userService;
	private UserParser userParser;
	private FoodParser foodParser;
	private MedicalConditionParser mediConditionParser;
	private GoalsParser goalParser;
	private ProgramParser programParser;
	@Autowired
	public void setProgramParser(ProgramParser programParser) {
		this.programParser = programParser;
	}
	@Autowired
	public void setGoalParser(GoalsParser goalParser) {
		this.goalParser = goalParser;
	}
	@Autowired
	public void setMediConditionParser(MedicalConditionParser mediConditionParser) {
		this.mediConditionParser = mediConditionParser;
	}
	@Autowired
	public void setFoodParser(FoodParser foodParser) {
		this.foodParser = foodParser;
	}
	@Autowired
	public void setUserParser(UserParser userParser) {
		this.userParser = userParser;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update User", notes = "Update User", response = UserDTO.class)
	@CrossOrigin
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws Exception {
		User userEntity = userParser.convertToEntity(userDTO);
		User userResponse = userService.updateUser(userEntity);
		UserDTO response = userParser.convertToDto(userResponse);
		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(value="/{userId}",method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get user details", notes = "Get user details", response = UserDTO.class)
	@CrossOrigin
	public ResponseEntity<UserDTO> getUserDetail(
			@PathVariable(value = "userId") Long userId) {
		User user = userService.getUserDetail(userId);
		UserDTO response = userParser.convertToDto(user);
		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get all users", notes = "Get all users", response = UserDTO.class)
	@CrossOrigin
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		List<User> usersResponse = userService
				.getAllUsers();
		List<UserDTO> response = usersResponse.stream()
				.map(user -> userParser.convertToDto(user))
				.collect(Collectors.toList());
		return new ResponseEntity<List<UserDTO>>(response,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/coaches/{coachId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get all Coaches", notes = "Get all Coaches", response = UserDTO.class)
	@CrossOrigin
	public ResponseEntity<List<UserDTO>> findUsersUnderthisTrainer(@PathVariable("coachId") Long coachId) {
		List<User> usersResponse = userService
				.findUsersUnderthisTrainer(coachId);
		List<UserDTO> response = usersResponse.stream()
				.map(user -> userParser.convertToDto(user))
				.collect(Collectors.toList());
		return new ResponseEntity<List<UserDTO>>(response,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/gcm/{gcmId}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "Get or Update GCM ID of a User. gcm id is updated for the logged in user if passed, if not passed you get the gcm_id of the logged in user", 
	notes = "Get or Update GCM ID of a User. gcm id is updated for the logged in user if passed, if not passed you get the gcm_id of the logged in user", response = UserDTO.class)
	@CrossOrigin
	public String manageUserGcmId(@PathVariable(value = "gcmId") String gcmId)
			throws InvalidRequestException {
//		return userService.manageUserGcmId(gcmId);
		return null;
	}
	
	@RequestMapping(value = "/{userId}/foodpreferences", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Assign different food preferences to a user", notes = "Assign different food preferences to a user", response = FoodPreferencesDTO.class)
	@CrossOrigin
	public ResponseEntity<List<FoodPreferencesDTO>> createUserFoodPreferences(@RequestBody List<FoodPreferencesDTO> foodPreferencesDTOs,
			@PathVariable("userId") Long userId)
			throws InvalidRequestException {
		List<FoodPreferences> foodPreferencesEntities = foodPreferencesDTOs
				.stream()
				.map(mealPlan -> foodParser
						.convertToEntity(mealPlan))
				.collect(Collectors.toList());
		List<FoodPreferences> foodPreferencesResponse = userService
				.createUserFoodPreferences(userId, foodPreferencesEntities);
		List<FoodPreferencesDTO> response = foodPreferencesResponse
				.stream()
				.map(foodPreferences -> foodParser
						.convertToDto(foodPreferences))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FoodPreferencesDTO>>(response,
				HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{userId}/foodpreferences", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update food preferences of a user", notes = "Update food preferences of a user", response = FoodPreferencesDTO.class)
	@CrossOrigin
	public ResponseEntity<List<FoodPreferencesDTO>> updateUserFoodPreferences(@RequestBody List<FoodPreferencesDTO> foodPreferencesDTOs,
			@PathVariable("userId") Long userId)
			throws InvalidRequestException {
		List<FoodPreferences> foodPreferencesEntities = foodPreferencesDTOs
				.stream()
				.map(mealPlan -> foodParser
						.convertToEntity(mealPlan))
				.collect(Collectors.toList());
		List<FoodPreferences> foodPreferencesResponse = userService
				.updateUserFoodPreferences(userId, foodPreferencesEntities);
		List<FoodPreferencesDTO> response = foodPreferencesResponse
				.stream()
				.map(foodPreferences -> foodParser
						.convertToDto(foodPreferences))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FoodPreferencesDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}/foodpreferences", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get food preferences of a user", notes = "Get food preferences of a user", response = FoodPreferencesDTO.class)
	@CrossOrigin
	public ResponseEntity<List<FoodPreferencesDTO>> getUserFoodPreferences(@PathVariable("userId") Long userId)
			throws InvalidRequestException {
		List<FoodPreferences> foodPreferencesResponse = userService
				.getUserFoodPreferences(userId);
		List<FoodPreferencesDTO> response = foodPreferencesResponse.stream()
				.map(foodPreferences -> foodParser.convertToDto(foodPreferences))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FoodPreferencesDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}/medicalconditions", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Assign different medical conditions to a user", notes = "Assign different medical conditions to a user", response = FoodPreferencesDTO.class)
	@CrossOrigin
	public ResponseEntity<List<MedicalConditionDTO>> createUserMedicalConditions(@RequestBody List<MedicalConditionDTO> medicalConditionsDTOs,
			@PathVariable("userId") Long userId)
			throws InvalidRequestException {
		List<MedicalCondition> mediConditionEntities = medicalConditionsDTOs
				.stream()
				.map(mediCondition -> mediConditionParser
						.convertToEntity(mediCondition))
				.collect(Collectors.toList());
		List<MedicalCondition> foodPreferencesResponse = userService
				.createUserMedicalConditions(userId, mediConditionEntities);
		List<MedicalConditionDTO> response = foodPreferencesResponse
				.stream()
				.map(mediCondition -> mediConditionParser
						.convertToDto(mediCondition))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MedicalConditionDTO>>(response,
				HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{userId}/medicalconditions", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update medical conditions of a user", notes = "Update medical conditions of a user", response = FoodPreferencesDTO.class)
	@CrossOrigin
	public ResponseEntity<List<MedicalConditionDTO>> updateUserMedicalConditions(@RequestBody List<MedicalConditionDTO> medicalConditionsDTOs,
			@PathVariable("userId") Long userId)
			throws InvalidRequestException {
		List<MedicalCondition> mediConditionEntities = medicalConditionsDTOs
				.stream()
				.map(mediCondition -> mediConditionParser
						.convertToEntity(mediCondition))
				.collect(Collectors.toList());
		List<MedicalCondition> foodPreferencesResponse = userService
				.updateUserMedicalConditions(userId, mediConditionEntities);
		List<MedicalConditionDTO> response = foodPreferencesResponse
				.stream()
				.map(mediCondition -> mediConditionParser
						.convertToDto(mediCondition))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MedicalConditionDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}/medicalconditions", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get medical conditions of a user", notes = "Get medical conditions of a user", response = FoodPreferencesDTO.class)
	@CrossOrigin
	public ResponseEntity<List<MedicalConditionDTO>> getUserMedicalConditions(@PathVariable("userId") Long userId)
			throws InvalidRequestException {
		List<MedicalCondition> mediConditionsResponse = userService
				.getUserMedicalConditions(userId);
		List<MedicalConditionDTO> response = mediConditionsResponse.stream()
				.map(mediCondition -> mediConditionParser.convertToDto(mediCondition))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MedicalConditionDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}/fitnessgoals", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Assign different Fitness Goals to a user", notes = "Assign different Fitness Goals to a user", response = FoodPreferencesDTO.class)
	@CrossOrigin
	public ResponseEntity<List<FitnessGoalDTO>> createUserFitnessGoal(@RequestBody List<FitnessGoalDTO> fitnessGoalsDTOs,
			@PathVariable("userId") Long userId)
			throws InvalidRequestException {
		List<FitnessGoal> fitnessGoalsEntities = fitnessGoalsDTOs
				.stream()
				.map(fitnessGoal -> goalParser
						.convertToEntity(fitnessGoal))
				.collect(Collectors.toList());
		List<FitnessGoal> foodPreferencesResponse = userService
				.createUserFitnessGoal(userId, fitnessGoalsEntities);
		List<FitnessGoalDTO> response = foodPreferencesResponse
				.stream()
				.map(mediCondition -> goalParser
						.convertToDto(mediCondition))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FitnessGoalDTO>>(response,
				HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{userId}/fitnessgoals", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update Fitness Goals of a user", notes = "Update Fitness Goals of a user", response = FoodPreferencesDTO.class)
	@CrossOrigin
	public ResponseEntity<List<FitnessGoalDTO>> updateUserFitnessGoals(@RequestBody List<FitnessGoalDTO> fitnessGoalsDTOs,
			@PathVariable("userId") Long userId)
			throws InvalidRequestException {
		List<FitnessGoal> fitnessGoalsEntities = fitnessGoalsDTOs
				.stream()
				.map(fitnessGoal -> goalParser
						.convertToEntity(fitnessGoal))
				.collect(Collectors.toList());
		List<FitnessGoal> foodPreferencesResponse = userService
				.updateUserFitnessGoals(userId, fitnessGoalsEntities);
		List<FitnessGoalDTO> response = foodPreferencesResponse
				.stream()
				.map(mediCondition -> goalParser
						.convertToDto(mediCondition))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FitnessGoalDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}/fitnessgoals", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get Fitness Goals of a user", notes = "Get Fitness Goals of a user", response = FoodPreferencesDTO.class)
	@CrossOrigin
	public ResponseEntity<List<FitnessGoalDTO>> getUserFitnessGoals(@PathVariable("userId") Long userId)
			throws InvalidRequestException {
		List<FitnessGoal> fitnessGoalsResponse = userService
				.getUserFitnessGoals(userId);
		List<FitnessGoalDTO> response = fitnessGoalsResponse.stream()
				.map(mediCondition -> goalParser.convertToDto(mediCondition))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FitnessGoalDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/recent/registration", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get the List of Users who have recently registered with Supafit(in last N days)", notes = "Get the List of Users who have recently registered with Supafit(in last N days)", response = UserDTO.class)
	@CrossOrigin
	public ResponseEntity<List<UserDTO>> getRecentlyRegisteredUsers(@RequestParam("no_of_days") Integer days) {
		List<User> usersResponse = userService
				.getRecentlyRegisteredUsers(days);
		List<UserDTO> response = usersResponse.stream()
				.map(user -> userParser.convertToDto(user))
				.collect(Collectors.toList());
		return new ResponseEntity<List<UserDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/coaches/{coachId}/recent/subscription", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get the List of Users (and their subscription detail) who have recently subscribed to Supafit programs(in last N days)", 
	notes = "Get the List of Users (and their subscription detail) who have recently subscribed to Supafit programs(in last N days)",
	response = ProgramSubscriptionDTO.class)
	@CrossOrigin
	public ResponseEntity<List<ProgramSubscriptionDTO>> getRecentlySubscribedUsers(@RequestParam("no_of_days") Integer days, @PathVariable(value = "coachId") Long coachId) {
		List<ProgramSubscription> usersResponse = userService
				.getRecentlySubscribedUsers(days, coachId);
		List<ProgramSubscriptionDTO> response = usersResponse.stream()
				.map(user -> programParser.convertToDto(user))
				.collect(Collectors.toList());
		return new ResponseEntity<List<ProgramSubscriptionDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/unassigned", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get the List of Users who have not yet been assigned with a coach.", 
	notes = "Get the List of Users who have not yet been assigned with a coach.",
	response = UserDTO.class)
	@CrossOrigin
	public ResponseEntity<List<UserDTO>> getUnassignedCoachUsers(@RequestParam(value = "coach_type_id", required = false) Long coachTypeId ) {
		List<User> usersResponse = userService
				.getUnassignedCoachUsers(coachTypeId);
		List<UserDTO> response = usersResponse.stream()
				.map(user -> userParser.convertToDto(user))
				.collect(Collectors.toList());
		return new ResponseEntity<List<UserDTO>>(response,
				HttpStatus.OK);
	}
}
