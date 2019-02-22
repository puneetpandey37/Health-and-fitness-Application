package com.supafit.model.food;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("foodTypes")
public class FoodTypesDTO {
	private long id;
	private String type;
	private String description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
