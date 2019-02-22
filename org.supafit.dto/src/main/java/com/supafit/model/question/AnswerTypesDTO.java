package com.supafit.model.question;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("AnswerTypes")
public class AnswerTypesDTO {

	private long id;
	private String type;
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
}
