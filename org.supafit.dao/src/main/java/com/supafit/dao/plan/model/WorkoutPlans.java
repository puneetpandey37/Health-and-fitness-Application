package com.supafit.dao.plan.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="workout_plans")
@NamedQueries({
	@NamedQuery(name = "WorkoutPlans.findAll", query = "SELECT m FROM WorkoutPlans m"),
	@NamedQuery(name = "WorkoutPlans.findByUserIdAndDates", query = "SELECT m FROM WorkoutPlans m WHERE m.userId = :userId AND m.dateCreated >= :startDate AND m.dateCreated <= :endDate"),
	@NamedQuery(name = "WorkoutPlans.findByTrainerId", query = "SELECT m FROM WorkoutPlans m WHERE m.trainerId = :trainerId")
})
@ApiModel("WorkoutPlans")
public class WorkoutPlans {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="user_id")
	private Long userId;
	@Column(name="trainer_id")
	private Long trainerId;
	@Column(name="date_created")
	private String dateCreated;
	@Column(name="trainer_remarks")
	private String trainerRemarks;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "workout_plan_id", referencedColumnName = "id")
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonManagedReference
	private List<WorkoutPlanDetail> workoutPlanDetails;
	
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
	public Long getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getTrainerRemarks() {
		return trainerRemarks;
	}
	public void setTrainerRemarks(String trainerRemarks) {
		this.trainerRemarks = trainerRemarks;
	}
	public List<WorkoutPlanDetail> getWorkoutPlanDetails() {
		return workoutPlanDetails;
	}
	public void setWorkoutPlanDetails(List<WorkoutPlanDetail> workoutPlanDetails) {
		this.workoutPlanDetails = workoutPlanDetails;
	}
}
