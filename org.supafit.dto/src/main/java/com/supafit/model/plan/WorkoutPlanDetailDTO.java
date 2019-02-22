package com.supafit.model.plan;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.supafit.model.exercise.TimingDTO;
import com.supafit.model.workout.WorkoutDTO;
import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("WorkoutPlanDetail")
public class WorkoutPlanDetailDTO {

	private long id;
	private String planDate;
	@JsonBackReference
	private WorkoutPlansDTO workoutPlans;
	private WorkoutDTO workout;
	private TimingDTO workoutTiming;
	private String specialInstructions;
	private String trainerRemarks;
	private List<WorkoutPlanStatusDetailDTO> workoutPlanStatus;
	private Integer planStatus;
	private String userRemarks;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPlanDate() {
		return planDate;
	}
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	public WorkoutPlansDTO getWorkoutPlans() {
		return workoutPlans;
	}
	public void setWorkoutPlans(WorkoutPlansDTO workoutPlans) {
		this.workoutPlans = workoutPlans;
	}
	public WorkoutDTO getWorkout() {
		return workout;
	}
	public void setWorkout(WorkoutDTO workout) {
		this.workout = workout;
	}
	public TimingDTO getWorkoutTiming() {
		return workoutTiming;
	}
	public void setWorkoutTiming(TimingDTO workoutTiming) {
		this.workoutTiming = workoutTiming;
	}
	public String getSpecialInstructions() {
		return specialInstructions;
	}
	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}
	public String getTrainerRemarks() {
		return trainerRemarks;
	}
	public void setTrainerRemarks(String trainerRemarks) {
		this.trainerRemarks = trainerRemarks;
	}
	public List<WorkoutPlanStatusDetailDTO> getWorkoutPlanStatus() {
		return workoutPlanStatus;
	}
	public void setWorkoutPlanStatus(List<WorkoutPlanStatusDetailDTO> workoutPlanStatus) {
		this.workoutPlanStatus = workoutPlanStatus;
	}
	public Integer getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(Integer planStatus) {
		this.planStatus = planStatus;
	}
	public String getUserRemarks() {
		return userRemarks;
	}
	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}
}
