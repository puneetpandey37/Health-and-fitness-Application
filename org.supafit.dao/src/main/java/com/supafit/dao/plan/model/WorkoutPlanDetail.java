package com.supafit.dao.plan.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.supafit.dao.exercise.model.Timing;
import com.supafit.dao.workout.model.Workout;
import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="workout_plan_detail")
@NamedQueries({
	@NamedQuery(name = "WorkoutPlanDetail.findAll", query = "select m from WorkoutPlanDetail m"),
	@NamedQuery(name = "WorkoutPlanDetail.findByPlanId", query = "select m from WorkoutPlanDetail m where m.workoutPlans.id = :planId"),
	@NamedQuery(name = "WorkoutPlanDetail.findUncompletedByTrainerIdAndDate", query = "SELECT s FROM WorkoutPlanDetail s "
			+ "WHERE s.workoutPlans.trainerId = :trainerId "
			+ "AND s.planDate >= :startDate "
			+ "AND s.planDate <= :endDate "
			+ "AND s.planStatus != 1"),
	@NamedQuery(name = "WorkoutPlanDetail.findCompletedByTrainerIdAndDate", query = "SELECT s FROM WorkoutPlanDetail s "
			+ "WHERE s.workoutPlans.trainerId = :trainerId "
			+ "AND s.planDate >= :startDate "
			+ "AND s.planDate <= :endDate "
			+ "AND s.planStatus = 1")		
})
@ApiModel("WorkoutPlanDetail")
public class WorkoutPlanDetail {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="plan_date")
	private String planDate;
	/*@Column(name="workout_plan_id")
	private Long workoutPlanId;*/
	@ManyToOne
	@JoinColumn(name = "workout_plan_id", referencedColumnName = "id")
	@JsonBackReference
	private WorkoutPlans workoutPlans;
	@OneToOne
	@JoinColumn(name = "workout_id", referencedColumnName = "id")
	private Workout workout;
	@OneToOne
	@JoinColumn(name = "workout_timing_id", referencedColumnName = "id")
	private Timing workoutTiming;
	@Column(name="special_instructions")
	private String specialInstructions;
	@Column(name="trainer_remarks")
	private String trainerRemarks;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "workout_plan_detail_id", referencedColumnName = "id")
	@Fetch(value = FetchMode.SUBSELECT)
//	@JsonManagedReference
	private List<WorkoutPlanStatusDetail> workoutPlanStatus;
	@Column(name="plan_status")
	private Integer planStatus;
	@Column(name="user_remarks")
	private String userRemarks;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPlanDate() {
		return planDate;
	}
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	public WorkoutPlans getWorkoutPlans() {
		return workoutPlans;
	}
	public void setWorkoutPlans(WorkoutPlans workoutPlans) {
		this.workoutPlans = workoutPlans;
	}
	public Workout getWorkout() {
		return workout;
	}
	public void setWorkout(Workout workout) {
		this.workout = workout;
	}
	public Timing getWorkoutTiming() {
		return workoutTiming;
	}
	public void setWorkoutTiming(Timing workoutTiming) {
		this.workoutTiming = workoutTiming;
	}
	public String getSpecialInstructions() {
		return specialInstructions;
	}
	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}
	public String getTrainerRemarks() {
		return trainerRemarks;
	}
	public void setTrainerRemarks(String trainerRemarks) {
		this.trainerRemarks = trainerRemarks;
	}
	public List<WorkoutPlanStatusDetail> getWorkoutPlanStatus() {
		return workoutPlanStatus;
	}
	public void setWorkoutPlanStatus(List<WorkoutPlanStatusDetail> workoutPlanStatus) {
		this.workoutPlanStatus = workoutPlanStatus;
	}
	public Integer getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(Integer planStatus) {
		this.planStatus = planStatus;
	}
	public String getUserRemarks() {
		return userRemarks;
	}
	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}
}
