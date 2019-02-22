package com.supafit.dao.plan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="meal_template")
@NamedQueries({
	@NamedQuery(name = "MealTemplate.findAll", query = "select mt from MealTemplate mt"),
	@NamedQuery(name = "MealTemplate.findByPlanId", query = "select mt from MealTemplate mt where mt.id = :id"),
	@NamedQuery(name = "MealTemplate.findByUserId", query = "select mt from MealTemplate mt where mt.userId = :userId"),
	@NamedQuery(name = "MealTemplate.findByUserIdAndDate", query = "select mt from MealTemplate mt where mt.userId = :userId AND mt.date = :date AND mt.mealTimingsId = :mealTimingsId"),
	@NamedQuery(name = "MealTemplate.setMealStatus", query = "UPDATE MealTemplate mt SET mt.status = :status WHERE mt.id = :planId")
})
@ApiModel("mealTemplate")
public class MealTemplate {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="user_id")
	private long userId;
	@Column(name="coach_id")
	private long coachId;
	@Column(name="meal_id")
	private long mealId;
	@Column(name="food_item_measure_id")
	private long foodItemMeasureId;
	@Column(name="meal_timing_id")
	private long mealTimingsId;
	@Column(name="special_instructions")
	private String instructions;
	@Column(name="date")
	private String date;
	@Column(name="status")
	private int status;
	@Column(name="remark_user")
	private String userRemarks;
	@Column(name="remark_coach")
	private String coachRemarks;
	
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
	public long getCoachId() {
		return coachId;
	}
	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}
	public long getMealId() {
		return mealId;
	}
	public void setMealId(long mealId) {
		this.mealId = mealId;
	}
	public long getFoodItemMeasureId() {
		return foodItemMeasureId;
	}
	public void setFoodItemMeasureId(long foodItemMeasureId) {
		this.foodItemMeasureId = foodItemMeasureId;
	}
	public long getMealTimingsId() {
		return mealTimingsId;
	}
	public void setMealTimingsId(long mealTimingsId) {
		this.mealTimingsId = mealTimingsId;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUserRemarks() {
		return userRemarks;
	}
	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}
	public String getCoachRemarks() {
		return coachRemarks;
	}
	public void setCoachRemarks(String coachRemarks) {
		this.coachRemarks = coachRemarks;
	}
}
