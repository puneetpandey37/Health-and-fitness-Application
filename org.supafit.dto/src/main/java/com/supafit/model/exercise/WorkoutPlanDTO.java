package com.supafit.model.exercise;

import java.util.List;

import com.supafit.model.coach.CoachDTO;

public class WorkoutPlanDTO {

	private long userId;
	private CoachDTO coach;
    private List<WorkoutDetailDTO> details;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public CoachDTO getCoach() {
		return coach;
	}
	public void setCoach(CoachDTO coach) {
		this.coach = coach;
	}
	public List<WorkoutDetailDTO> getDetails() {
		return details;
	}
	public void setDetails(List<WorkoutDetailDTO> details) {
		this.details = details;
	}
}
