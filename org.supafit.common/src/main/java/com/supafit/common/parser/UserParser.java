package com.supafit.common.parser;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.user.model.User;
import com.supafit.dao.user.model.UserFitnessGoal;
import com.supafit.dao.user.model.UserFoodPreferences;
import com.supafit.dao.workout.model.UserWorkout;
import com.supafit.model.user.UserDTO;
import com.supafit.model.user.UserFitnessGoalDTO;
import com.supafit.model.user.UserFoodPreferencesDTO;
import com.supafit.model.workout.UserWorkoutDTO;
@Component("userParser")
public class UserParser {

    public ModelMapper modelMapper;
	
    @Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public User convertToEntity(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
	    return user;
	}
	
	public UserWorkout convertToEntity(UserWorkoutDTO userWorkoutDTO) {
		UserWorkout userWorkout = modelMapper.map(userWorkoutDTO, UserWorkout.class);
	    return userWorkout;
	}
	
	public UserFitnessGoal convertToEntity(UserFitnessGoalDTO userFitnessGoalDTO) {
		UserFitnessGoal userWorkout = modelMapper.map(userFitnessGoalDTO, UserFitnessGoal.class);
	    return userWorkout;
	}
	
	public UserFoodPreferences convertToEntity(UserFoodPreferencesDTO userFoodPreferencesDTO) {
		UserFoodPreferences userFoodPreferences = modelMapper.map(userFoodPreferencesDTO, UserFoodPreferences.class);
	    return userFoodPreferences;
	}
	
	public UserDTO convertToDto(User user) {
		UserDTO userDto = modelMapper.map(user, UserDTO.class);
	    return userDto;
	}
}
