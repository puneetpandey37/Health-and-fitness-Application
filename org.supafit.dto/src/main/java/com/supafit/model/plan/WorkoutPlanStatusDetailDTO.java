package com.supafit.model.plan;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("WorkoutPlanStatusDetail")
public class WorkoutPlanStatusDetailDTO {

	private long id;
	private Long workoutPlanDetailId;
	private String exercise;
	private Double duration;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getWorkoutPlanDetailId() {
		return workoutPlanDetailId;
	}
	public void setWorkoutPlanDetailId(Long workoutPlanDetailId) {
		this.workoutPlanDetailId = workoutPlanDetailId;
	}
	public String getExercise() {
		return exercise;
	}
	public void setExercise(String exercise) {
		this.exercise = exercise;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
}
