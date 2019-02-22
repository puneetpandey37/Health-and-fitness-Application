package com.supafit.common.parser;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.exercise.model.Exercise;
import com.supafit.dao.exercise.model.ExerciseCategory;
import com.supafit.model.exercise.ExerciseCategoryDTO;
import com.supafit.model.exercise.ExerciseDTO;
@Component("exerciseParser")
public class ExerciseParser {

    public ModelMapper modelMapper;
    @Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public ExerciseCategory convertToEntity(ExerciseCategoryDTO exerciseCategoryDTO) {
		ExerciseCategory exerciseCategory = modelMapper.map(exerciseCategoryDTO, ExerciseCategory.class);
	    return exerciseCategory;
	}
	
	public Exercise convertToEntity(ExerciseDTO exerciseDTO) {
		Exercise exercise = modelMapper.map(exerciseDTO, Exercise.class);
	    return exercise;
	}
	
	public ExerciseCategoryDTO convertToDto(ExerciseCategory exerciseCategory) {
		ExerciseCategoryDTO exerciseCategoryDTO = modelMapper.map(exerciseCategory, ExerciseCategoryDTO.class);
	    return exerciseCategoryDTO;
	}
	
	public ExerciseDTO convertToDto(Exercise exercise) {
		ExerciseDTO exerciseDTO = modelMapper.map(exercise, ExerciseDTO.class);
	    return exerciseDTO;
	}
}
