package com.supafit.dao.workout;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.supafit.dao.workout.model.UserWorkout;
import com.supafit.dao.workout.model.Workout;

@Repository("workoutManager")
@Transactional(propagation = Propagation.REQUIRED)
public class WorkoutManager {

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public UserWorkout createWorkout(UserWorkout workout) {
		return entityManager.merge(workout);
	}
	
	public List<UserWorkout> getUserWorkouts() {
		return entityManager
	            .createNamedQuery("UserWorkout.findAll", UserWorkout.class)
	            .getResultList();
	}
	
	public List<Workout> getWorkouts() {
		return entityManager
	            .createNamedQuery("Workout.findAll", Workout.class)
	            .getResultList();
	}
	
	public UserWorkout updateWorkout(UserWorkout userWorkout) {
		return entityManager.merge(userWorkout);
	}
	
	public void deleteUserWorkout(long id) {
		entityManager
		.createNamedQuery("UserWorkout.deleteByMealId")
		.setParameter("workoutId", id).executeUpdate();
	}
	
	public void deleteWorkout(long id) {
		entityManager
		.createNamedQuery("Workout.deleteByWorkoutId")
		.setParameter("workoutId", id).executeUpdate();
	}

	public List<UserWorkout> getUserWorkoutsByUserId(Long userId) {
		return entityManager
	            .createNamedQuery("UserWorkout.findByUserId", UserWorkout.class)
	            .setParameter("userId", userId)
	            .getResultList();
	}
	
	public List<Workout> getWorkoutsByUserId(Long userId) {
		return entityManager
	            .createNamedQuery("Workout.findByUserId", Workout.class)
	            .setParameter("userId", userId)
	            .getResultList();
	} 

	public Workout createWorkout(Workout workout) {
		return entityManager.merge(workout);
	}

	public List<UserWorkout> getUserWorkoutsByWorkoutId(Long workoutId) {
		return entityManager
	            .createNamedQuery("UserWorkout.findByWorkoutId", UserWorkout.class)
	            .setParameter("workoutId", workoutId)
	            .getResultList();
	}
}
