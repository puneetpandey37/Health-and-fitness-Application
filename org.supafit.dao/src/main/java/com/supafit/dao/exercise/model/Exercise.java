package com.supafit.dao.exercise.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "exercise")
@NamedQueries({ 
	@NamedQuery(name = "Exercise.findAll", query = "select e from Exercise e"),
	@NamedQuery(name = "Exercise.findByCategory", query = "select e from Exercise e where e.exerciseCategoryId = :id"),
	@NamedQuery(name = "Exercise.findById", query = "select e from Exercise e where e.id = :id")
	})
public class Exercise {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="exercise_category_id")
	private long exerciseCategoryId;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="precautions")
	private String precautions;
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
	public long getExerciseCategoryId() {
		return exerciseCategoryId;
	}
	public void setExerciseCategoryId(long exerciseCategoryId) {
		this.exerciseCategoryId = exerciseCategoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrecautions() {
		return precautions;
	}
	public void setPrecautions(String precautions) {
		this.precautions = precautions;
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
