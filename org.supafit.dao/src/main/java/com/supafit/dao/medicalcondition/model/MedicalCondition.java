package com.supafit.dao.medicalcondition.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("MedicalCondition")
@Entity
@Table(name="medical_conditions")
@NamedQueries({
	@NamedQuery(name = "MedicalCondition.findAll", query = "select ft from MedicalCondition ft"),
	@NamedQuery(name = "MedicalCondition.findById", query = "select ft from MedicalCondition ft where ft.id = :id"),
	@NamedQuery(name = "MedicalCondition.deleteByMedicalConditionId", query = "DELETE FROM MedicalCondition m where m.id = :medicalConditionId")
})
public class MedicalCondition {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="condition")
	private String condition;
	@Column(name="description")
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
