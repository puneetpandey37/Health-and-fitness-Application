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
@Table(name="coach_type")
@ApiModel("userType")
@NamedQueries({
    @NamedQuery(name = "CoachType.findAll", query = "SELECT ut FROM CoachType ut"),
    @NamedQuery(name = "CoachType.findById", query = "SELECT ut FROM CoachType ut WHERE ut.id = :id"),
    @NamedQuery(name = "CoachType.findByType", query = "SELECT ut FROM CoachType ut WHERE ut.id = :id") 
    })
public class CoachType {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name = "type")
	private String type;
	@Column(name = "description")
	private String description;
	/*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JsonBackReference
	@JoinTable(
			   name = "coach_type_mapping", 
			   joinColumns = @JoinColumn(name = "coach_type_id"), 
			   inverseJoinColumns = @JoinColumn(name = "coach_id")
			 )
	private List<Coach> coaches;*/
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
