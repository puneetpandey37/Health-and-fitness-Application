package com.supafit.model.plan;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("WorkoutPlans")
public class WorkoutPlansDTO {

	private long id;
	private Long userId;
	private Long trainerId;
	private String dateCreated;
	private String trainerRemarks;
	@JsonManagedReference
	private List<WorkoutPlanDetailDTO> workoutPlanDetails;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getTrainerRemarks() {
		return trainerRemarks;
	}
	public void setTrainerRemarks(String trainerRemarks) {
		this.trainerRemarks = trainerRemarks;
	}
	public List<WorkoutPlanDetailDTO> getWorkoutPlanDetails() {
		return workoutPlanDetails;
	}
	public void setWorkoutPlanDetails(List<WorkoutPlanDetailDTO> workoutPlanDetails) {
		this.workoutPlanDetails = workoutPlanDetails;
	}
}
