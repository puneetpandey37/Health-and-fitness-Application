package com.supafit.dao.summary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="activity_summary")
@NamedQueries({
	@NamedQuery(name = "ActivitySummary.findAll", query = "select q from ActivitySummary q"),
	@NamedQuery(name = "ActivitySummary.findByUserId", query = "select q from ActivitySummary q where q.userId = :userId"),
	@NamedQuery(name = "ActivitySummary.findByDateAndUserId", query = "select q from ActivitySummary q WHERE q.userId = :userId AND q.date = :date"),
	@NamedQuery(name= "ActivitySummary.findByDate", query = "select q from ActivitySummary q WHERE q.date = :date")
})
@ApiModel("ActivitySummary")
public class ActivitySummary {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="user_id")
	private Long userId;
	@Column(name="date")
	private String date;
	@Column(name="steps_walked")
	private int stepsWalked;
	@Column(name="water_consumed")
	private Double waterConsumed;
	@Column(name="sleep_hours")
	private Double sleepConsumed;
	@Column(name="calories_gained")
	private Double caloriesGained;
	@Column(name="calories_burned")
	private Double caloriesBurned;
	@Column(name="steps_target")
	private Integer stepsTarget;
	@Column(name="water_target")
	private Double waterTarget;
	@Column(name="sleep_target")
	private Double sleepTarget;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStepsWalked() {
		return stepsWalked;
	}
	public void setStepsWalked(int stepsWalked) {
		this.stepsWalked = stepsWalked;
	}
	public Double getWaterConsumed() {
		return waterConsumed;
	}
	public void setWaterConsumed(Double waterConsumed) {
		this.waterConsumed = waterConsumed;
	}
	public Double getSleepConsumed() {
		return sleepConsumed;
	}
	public void setSleepConsumed(Double sleepConsumed) {
		this.sleepConsumed = sleepConsumed;
	}
	public Double getCaloriesGained() {
		return caloriesGained;
	}
	public void setCaloriesGained(Double caloriesGained) {
		this.caloriesGained = caloriesGained;
	}
	public Double getCaloriesBurned() {
		return caloriesBurned;
	}
	public void setCaloriesBurned(Double caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}
	public Integer getStepsTarget() {
		return stepsTarget;
	}
	public void setStepsTarget(Integer stepsTarget) {
		this.stepsTarget = stepsTarget;
	}
	public Double getWaterTarget() {
		return waterTarget;
	}
	public void setWaterTarget(Double waterTarget) {
		this.waterTarget = waterTarget;
	}
	public Double getSleepTarget() {
		return sleepTarget;
	}
	public void setSleepTarget(Double sleepTarget) {
		this.sleepTarget = sleepTarget;
	}
}
