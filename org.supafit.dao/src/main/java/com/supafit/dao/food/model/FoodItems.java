package com.supafit.dao.food.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="food_items")
@NamedQueries({
	@NamedQuery(name = "FoodItems.findAll", query = "select fi from FoodItems fi"),
	@NamedQuery(name = "FoodItems.findByItemId", query = "select fi from FoodItems fi where id = :foodItemId")
})
@ApiModel("foodItems")
public class FoodItems {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="food_type_id")
	private long food_type_id;
	@Column(name="food_item")
	private String foodItem;
	@Column(name="description")
	private String description;
	@Column(name="calories")
	private String calories;
	@Column(name="measure_parameter")
	private String measureParameter;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFood_type_id() {
		return food_type_id;
	}
	public void setFood_type_id(long food_type_id) {
		this.food_type_id = food_type_id;
	}
	public String getFoodItem() {
		return foodItem;
	}
	public void setFoodItem(String foodItem) {
		this.foodItem = foodItem;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCalories() {
		return calories;
	}
	public void setCalories(String calories) {
		this.calories = calories;
	}
	public String getMeasureParameter() {
		return measureParameter;
	}
	public void setMeasureParameter(String measureParameter) {
		this.measureParameter = measureParameter;
	}
}
