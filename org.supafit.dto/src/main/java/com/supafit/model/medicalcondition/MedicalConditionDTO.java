package com.supafit.model.medicalcondition;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("MedicalCondition")
public class MedicalConditionDTO {
	private long id;
	private String condition;
	private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
