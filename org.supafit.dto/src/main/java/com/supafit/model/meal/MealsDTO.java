package com.supafit.model.meal;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
@ApiModel("Meals")
public class MealsDTO {
	private long id;
	private String name;
	private Long dietitianId;
	private List<UserMealsDTO> meals;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getDietitianId() {
		return dietitianId;
	}
	public void setDietitianId(Long dietitianId) {
		this.dietitianId = dietitianId;
	}
	public List<UserMealsDTO> getMeals() {
		return meals;
	}
	public void setMeals(List<UserMealsDTO> meals) {
		this.meals = meals;
	}
}
