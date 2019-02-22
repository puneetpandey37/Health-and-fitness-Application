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
@Table(name="meal_timings")
@NamedQueries({
	@NamedQuery(name = "MealTimings.findAll", query = "select mt from MealTimings mt"),
	@NamedQuery(name = "MealTimings.findById", query = "select mt from MealTimings mt where mt.id = :id")
})
@ApiModel("mealTimings")
public class MealTimings {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="type")
	private String type;
	@Column(name="timings")
	private String timings;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTimings() {
		return timings;
	}
	public void setTimings(String timings) {
		this.timings = timings;
	}
}
