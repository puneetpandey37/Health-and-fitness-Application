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
@Table(name="food_preferences")
@NamedQueries({
	@NamedQuery(name = "FoodPreferences.findAll", query = "select ft from FoodPreferences ft"),
	@NamedQuery(name = "FoodPreferences.findById", query = "select ft from FoodPreferences ft where ft.id = :id"),
	@NamedQuery(name = "FoodPreferences.deleteByfoodPreferenceId", query = "DELETE FROM FoodPreferences m where m.id = :foodPreferenceId")
})
@ApiModel("FoodPreferences")
public class FoodPreferences {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="cuisine")
	private String cuisine;
	@Column(name="description")
	private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
