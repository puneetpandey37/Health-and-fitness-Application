package com.supafit.model.exercise;


public class ExerciseDTO {

	private long id;
	private long exerciseCategoryId;
	private String name;
	private String description;
	private String precautions;
	private String calories;
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
