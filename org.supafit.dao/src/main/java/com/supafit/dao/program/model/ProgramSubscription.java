package com.supafit.dao.program.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.supafit.dao.user.model.User;
import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="program_subscription")
@NamedQueries({
    @NamedQuery(name = "ProgramSubscription.getAll", query = "SELECT p FROM ProgramSubscription p"),
    @NamedQuery(name = "ProgramSubscription.findByDate", query = "SELECT p FROM ProgramSubscription p WHERE p.startDate >= :startDate"),
    @NamedQuery(name = "ProgramSubscription.findByCoachId", query = "SELECT p FROM ProgramSubscription p WHERE p.startDate >= :startDate and p.coachId = :coachId"),
    @NamedQuery(name = "ProgramSubscription.findByDietitianId", query = "SELECT p FROM ProgramSubscription p WHERE p.startDate >= :startDate and p.dietitianId = :dietitianId")
})
@ApiModel("programSubscription")
public class ProgramSubscription {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private Long id;
	/*@Column(name="user_id")
	private Long userId;*/
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	@JsonBackReference
	private User user;
	@Column(name="program_id")
	private Long programId;
	@Column(name="coach_id")
	private Long coachId;
	@Column(name="dietitian_id")
	private Long dietitianId;
	@Column(name="detail")
	private String detail;
	@Column(name="start_date")
	private String startDate;
	@Column(name="end_date")
	private String endDate;
	@Column(name="cost")
	private String cost;
	@Column(name="status")
	private String status;
	@Column(name="discount")
	private String discount;
	@Column(name="remark")
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
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
