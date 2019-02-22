package com.supafit.controller.fitnessgoal;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supafit.bo.fitnessgoal.FitnessGoalService;
import com.supafit.common.parser.GoalsParser;
import com.supafit.dao.goal.model.FitnessGoal;
import com.supafit.model.goal.FitnessGoalDTO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/goals")
@Api(value = "goals", description = "Operations pertaining to fitness goals")
@CrossOrigin
public class FitnessGoalController {

	private FitnessGoalService fitnessGoalService;
	private GoalsParser goalsParser;
	@Autowired
	public void setGoalsParser(GoalsParser goalsParser) {
		this.goalsParser = goalsParser;
	}

	@Autowired
	public void setFitnessGoalService(FitnessGoalService fitnessGoalService) {
		this.fitnessGoalService = fitnessGoalService;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get the list of Goals", notes = "Get the list of Goals", response = FitnessGoalDTO.class)
	public ResponseEntity<List<FitnessGoalDTO>> getFitnessGoals() {
		List<FitnessGoal> fitnessGoals = fitnessGoalService.getFitnessGoals();
		List<FitnessGoalDTO> response = fitnessGoals.stream()
				.map(fitnessGoal -> goalsParser.convertToDto(fitnessGoal))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FitnessGoalDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Create Goals", notes = "Create Goals", response = FitnessGoalDTO.class)
	public ResponseEntity<List<FitnessGoalDTO>> createFitnessGoals(
			List<FitnessGoalDTO> fitnessGoalDTOs) {
		List<FitnessGoal> fitnessGoalEntities = fitnessGoalDTOs.stream()
				.map(fitnessGoal -> goalsParser.convertToEntity(fitnessGoal))
				.collect(Collectors.toList());
		List<FitnessGoal> fitnessGoalResponse = fitnessGoalService
				.createFitnessGoals(fitnessGoalEntities);
		List<FitnessGoalDTO> response = fitnessGoalResponse.stream()
				.map(exerciseCategory -> goalsParser.convertToDto(exerciseCategory))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FitnessGoalDTO>>(response,
				HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update Goals", notes = "Update Goals", response = FitnessGoalDTO.class)
	public ResponseEntity<Void> updateFitnessGoals(
			List<FitnessGoalDTO> fitnessGoalDTOs) {
		List<FitnessGoal> fitnessGoalEntities = fitnessGoalDTOs.stream()
				.map(fitnessGoal -> goalsParser.convertToEntity(fitnessGoal))
				.collect(Collectors.toList());
		List<FitnessGoal> categoryResponse = fitnessGoalService
				.updateFitnessGoals(fitnessGoalEntities);
		List<FitnessGoalDTO> response = categoryResponse.stream()
				.map(fitnessGoal -> goalsParser.convertToDto(fitnessGoal))
				.collect(Collectors.toList());
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "{fitnessGoalId}", method = RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "Delete Goals", notes = "Delete Goals")
	public ResponseEntity<Void> deleteFitnessGoal(
			@PathVariable("fitnessGoalId") Long fitnessGoalId) {
		fitnessGoalService.deleteFitnessGoal(fitnessGoalId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
