package com.supafit.dao.program.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="program_type")
@NamedQueries({
    @NamedQuery(name = "ProgramType.findAll", query = "SELECT pt FROM ProgramType pt")})
@ApiModel("programType")
public class ProgramType {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="type")
	private String type;
	@Column(name="duration")
	private String duration;
	@Column(name="description")
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
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
