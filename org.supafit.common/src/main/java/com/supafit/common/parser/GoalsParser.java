package com.supafit.common.parser;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.goal.model.FitnessGoal;
import com.supafit.dao.user.model.UserFitnessGoal;
import com.supafit.model.goal.FitnessGoalDTO;
import com.supafit.model.user.UserFitnessGoalDTO;
@Component("goalsParser")
public class GoalsParser {

    public ModelMapper modelMapper;
    @Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public UserFitnessGoal convertToEntity(UserFitnessGoalDTO userFitnessGoalDTO) {
		UserFitnessGoal userWorkout = modelMapper.map(userFitnessGoalDTO, UserFitnessGoal.class);
	    return userWorkout;
	}
	
	public FitnessGoal convertToEntity(FitnessGoalDTO fitnessGoalDTO) {
		FitnessGoal fitnessGoal = modelMapper.map(fitnessGoalDTO, FitnessGoal.class);
	    return fitnessGoal;
	}
	
	public FitnessGoalDTO convertToDto(FitnessGoal fitnessGoal) {
		FitnessGoalDTO fitnessDTO = modelMapper.map(fitnessGoal, FitnessGoalDTO.class);
		return fitnessDTO;
	}
}
