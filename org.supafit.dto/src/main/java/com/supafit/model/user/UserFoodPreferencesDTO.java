package com.supafit.model.user;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("UserFoodPreferences")
public class UserFoodPreferencesDTO {
	private long id;
	private long userId;
	private long foodPreferenceId;
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
	public long getFoodPreferenceId() {
		return foodPreferenceId;
	}
	public void setFoodPreferenceId(long foodPreferenceId) {
		this.foodPreferenceId = foodPreferenceId;
	}
}
