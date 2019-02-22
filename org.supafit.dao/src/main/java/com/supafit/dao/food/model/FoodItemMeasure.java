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
@Table(name="food_item_measure")
@NamedQueries({
	@NamedQuery(name = "FoodItemMeasure.findAll", query = "select fm from FoodItemMeasure fm"),
	@NamedQuery(name = "FoodItemMeasure.findById", query = "select fm from FoodItemMeasure fm where id = :id")
})
@ApiModel("foodItemMeasure")
public class FoodItemMeasure {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="food_item_id")
	private long foodItemId;
	@Column(name="parameter")
	private String parameter;
	@Column(name="quantity")
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
