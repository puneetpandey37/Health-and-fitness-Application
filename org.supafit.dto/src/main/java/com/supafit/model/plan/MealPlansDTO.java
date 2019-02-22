package com.supafit.model.plan;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("MealPlans")
public class MealPlansDTO {
	private long id;
	private Long userId;
	private Long dietitianId;
	private String dateCreated;
	private String dietitianRemarks;
	@JsonManagedReference
	private List<MealPlanDetailDTO> mealPlanDetails;
	
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
	public Long getDietitianId() {
		return dietitianId;
	}
	public void setDietitianId(Long dietitianId) {
		this.dietitianId = dietitianId;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDietitianRemarks() {
		return dietitianRemarks;
	}
	public void setDietitianRemarks(String dietitianRemarks) {
		this.dietitianRemarks = dietitianRemarks;
	}
	public List<MealPlanDetailDTO> getMealPlanDetails() {
		return mealPlanDetails;
	}
	public void setMealPlanDetails(List<MealPlanDetailDTO> mealPlanDetails) {
		this.mealPlanDetails = mealPlanDetails;
	}
}
