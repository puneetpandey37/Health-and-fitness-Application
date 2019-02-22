package com.supafit.model.program;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.supafit.model.user.UserDTO;
import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("programSubscription")
public class ProgramSubscriptionDTO {

	private Long id;
	@JsonBackReference
	private UserDTO user;
	private Long programId;
	private Long coachId;
	private Long dietitianId;
	private String detail;
	private String startDate;
	private String endDate;
	private String cost;
	private String status;
	private String discount;
	private String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/*public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}*/
	public Long getProgramId() {
		return programId;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public Long getCoachId() {
		return coachId;
	}
	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}
	public Long getDietitianId() {
		return dietitianId;
	}
	public void setDietitianId(Long dietitianId) {
		this.dietitianId = dietitianId;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
