package com.supafit.bo.meal;

import java.util.List;

import com.supafit.dao.meal.model.UserMeals;
import com.wordnik.swagger.annotations.ApiModel;
@ApiModel("MealRequest")
public class MealRequestDTO {

	private long id;
	private long coachId;
	private String mealName;
	private List<UserMeals> meals;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMealName() {
		return mealName;
	}
	public void setMealName(String mealName) {
		this.mealName = mealName;
	}
	public List<UserMeals> getMeals() {
		return meals;
	}
	public void setMeals(List<UserMeals> meals) {
		this.meals = meals;
	}
	public long getCoachId() {
		return coachId;
	}
	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}
}
