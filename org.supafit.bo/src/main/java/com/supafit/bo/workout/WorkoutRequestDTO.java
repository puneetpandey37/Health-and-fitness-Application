package com.supafit.bo.workout;

import java.util.List;

import com.supafit.dao.workout.model.UserWorkout;

public class WorkoutRequestDTO {

	private long id;
	private long userId;
	private String workoutName;
	private List<UserWorkout> workouts;
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
	public List<UserWorkout> getWorkouts() {
		return workouts;
	}
	public void setWorkouts(List<UserWorkout> workouts) {
		this.workouts = workouts;
	}
}
