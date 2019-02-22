package com.supafit.dao.workout.model;

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

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("Workout")
@Entity
@Table(name="workout")
@NamedQueries({
	@NamedQuery(name = "Workout.findAll", query = "select m from Workout m"),
	@NamedQuery(name = "Workout.findByUserId", query = "select m from Workout m WHERE m.trainerId = :userId"),
	@NamedQuery(name = "Workout.deleteByWorkoutId", query = "DELETE FROM Workout m where m.id = :workoutId")
})
public class Workout {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="trainer_id")
	private Long trainerId;
	@Column(name="workout_name")
	private String workoutName;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "workout_id", referencedColumnName = "id")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<UserWorkout> workouts;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}
	public String getWorkoutName() {
		return workoutName;
	}
	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}
	public List<UserWorkout> getWorkouts() {
		return workouts;
	}
	public void setWorkouts(List<UserWorkout> workouts) {
		this.workouts = workouts;
	}
}
