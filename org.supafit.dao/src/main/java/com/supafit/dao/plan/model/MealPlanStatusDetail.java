package com.supafit.dao.plan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.supafit.dao.food.model.FoodItemMeasure;
import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="meal_plan_status_detail")
@NamedQueries({
	@NamedQuery(name = "MealPlanStatusDetail.findAll", query = "select s from MealPlanStatusDetail s"),
	@NamedQuery(name = "MealPlanStatusDetail.findByPlanStatusId", query = "select s from MealPlanStatusDetail s where s.mealPlanDetailId = :mealPlanStatusId")
})
@ApiModel("MealPlanStatusDetail")
public class MealPlanStatusDetail {

	public MealPlanStatusDetail(){}
	public MealPlanStatusDetail(long id, Long mealPlanDetailId,
			String item, Double quantity, FoodItemMeasure foodItemMeasure) {
		super();
		this.id = id;
		this.mealPlanDetailId = mealPlanDetailId;
		this.item = item;
		this.quantity = quantity;
		this.foodItemMeasure = foodItemMeasure;
	}
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="meal_plan_detail_id")
	private Long mealPlanDetailId;
	@Column(name="item")
	private String item;
	@Column(name="quantity")
	private Double quantity;
	@OneToOne
	@JoinColumn(name = "item_measure_id", referencedColumnName = "id")
	private FoodItemMeasure foodItemMeasure;
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
	public FoodItemMeasure getFoodItemMeasure() {
		return foodItemMeasure;
	}
	public void setFoodItemMeasure(FoodItemMeasure foodItemMeasure) {
		this.foodItemMeasure = foodItemMeasure;
	}
}
