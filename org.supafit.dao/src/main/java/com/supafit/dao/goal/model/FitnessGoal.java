package com.supafit.dao.goal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("FitnessGoal")
@Entity
@Table(name="fitness_goal")
@NamedQueries({
	@NamedQuery(name = "FitnessGoal.findAll", query = "select ft from FitnessGoal ft"),
	@NamedQuery(name = "FitnessGoal.findById", query = "select ft from FitnessGoal ft where ft.id = :id"),
	@NamedQuery(name = "FitnessGoal.deleteByFitnessGoalId", query = "DELETE FROM FitnessGoal m where m.id = :fitnessGoalId")
})
public class FitnessGoal {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="goal")
	private String goal;
	@Column(name="description")
	private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
