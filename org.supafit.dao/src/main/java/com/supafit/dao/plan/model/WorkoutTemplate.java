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
@Table(name="workout_template")
@NamedQueries({
	@NamedQuery(name = "WorkoutTemplate.findAll", query = "select wt from WorkoutTemplate wt"),
	@NamedQuery(name = "WorkoutTemplate.findByPlanId", query = "select wt from WorkoutTemplate wt where wt.id = :id"),
	@NamedQuery(name = "WorkoutTemplate.findByUserId", query = "select wt from WorkoutTemplate wt where wt.userId = :userId"),
	@NamedQuery(name = "WorkoutTemplate.findByUserIdAndDate", query = "select mt from WorkoutTemplate mt where mt.userId = :userId AND mt.date = :date AND mt.exerciseTimingId = :exerciseTimingId")
})
@ApiModel("workoutTemplate")
public class WorkoutTemplate {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="user_id")
	private Long userId;
	@Column(name="coach_id")
	private Long coachId;
	@Column(name="workout_id")
	private long workoutId;
	@Column(name="timings_id")
	private long exerciseTimingId;
	@Column(name="special_instructions")
	private String instructions;
	@Column(name="date")
	private String date;
	@Column(name="status")
	private int status;
	@Column(name="remark_user")
	private String userRemarks;
	@Column(name="remark_coach")
	private String coachRemarks;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCoachId() {
		return coachId;
	}
	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}
	public long getWorkoutId() {
		return workoutId;
	}
	public void setWorkoutId(long workoutId) {
		this.workoutId = workoutId;
	}
	public long getExerciseTimingId() {
		return exerciseTimingId;
	}
	public void setExerciseTimingId(long exerciseTimingId) {
		this.exerciseTimingId = exerciseTimingId;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUserRemarks() {
		return userRemarks;
	}
	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}
	public String getCoachRemarks() {
		return coachRemarks;
	}
	public void setCoachRemarks(String coachRemarks) {
		this.coachRemarks = coachRemarks;
	}
}
