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
@Table(name="user_fitness_goal")
@NamedQueries({
    @NamedQuery(name = "UserFitnessGoal.findByUserId", query = "SELECT p FROM UserFitnessGoal p where p.userId = :userId"),
    @NamedQuery(name = "UserFitnessGoal.deleteByUserId", query = "DELETE FROM UserFitnessGoal m where m.userId = :userId")
    })
@ApiModel("UserFitnessGoal")
public class UserFitnessGoal {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="user_id")
	private long userId;
	@Column(name="fitness_goal_id")
	private long fitnessGoalId;
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
	public long getFitnessGoalId() {
		return fitnessGoalId;
	}
	public void setFitnessGoalId(long fitnessGoalId) {
		this.fitnessGoalId = fitnessGoalId;
	}
}
