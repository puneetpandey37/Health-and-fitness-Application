package com.supafit.model.coach;


public class CoachTypeDTO {

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
	/*public List<Coach> getCoaches() {
		return coaches;
	}
	public void setCoaches(List<Coach> coaches) {
		this.coaches = coaches;
	}*/
}
