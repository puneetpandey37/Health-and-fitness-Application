package com.supafit.model.plan;

import com.supafit.model.food.FoodItemMeasureDTO;
import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("MealPlanStatusDetail")
public class MealPlanStatusDetailDTO {

	public MealPlanStatusDetailDTO(){}
	public MealPlanStatusDetailDTO(long id, Long mealPlanDetailId,
			String item, Double quantity, FoodItemMeasureDTO foodItemMeasure) {
		super();
		this.id = id;
		this.mealPlanDetailId = mealPlanDetailId;
		this.item = item;
		this.quantity = quantity;
		this.foodItemMeasure = foodItemMeasure;
	}
	private long id;
	private Long mealPlanDetailId;
	private String item;
	private Double quantity;
	private FoodItemMeasureDTO foodItemMeasure;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getMealPlanDetailId() {
		return mealPlanDetailId;
	}
	public void setMealPlanDetailId(Long mealPlanDetailId) {
		this.mealPlanDetailId = mealPlanDetailId;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public FoodItemMeasureDTO getFoodItemMeasure() {
		return foodItemMeasure;
	}
	public void setFoodItemMeasure(FoodItemMeasureDTO foodItemMeasure) {
		this.foodItemMeasure = foodItemMeasure;
	}
}
