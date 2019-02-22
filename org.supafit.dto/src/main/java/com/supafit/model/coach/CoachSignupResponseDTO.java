package com.supafit.model.coach;

import org.springframework.hateoas.ResourceSupport;

import com.wordnik.swagger.annotations.ApiModel;
@ApiModel("CoachSignupResponseDTO")
public class CoachSignupResponseDTO extends ResourceSupport{

	private String coachId;
	public String getCoachId() {
		return coachId;
	}
	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}
}
