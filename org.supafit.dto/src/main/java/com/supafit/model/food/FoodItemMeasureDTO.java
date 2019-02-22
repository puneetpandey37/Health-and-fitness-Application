package com.supafit.model.food;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("foodItemMeasure")
public class FoodItemMeasureDTO {
	private long id;
	private long foodItemId;
	private String parameter;
	private Double quantity;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFoodItemId() {
		return foodItemId;
	}
	public void setFoodItemId(long foodItemId) {
		this.foodItemId = foodItemId;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
}
