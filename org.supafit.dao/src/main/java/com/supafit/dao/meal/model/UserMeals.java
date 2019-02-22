package com.supafit.dao.meal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("UserMeals")
@Entity
@Table(name="user_meals")
@NamedQueries({
	@NamedQuery(name = "UserMeals.findAll", query = "select m from UserMeals m"),
	@NamedQuery(name = "UserMeals.deleteByMealId", query = "DELETE FROM UserMeals m where m.mealId = :mealId"),
	@NamedQuery(name= "UserMeals.findByMealId", query = "SELECT m FROM UserMeals m where m.mealId = :mealId")
})
public class UserMeals {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="meal_id")
	private long mealId;
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "meal_id", referencedColumnName = "id")
	private Meals meal;*/
	@Column(name="item")
	private String item;
	@Column(name="quantity")
	private Double quantity;
	@Column(name="item_measure_id")
	private Long itemMeasureId;
	@Column(name="calories")
	private String calories;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	/*public Meals getMeal() {
		return meal;
	}
	public void setMeal(Meals meal) {
		this.meal = meal;
	}*/
	public String getItem() {
		return item;
	}
	public long getMealId() {
		return mealId;
	}
	public void setMealId(long mealId) {
		this.mealId = mealId;
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
	public Long getItemMeasureId() {
		return itemMeasureId;
	}
	public void setItemMeasureId(Long itemMeasureId) {
		this.itemMeasureId = itemMeasureId;
	}
	public String getCalories() {
		return calories;
	}
	public void setCalories(String calories) {
		this.calories = calories;
	}
}
