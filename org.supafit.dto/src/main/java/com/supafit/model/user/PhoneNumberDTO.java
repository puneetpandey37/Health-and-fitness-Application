package com.supafit.model.user;

import com.wordnik.swagger.annotations.ApiModel;
@ApiModel("phoneNumber")
public class PhoneNumberDTO {
	private long id;
	private long userId;
	private String type;
	private String number;
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
