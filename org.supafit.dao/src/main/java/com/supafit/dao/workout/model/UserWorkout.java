package com.supafit.dao.workout.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;


@ApiModel("UserWorkout")
@Entity
@Table(name="user_workout")
@NamedQueries({
	@NamedQuery(name = "UserWorkout.findAll", query = "select m from UserWorkout m"),
	@NamedQuery(name = "UserWorkout.deleteByMealId", query = "DELETE FROM UserWorkout m where m.workoutId = :workoutId"),
	@NamedQuery(name= "UserWorkout.findByWorkoutId", query = "select m from UserWorkout m WHERE m.workoutId = :workoutId")
})
public class UserWorkout {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="workout_id")
	private Long workoutId;
	@Column(name="exercise_name")
	private String exerciseName;
	@Column(name="duration")
	private String duration;
	@Column(name="calories")
	private String calories;
	@Column(name="description")
	private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getWorkoutId() {
		return workoutId;
	}
	public void setWorkoutId(Long workoutId) {
		this.workoutId = workoutId;
	}
	public String getExerciseName() {
		return exerciseName;
	}
	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getCalories() {
		return calories;
	}
	public void setCalories(String calories) {
		this.calories = calories;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
