package com.supafit.model.coach;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CoachSignupRequestDTO {

	private String name;
	@Email(message="Not a valid email address")
	private String email;
	@NotEmpty(message="Password cannot be empty")
	@NotNull(message="Password cannot be null")
	@Length(min=5, max= 10, message="Password lenght should be between 5 to 10")
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
