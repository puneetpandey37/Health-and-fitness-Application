package com.supafit.bo.exercise;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.exercise.ExerciseManager;
import com.supafit.dao.exercise.model.Exercise;
import com.supafit.dao.exercise.model.ExerciseCategory;
import com.supafit.dao.exercise.model.Timing;

@Component
public class ExerciseService {

	private ExerciseManager exerciseManager;
	@Autowired
	public void setExerciseManager(ExerciseManager exerciseManager) {
		this.exerciseManager = exerciseManager;
	}
	
	public List<ExerciseCategory> getExerciseCategories() {
		return exerciseManager.getExerciseCategories();
		}
	
	public List<Exercise> getExercises(Long categoryId) {
		
		List<Exercise> result;
		if(categoryId == null) {
			result = exerciseManager.getExercises();
		} else {
			result = exerciseManager.getExerciseByCategory(categoryId);
		}
		return result;
	}
	
	public List<ExerciseCategory> createExerciseCategories(
			List<ExerciseCategory> categories) {
		List<ExerciseCategory> result = new ArrayList<ExerciseCategory>();
		for(ExerciseCategory category : categories) {
			category = exerciseManager.createUpdateExerciseCategory(category);
			result.add(category);
		}
		return result;
	}

	public List<ExerciseCategory> updateExerciseCategories(
			List<ExerciseCategory> categories) {
		List<ExerciseCategory> result = new ArrayList<ExerciseCategory>();
		for(ExerciseCategory category : categories) {
			category = exerciseManager.createUpdateExerciseCategory(category);
			result.add(category);
		}
		return result;
	}

	public List<Exercise> createExercises(List<Exercise> exercises) {
		List<Exercise> result = new ArrayList<Exercise>();
		for(Exercise exercise : exercises) {
			exercise = exerciseManager.createUpdateExercise(exercise);
			result.add(exercise);
		}
		return result;
	}

	public List<Exercise> updateExercises(List<Exercise> exercises) {
		List<Exercise> result = new ArrayList<Exercise>();
		for(Exercise exercise : exercises) {
			exercise = exerciseManager.createUpdateExercise(exercise);
			result.add(exercise);
		}
		return result;
	}

	public Exercise getExerciseDetail(long exerciseId) {
		return exerciseManager.getExerciseDetail(exerciseId);
	}

	public Timing getTimings(Long timingId) {
		return exerciseManager.getTimings(timingId);
	}

	public List<Timing> getAllTimings() {
		return exerciseManager.getAllTimings();
	}
}
