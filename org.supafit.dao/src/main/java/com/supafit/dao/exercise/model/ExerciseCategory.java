package com.supafit.dao.exercise.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "exercise_category")
@NamedQueries({ 
	@NamedQuery(name = "ExerciseCategory.findAll", query = "select ec from ExerciseCategory ec") 
	})
@ApiModel("exerciseCategory")
public class ExerciseCategory {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="category")
	@ApiModelProperty(value="category")
	private String category;
	@Column(name="description")
	@ApiModelProperty(value="description")
	private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
