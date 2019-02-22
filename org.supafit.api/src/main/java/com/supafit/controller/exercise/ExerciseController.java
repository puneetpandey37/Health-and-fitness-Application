package com.supafit.controller.exercise;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supafit.bo.exercise.ExerciseService;
import com.supafit.common.parser.ExerciseParser;
import com.supafit.dao.exercise.model.Exercise;
import com.supafit.dao.exercise.model.ExerciseCategory;
import com.supafit.model.exercise.ExerciseCategoryDTO;
import com.supafit.model.exercise.ExerciseCategoryResponse;
import com.supafit.model.exercise.ExerciseDTO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/exercises")
@Api(value = "exercises", description = "Operations pertaining to exercises")
@CrossOrigin
public class ExerciseController {

	private ExerciseService exerciseService;
	private ExerciseParser exerciseParser;

	@Autowired
	public void setExerciseParser(ExerciseParser exerciseParser) {
		this.exerciseParser = exerciseParser;
	}

	@Autowired
	public void setExerciseService(ExerciseService exerciseService) {
		this.exerciseService = exerciseService;
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get the List of exercise categories", notes = "This list contains categories of Exercises")
	public ResponseEntity<List<ExerciseCategoryDTO>> getExerciseCategories() {
		List<ExerciseCategory> exerciseResponse = exerciseService.getExerciseCategories();
		List<ExerciseCategoryDTO> response = exerciseResponse.stream()
				.map(exerciseCategory -> exerciseParser.convertToDto(exerciseCategory)).collect(Collectors.toList());
		return new ResponseEntity<List<ExerciseCategoryDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Create exercise categories", notes = "Create exercise categories", response = ExerciseCategory.class, responseContainer = "List")
	public ResponseEntity<ExerciseCategoryResponse> createExerciseCategories(
			@RequestBody List<ExerciseCategoryDTO> categoryDTOs) {
		List<ExerciseCategory> categoryEntities = categoryDTOs.stream()
				.map(exerciseCategory -> exerciseParser.convertToEntity(exerciseCategory)).collect(Collectors.toList());
		List<ExerciseCategory> categoryResponse = exerciseService.createExerciseCategories(categoryEntities);
		List<ExerciseCategoryDTO> responseDTO = categoryResponse.stream()
				.map(exerciseCategory -> exerciseParser.convertToDto(exerciseCategory)).collect(Collectors.toList());
		ExerciseCategoryResponse response = new ExerciseCategoryResponse();
		response.setCategory(responseDTO);
		response.add(
				linkTo(methodOn(ExerciseController.class).getExerciseCategories()).withRel("exercise_category_list"));
		return new ResponseEntity<ExerciseCategoryResponse>(response, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update exercise categories", notes = "Update exercise categories", response = ExerciseCategory.class, responseContainer = "List")
	public ResponseEntity<Void> updateExerciseCategories(@RequestBody List<ExerciseCategoryDTO> categorYDTOs) {
		List<ExerciseCategory> categoryEntities = categorYDTOs.stream()
				.map(exerciseCategory -> exerciseParser.convertToEntity(exerciseCategory)).collect(Collectors.toList());
		List<ExerciseCategory> categoryResponse = exerciseService.updateExerciseCategories(categoryEntities);
		List<ExerciseCategoryDTO> responseDTO = categoryResponse.stream()
				.map(exerciseCategory -> exerciseParser.convertToDto(exerciseCategory)).collect(Collectors.toList());
		ExerciseCategoryResponse response = new ExerciseCategoryResponse();
		response.setCategory(responseDTO);
		response.add(
				linkTo(methodOn(ExerciseController.class).getExerciseCategories()).withRel("exercise_category_list"));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get List of exercises", notes = "Get List of exercises")
	public ResponseEntity<List<ExerciseDTO>> getExercises(
			@RequestParam(value = "exercise_id", required = false) Long exerciseId) {
		List<Exercise> exerciseResponse = exerciseService.getExercises(exerciseId);
		List<ExerciseDTO> response = exerciseResponse.stream().map(exercise -> exerciseParser.convertToDto(exercise))
				.collect(Collectors.toList());
		return new ResponseEntity<List<ExerciseDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Create exercises", notes = "Create exercises", response = ExerciseDTO.class, responseContainer = "List")
	public ResponseEntity<List<ExerciseDTO>> createExercises(@RequestBody List<ExerciseDTO> exerciseDTOs) {
		List<Exercise> exerciseResopnse = exerciseDTOs.stream()
				.map(exercise -> exerciseParser.convertToEntity(exercise)).collect(Collectors.toList());
		List<Exercise> exerciseResponse = exerciseService.createExercises(exerciseResopnse);
		List<ExerciseDTO> response = exerciseResponse.stream().map(exercise -> exerciseParser.convertToDto(exercise))
				.collect(Collectors.toList());
		return new ResponseEntity<List<ExerciseDTO>>(response, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update exercises", notes = "Update exercises", response = ExerciseDTO.class, responseContainer = "List")
	public ResponseEntity<Void> updateExercises(@RequestBody List<ExerciseDTO> exerciseDTOs) {
		List<Exercise> exerciseResopnse = exerciseDTOs.stream()
				.map(exercise -> exerciseParser.convertToEntity(exercise)).collect(Collectors.toList());
		List<Exercise> exerciseResponse = exerciseService.updateExercises(exerciseResopnse);
		List<ExerciseDTO> response = exerciseResponse.stream().map(exercise -> exerciseParser.convertToDto(exercise))
				.collect(Collectors.toList());
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
