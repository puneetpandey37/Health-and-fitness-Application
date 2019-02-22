package com.supafit.dao.exercise;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.supafit.dao.exercise.model.Exercise;
import com.supafit.dao.exercise.model.ExerciseCategory;
import com.supafit.dao.exercise.model.Timing;
@Repository("exerciseManager")
@Transactional(propagation = Propagation.REQUIRED)
public class ExerciseManager {

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<ExerciseCategory> getExerciseCategories() {
		return entityManager
	            .createNamedQuery("ExerciseCategory.findAll", ExerciseCategory.class).getResultList();
	}
	
	public List<Exercise> getExercises() {
		return entityManager
	            .createNamedQuery("Exercise.findAll", Exercise.class).getResultList();
	}
	
	public List<Exercise> getExerciseByCategory(long exerciseId) {
		return entityManager
	            .createNamedQuery("Exercise.findByCategory", Exercise.class)
	            .setParameter("id", exerciseId)
	            .getResultList();
	}

	public ExerciseCategory createUpdateExerciseCategory(ExerciseCategory category) {
		return entityManager.merge(category);
	}

	public Exercise createUpdateExercise(Exercise exercise) {
		return entityManager.merge(exercise);
	}

	public Exercise getExerciseDetail(long exerciseId) {
		List<Exercise> results = entityManager
	            .createNamedQuery("Exercise.findById", Exercise.class)
	            .setParameter("id", exerciseId).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public Timing getTimings(Long timingId) {
			List<Timing> results = entityManager
		            .createNamedQuery("Timing.findById", Timing.class)
		            .setParameter("id", timingId).getResultList();
		      return results.isEmpty() ? null : results.get(0);
	}

	public List<Timing> getAllTimings() {
		List<Timing> results = entityManager
	            .createNamedQuery("Timing.findAll", Timing.class)
	            .getResultList();
	      return results;
	}
}
