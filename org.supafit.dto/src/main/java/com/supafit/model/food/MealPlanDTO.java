package com.supafit.model.food;

import java.util.List;

import com.supafit.model.coach.CoachDTO;


public class MealPlanDTO {

	private long userId;
	private CoachDTO coach;
    private List<FoodDetailDTO> details;
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
	public List<FoodDetailDTO> getDetails() {
		return details;
	}
	public void setDetails(List<FoodDetailDTO> details) {
		this.details = details;
	}
}
