package com.supafit.dao.plan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="workout_plan_status_detail")
@NamedQueries({
	@NamedQuery(name = "WorkoutPlanStatusDetail.findAll", query = "select m from WorkoutPlanStatusDetail m"),
	@NamedQuery(name = "WorkoutPlanStatusDetail.findByPlanId", query = "select m from WorkoutPlanStatusDetail m where m.workoutPlanDetailId = :planId")
})
@ApiModel("WorkoutPlanStatusDetail")
public class WorkoutPlanStatusDetail {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="workout_plan_detail_id")
	private Long workoutPlanDetailId;
	@Column(name="exercise")
	private String exercise;
	@Column(name="duration")
	private Double duration;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getWorkoutPlanDetailId() {
		return workoutPlanDetailId;
	}
	public void setWorkoutPlanDetailId(Long workoutPlanDetailId) {
		this.workoutPlanDetailId = workoutPlanDetailId;
	}
	public String getExercise() {
		return exercise;
	}
	public void setExercise(String exercise) {
		this.exercise = exercise;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
}
