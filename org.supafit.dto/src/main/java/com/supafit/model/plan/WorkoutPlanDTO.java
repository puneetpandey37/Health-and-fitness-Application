package com.supafit.model.plan;

import java.util.List;

import com.supafit.model.workout.UserWorkoutDTO;

public class WorkoutPlanDTO {

	private String timing;
	private List<UserWorkoutDTO> items;
	public String getTiming() {
		return timing;
	}
	public void setTiming(String timing) {
		this.timing = timing;
	}
	public List<UserWorkoutDTO> getItems() {
		return items;
	}
	public void setItems(List<UserWorkoutDTO> items) {
		this.items = items;
	}
}
