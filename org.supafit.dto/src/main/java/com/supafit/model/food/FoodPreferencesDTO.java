package com.supafit.model.food;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("FoodPreferences")
public class FoodPreferencesDTO {
	private long id;
	private String cuisine;
	private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
