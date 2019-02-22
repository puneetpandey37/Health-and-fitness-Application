package com.supafit.controller.workout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supafit.bo.workout.WorkoutRequestDTO;
import com.supafit.bo.workout.WorkoutService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/workouts")
@Api(value = "workouts", description = "Operations pertaining to workouts")
@CrossOrigin
public class WorkoutController {

	private WorkoutService workoutService;
	@Autowired
	public void setWorkoutService(WorkoutService workoutService) {
		this.workoutService = workoutService;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Create Meal", notes = "Create Workout", response = WorkoutRequestDTO.class)
	public WorkoutRequestDTO createWorkout(@RequestBody WorkoutRequestDTO workoutRequest) throws Exception {
		return workoutService.createWorkout(workoutRequest);
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update Workout", notes = "Update Workout", response = WorkoutRequestDTO.class)
	public WorkoutRequestDTO updateWorkout(@RequestBody WorkoutRequestDTO workoutRequest) throws Exception {
		return workoutService.updateWorkout(workoutRequest);
	}
	
	@RequestMapping(value="/user/{userId}",method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get Workouts", notes = "Get Workouts", response = WorkoutRequestDTO.class)
	public List<WorkoutRequestDTO> getWorkoutsByUserId(@PathVariable(value="userId") Long userId) throws Exception {
		return workoutService.getWorkoutsByUserId(userId);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get Workouts", notes = "Get Workouts", response = WorkoutRequestDTO.class)
	public List<WorkoutRequestDTO> getWorkouts() throws Exception {
		return workoutService.getWorkouts();
	}
	
	@RequestMapping(value="{workoutId}",method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Delete Meals", notes = "Delete Meals")
	public String deleteWorkout(@PathVariable("workoutId") Long workoutId) throws Exception {
		return workoutService.deleteWorkout(workoutId);
	}
}
