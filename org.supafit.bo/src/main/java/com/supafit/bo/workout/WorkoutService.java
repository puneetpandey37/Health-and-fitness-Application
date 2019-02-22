package com.supafit.bo.workout;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.workout.WorkoutManager;
import com.supafit.dao.workout.model.UserWorkout;
import com.supafit.dao.workout.model.Workout;
@Component
public class WorkoutService {

	private WorkoutManager workoutManager;
	@Autowired
	public void setWorkoutManager(WorkoutManager workoutManager) {
		this.workoutManager = workoutManager;
	}

	public WorkoutRequestDTO createWorkout(WorkoutRequestDTO workoutRequest) {
		Workout workout = new Workout();
		String workoutName = workoutRequest.getWorkoutName();
		Long userId = workoutRequest.getUserId();
		if(userId != null)
			workout.setTrainerId(userId);
		if(workoutName != null)
			workout.setWorkoutName(workoutName);
		workout = workoutManager.createWorkout(workout);
		if(workout != null)
			workoutRequest.setId(workout.getId());
		List<UserWorkout> userWorkout = workoutRequest.getWorkouts();
		List<UserWorkout> userWorkoutResponse = new ArrayList<UserWorkout>();
		if(userWorkout  != null) {
			for(UserWorkout uWorkout : userWorkout) {
				uWorkout.setWorkoutId(workout.getId());
				uWorkout = workoutManager.createWorkout(uWorkout);
				userWorkoutResponse.add(uWorkout);
			}
		}
		workoutRequest.setWorkouts(userWorkoutResponse);
		return workoutRequest;
	}
	
	public List<WorkoutRequestDTO> getWorkoutsByUserId(Long userId) {
		List<WorkoutRequestDTO> response = new ArrayList<WorkoutRequestDTO>();
		List<Workout> workouts = null; 
		if(userId != null) {
			workouts = workoutManager.getWorkoutsByUserId(userId);
		} else {
			workouts = workoutManager.getWorkouts();
		}
		if(workouts != null) {
			for(Workout workout : workouts) {
				WorkoutRequestDTO workoutResponse = new WorkoutRequestDTO();
				long workoutId = workout.getId();
				workoutResponse.setUserId(userId);
				workoutResponse.setWorkoutName(workout.getWorkoutName());
				workoutResponse.setId(workout.getId());
				List<UserWorkout> userWorkouts = workoutManager.getUserWorkoutsByWorkoutId(workoutId);
				workoutResponse.setWorkouts(userWorkouts);
				response.add(workoutResponse);
			}
		}
		return response;
	}
	
	
	public List<WorkoutRequestDTO> getWorkouts() {
		List<WorkoutRequestDTO> response = new ArrayList<WorkoutRequestDTO>();
		List<Workout> workouts = workoutManager.getWorkouts();
		if(workouts != null) {
			for(Workout workout : workouts) {
				WorkoutRequestDTO workoutResponse = new WorkoutRequestDTO();
				long workoutId = workout.getId();
				workoutResponse.setWorkoutName(workout.getWorkoutName());
				workoutResponse.setId(workout.getId());
				List<UserWorkout> userWorkouts = workoutManager.getUserWorkoutsByWorkoutId(workoutId);
				workoutResponse.setWorkouts(userWorkouts);
				response.add(workoutResponse);
			}
		}
		return response;
	}
	
	public String deleteWorkout(long workoutId) {
		String result;
		try {
			workoutManager.deleteWorkout(workoutId);
			workoutManager.deleteUserWorkout(workoutId);
			result = "success";
		} catch (Exception e) {
			result = "failed";
		}
		return result;
	}
	
	public WorkoutRequestDTO updateWorkout(WorkoutRequestDTO workoutRequest) {
		if(workoutRequest != null) {
			String workoutName = workoutRequest.getWorkoutName();
			long id = workoutRequest.getId();
			long userId = workoutRequest.getUserId();
			Workout workout = new Workout();
			workout.setId(id);
			workout.setWorkoutName(workoutName);
			workout.setTrainerId(userId);
			workoutManager.createWorkout(workout);
			List<UserWorkout> userWorkouts = workoutRequest.getWorkouts();
			if(userWorkouts != null) {
				for(UserWorkout userWorkout : userWorkouts) {
					userWorkout = workoutManager.createWorkout(userWorkout);
				}
			}
		}
		return workoutRequest;
	}
}
