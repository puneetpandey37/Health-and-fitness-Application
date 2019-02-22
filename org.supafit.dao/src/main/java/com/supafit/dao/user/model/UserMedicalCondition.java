package com.supafit.dao.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="user_medical_condition")
@NamedQueries({
    @NamedQuery(name = "UserMedicalCondition.findByUserId", query = "SELECT p FROM UserMedicalCondition p where p.userId = :userId"),
    @NamedQuery(name = "UserMedicalCondition.deleteByUserId", query = "DELETE FROM UserMedicalCondition m where m.userId = :userId")
    })
@ApiModel("UserMedicalCondition")
public class UserMedicalCondition {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="user_id")
	private long userId;
	@Column(name="medical_condition_id")
	private long medicalConditionId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getMedicalConditionId() {
		return medicalConditionId;
	}
	public void setMedicalConditionId(long medicalConditionId) {
		this.medicalConditionId = medicalConditionId;
	}
}
