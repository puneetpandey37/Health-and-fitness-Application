package com.supafit.model.exercise;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class ExerciseCategoryResponse extends ResourceSupport{

	private List<ExerciseCategoryDTO> category;
	public List<ExerciseCategoryDTO> getCategory() {
		return category;
	}
	public void setCategory(List<ExerciseCategoryDTO> category) {
		this.category = category;
	}
}
