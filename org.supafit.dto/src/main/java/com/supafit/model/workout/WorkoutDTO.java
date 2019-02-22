package com.supafit.model.workout;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("Workout")
public class WorkoutDTO {
	private long id;
	private Long trainerId;
	private String workoutName;
	private List<UserWorkoutDTO> workouts;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}
	public String getWorkoutName() {
		return workoutName;
	}
	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}
	public List<UserWorkoutDTO> getWorkouts() {
		return workouts;
	}
	public void setWorkouts(List<UserWorkoutDTO> workouts) {
		this.workouts = workouts;
	}
}
