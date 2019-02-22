package com.supafit.dao.coach.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="coach_phone_number")
@NamedQueries({
    @NamedQuery(name = "CoachPhoneNumber.findByCoachId", query = "SELECT p FROM CoachPhoneNumber p where p.coachId = :coachId")
})
@ApiModel("CoachPhoneNumber")
public class CoachPhoneNumber {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="coach_id")
	private long coachId;
	@Column(name="type")
	private String type;
	@Column(name="number")
	private String number;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCoachId() {
		return coachId;
	}
	public void setCoachId(long coachId) {
		this.coachId = coachId;
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
}
