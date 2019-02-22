package com.supafit.model.plan;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.supafit.model.food.MealTimingsDTO;
import com.supafit.model.meal.MealsDTO;
import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("MealPlanDetail")
public class MealPlanDetailDTO {
	private long id;
	private String planDate;
	@JsonBackReference
	private MealPlansDTO mealPlans;
	private MealsDTO meal;
	private MealTimingsDTO mealTiming;
	private String specialInstructions;
	private String coachRemarks;
	private List<MealPlanStatusDetailDTO> mealPlanStatus;
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
	/*public Long getMealPlanId() {
		return mealPlanId;
	}
	public void setMealPlanId(Long mealPlanId) {
		this.mealPlanId = mealPlanId;
	}*/
	public MealsDTO getMeal() {
		return meal;
	}
	public MealPlansDTO getMealPlans() {
		return mealPlans;
	}
	public void setMealPlans(MealPlansDTO mealPlans) {
		this.mealPlans = mealPlans;
	}
	public void setMeal(MealsDTO meal) {
		this.meal = meal;
	}
	public MealTimingsDTO getMealTiming() {
		return mealTiming;
	}
	public void setMealTiming(MealTimingsDTO mealTiming) {
		this.mealTiming = mealTiming;
	}
	public String getSpecialInstructions() {
		return specialInstructions;
	}
	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}
	public String getCoachRemarks() {
		return coachRemarks;
	}
	public void setCoachRemarks(String coachRemarks) {
		this.coachRemarks = coachRemarks;
	}
	/*public List<MealPlanStatus> getMealPlanStatus() {
		return mealPlanStatus;
	}
	public void setMealPlanStatus(List<MealPlanStatus> mealPlanStatus) {
		this.mealPlanStatus = mealPlanStatus;
	}*/
	/*public MealPlanStatus getMealPlanStatus() {
		return mealPlanStatus;
	}
	public void setMealPlanStatus(MealPlanStatus mealPlanStatus) {
		this.mealPlanStatus = mealPlanStatus;
	}*/
	/*public List<MealPlanStatus> getMealPlanStatus() {
		return mealPlanStatus;
	}
	public void setMealPlanStatus(List<MealPlanStatus> mealPlanStatus) {
		this.mealPlanStatus = mealPlanStatus;
	}*/
	public Integer getPlanStatus() {
		return planStatus;
	}
	public List<MealPlanStatusDetailDTO> getMealPlanStatus() {
		return mealPlanStatus;
	}
	public void setMealPlanStatus(List<MealPlanStatusDetailDTO> mealPlanStatus) {
		this.mealPlanStatus = mealPlanStatus;
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
