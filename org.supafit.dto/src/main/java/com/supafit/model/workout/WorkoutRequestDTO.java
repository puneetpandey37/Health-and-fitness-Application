package com.supafit.model.workout;

import java.util.List;

public class WorkoutRequestDTO {

	private long id;
	private long userId;
	private String workoutName;
	private List<UserWorkoutDTO> workouts;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
