package com.supafit.model.user;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("UserFitnessGoal")
public class UserFitnessGoalDTO {
	private long id;
	private long userId;
	private long fitnessGoalId;
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
	public long getFitnessGoalId() {
		return fitnessGoalId;
	}
	public void setFitnessGoalId(long fitnessGoalId) {
		this.fitnessGoalId = fitnessGoalId;
	}
}
