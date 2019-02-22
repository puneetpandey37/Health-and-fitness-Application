package com.supafit.controller.plan;

import java.text.ParseException;
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

import com.supafit.bo.plan.PlanService;
import com.supafit.common.parser.PlanParser;
import com.supafit.dao.exercise.model.Timing;
import com.supafit.dao.food.model.MealTimings;
import com.supafit.dao.plan.model.MealPlanDetail;
import com.supafit.dao.plan.model.MealPlans;
import com.supafit.dao.plan.model.WorkoutPlanDetail;
import com.supafit.dao.plan.model.WorkoutPlans;
import com.supafit.model.exercise.TimingDTO;
import com.supafit.model.food.MealTimingsDTO;
import com.supafit.model.plan.MealPlanDetailDTO;
import com.supafit.model.plan.MealPlansDTO;
import com.supafit.model.plan.WorkoutPlansDTO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
//import com.supafit.model.plan.MealPlanResponse;

@Controller
@RequestMapping("/plans")
@Api(value="plans", description="Operations pertaining to workout and meal plans")
@CrossOrigin
public class PlanController {

	private PlanService planService;
	private PlanParser planParser;
	@Autowired
	public void setPlanParser(PlanParser planParser) {
		this.planParser = planParser;
	}

	@Autowired
	public void setPlanService(PlanService planService) {
		this.planService = planService;
	}
	
	@RequestMapping(value = "/meal/timings", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get defined timings for meals", notes = "Get defined timings for meals", response = MealTimingsDTO.class)
	public ResponseEntity<List<MealTimingsDTO>> getDefinedMealTimings() throws ParseException {
		List<MealTimings> mealTimingsResponse = planService
				.getDefinedMealTimings();
		List<MealTimingsDTO> response = mealTimingsResponse
				.stream()
				.map(plan -> planParser
						.convertToDto(plan))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MealTimingsDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/meal/users/{userId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get Meal Plan of the user", notes = "Get Meal Plan of the user", response = MealPlansDTO.class)
	public ResponseEntity<List<MealPlansDTO>> getUserMealPlan(@PathVariable(value="userId") Long userId, 
			@RequestParam("start_date") String startDate,
			@RequestParam("end_date") String endDate) throws ParseException {
		List<MealPlans> mealPlanResponse = planService
				.getUserMealPlan(userId, startDate, endDate);
		List<MealPlansDTO> response = mealPlanResponse
				.stream()
				.map(mealPlan -> planParser
						.convertToDto(mealPlan))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MealPlansDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/meal", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Create Meal Plan of a user", notes = "Create Meal Plan of a user", response = MealPlansDTO.class)
	public ResponseEntity<List<MealPlansDTO>> createUserMealPlan(@RequestBody List<MealPlansDTO> mealPlansDTO) {
		List<MealPlans> mealPlanEntities = mealPlansDTO
				.stream()
				.map(mealPlan -> planParser
						.convertToEntity(mealPlan))
				.collect(Collectors.toList());
		List<MealPlans> medConditionResponse = planService
				.createUserMealPlan(mealPlanEntities);
		List<MealPlansDTO> response = medConditionResponse
				.stream()
				.map(mealPlan -> planParser
						.convertToDto(mealPlan))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MealPlansDTO>>(response,
				HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/meal", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update Meal Plan of a user", notes = "Update Meal Plan of a user", response = MealPlansDTO.class)
	public ResponseEntity<List<MealPlansDTO>> updateUserMealPlan(@RequestBody List<MealPlansDTO> mealPlansDTO) {
		List<MealPlans> mealPlanEntities = mealPlansDTO
				.stream()
				.map(mealPlan -> planParser
						.convertToEntity(mealPlan))
				.collect(Collectors.toList());
		List<MealPlans> medConditionResponse = planService
				.createUserMealPlan(mealPlanEntities);
		List<MealPlansDTO> response = medConditionResponse
				.stream()
				.map(mealPlan -> planParser
						.convertToDto(mealPlan))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MealPlansDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/meal/dietitian/{dietitianId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get Meal Plan of all the users under a dietitian", notes = "Get Meal Plan of all the users under a dietitian", response = MealPlansDTO.class)
	public ResponseEntity<List<MealPlansDTO>> getUserMealPlanOfAllUsersUnderADietitan(@PathVariable(value="dietitianId") Long dietitianId) {
		List<MealPlans> mealPlanResponse = planService
				.getUserMealPlanOfAllUsersUnderADietitan(dietitianId);
		List<MealPlansDTO> response = mealPlanResponse
				.stream()
				.map(mealPlan -> planParser
						.convertToDto(mealPlan))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MealPlansDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/meal/notcompleted/dietitian/{dietitianId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get Meal Plan of all the users  under a dietitian who have not completed their tasks in last n days", notes = "Get Meal Plan of all the users  under a dietitian who have not completed their tasks in last n days", response = MealPlanDetailDTO.class)
	public ResponseEntity<List<MealPlanDetailDTO>> getNotCopletedMealPlanStatusOfNDays(@RequestParam(value="days", required = false) Integer days, @PathVariable(value="dietitianId") Long dietitianId) {
		List<MealPlanDetail> mealPlanResponse = planService
				.getNotCopletedMealPlanStatusOfNDays(dietitianId, days);
		List<MealPlanDetailDTO> response = mealPlanResponse
				.stream()
				.map(mealPlan -> planParser
						.convertToDto(mealPlan))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MealPlanDetailDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/meal/completed/dietitian/{dietitianId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get Meal Plan of all the users under a dietitian who have completed their tasks in last n days", notes = "Get Meal Plan of all the users  under a dietitian who have completed their tasks in last n days", response = MealPlanDetailDTO.class)
	public ResponseEntity<List<MealPlanDetailDTO>> getCopletedMealPlanStatusOfNDays(@RequestParam(value="days", required = false) Integer days, @PathVariable(value="dietitianId") Long dietitianId) {
		List<MealPlanDetail> mealPlanResponse = planService
				.getCopletedMealPlanStatusOfNDays(dietitianId, days);
		List<MealPlanDetailDTO> response = mealPlanResponse
				.stream()
				.map(mealPlan -> planParser
						.convertToDto(mealPlan))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MealPlanDetailDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/workout/timings", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get defined timings for Workouts", notes = "Get defined timings for Workouts", response = TimingDTO.class)
	public ResponseEntity<List<TimingDTO>> getDefinedWorkoutTimings() throws ParseException {
		List<Timing> timingResponse = planService
				.getDefinedWorkoutTimings();
		List<TimingDTO> response = timingResponse
				.stream()
				.map(timing -> planParser
						.convertToDto(timing))
				.collect(Collectors.toList());
		return new ResponseEntity<List<TimingDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/workout/users/{userId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get Workout Plan of the user", notes = "Get Workout Plan of the user", response = WorkoutPlansDTO.class)
	public ResponseEntity<List<WorkoutPlansDTO>> getUserWorkoutPlan(@PathVariable(value="userId") Long userId, 
			@RequestParam("start_date") String startDate,
			@RequestParam("end_date") String endDate) throws ParseException {
		List<WorkoutPlans> workoutPlanResponse = planService
				.getUserWorkoutPlan(userId, startDate, endDate);
		List<WorkoutPlansDTO> response = workoutPlanResponse
				.stream()
				.map(workoutPlan -> planParser
						.convertToDto(workoutPlan))
				.collect(Collectors.toList());
		return new ResponseEntity<List<WorkoutPlansDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/workout", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Create Workout Plan of the user", notes = "Create Workout Plan of the user", response = WorkoutPlansDTO.class)
	public ResponseEntity<List<WorkoutPlansDTO>> createUserWorkoutPlan(@RequestBody List<WorkoutPlansDTO> workoutPlansDTO) {
		List<WorkoutPlans> workoutPlanEntities = workoutPlansDTO
				.stream()
				.map(workoutPlan -> planParser
						.convertToEntity(workoutPlan))
				.collect(Collectors.toList());
		List<WorkoutPlans> medConditionResponse = planService
				.createUserWorkoutPlan(workoutPlanEntities);
		List<WorkoutPlansDTO> response = medConditionResponse
				.stream()
				.map(workoutPlan -> planParser
						.convertToDto(workoutPlan))
				.collect(Collectors.toList());
		return new ResponseEntity<List<WorkoutPlansDTO>>(response,
				HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/workout", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update Workout Plan of the user", notes = "Update Workout Plan of the user", response = WorkoutPlansDTO.class)
	public ResponseEntity<List<WorkoutPlansDTO>> updateUserWorkoutPlan(@RequestBody List<WorkoutPlansDTO> workoutPlansDTO) {
		List<WorkoutPlans> workoutPlanEntities = workoutPlansDTO
				.stream()
				.map(workoutPlan -> planParser
						.convertToEntity(workoutPlan))
				.collect(Collectors.toList());
		List<WorkoutPlans> medConditionResponse = planService
				.createUserWorkoutPlan(workoutPlanEntities);
		List<WorkoutPlansDTO> response = medConditionResponse
				.stream()
				.map(workoutPlan -> planParser
						.convertToDto(workoutPlan))
				.collect(Collectors.toList());
		return new ResponseEntity<List<WorkoutPlansDTO>>(response,
				HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/workout/trainer/{trainerId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get Workout Plan of all the users undaer a trainer", notes = "Get Workout Plan of all the users undaer a trainer", response = WorkoutPlansDTO.class)
	public ResponseEntity<List<WorkoutPlansDTO>> getUserMealPlanOfAllUsersUnderATrainer(@PathVariable(value="trainerId") Long trainerId) {
		List<WorkoutPlans> workoutPlanResponse = planService
				.getUserMealPlanOfAllUsersUnderATrainer(trainerId);
		List<WorkoutPlansDTO> response = workoutPlanResponse
				.stream()
				.map(workoutPlan -> planParser
						.convertToDto(workoutPlan))
				.collect(Collectors.toList());
		return new ResponseEntity<List<WorkoutPlansDTO>>(response,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/workout/notcompleted/trainer/{trainerId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get Workout Plan of all the users under a trainer, who have not completed their tasks in last n days", notes = "Get Workout Plan of all the users  under a trainer who have not completed their tasks in last n days", response = WorkoutPlansDTO.class)
	public ResponseEntity<List<WorkoutPlanDetail>> getNotCopletedWorkoutPlanStatusOfNDays(@RequestParam(value="days", required = false) Integer days, @PathVariable(value="trainerId") Long trainerId) {
		List<WorkoutPlanDetail> workoutPlanDetailResponse = planService.getNotCopletedWorkoutPlanStatusOfNDays(trainerId, days);
		return new ResponseEntity<List<WorkoutPlanDetail>>(workoutPlanDetailResponse,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/workout/completed/trainer/{trainerId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get Workout Plan of all the users under a trainer who have completed their tasks in last n days", notes = "Get Workout Plan of all the users  under a trainer who have completed their tasks in last n days", response = WorkoutPlansDTO.class)
	public ResponseEntity<List<WorkoutPlanDetail>> getCopletedWorkoutPlanStatusOfNDays(@RequestParam(value="days", required = false) Integer days, @PathVariable(value="trainerId") Long trainerId) {
		List<WorkoutPlanDetail> workoutPlanDetailResponse = planService.getCopletedWorkoutPlanStatusOfNDays(trainerId, days);
		return new ResponseEntity<List<WorkoutPlanDetail>>(workoutPlanDetailResponse,
				HttpStatus.OK);
	}
	
}
