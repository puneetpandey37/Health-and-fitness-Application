package com.supafit.common.parser;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.food.model.FoodItems;
import com.supafit.dao.food.model.FoodPreferences;
import com.supafit.dao.food.model.FoodTypes;
import com.supafit.model.food.FoodItemsDTO;
import com.supafit.model.food.FoodPreferencesDTO;
import com.supafit.model.food.FoodTypesDTO;
@Component("foodParser")
public class FoodParser {

    public ModelMapper modelMapper;
    @Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public FoodItems convertToEntity(FoodItemsDTO foodItemsDTOs) {
		FoodItems foodItems = modelMapper.map(foodItemsDTOs, FoodItems.class);
	    return foodItems;
	}
	
	public FoodTypes convertToEntity(FoodTypesDTO foodTypesDTO) {
		FoodTypes foodTypes = modelMapper.map(foodTypesDTO, FoodTypes.class);
	    return foodTypes;
	}

	public FoodPreferences convertToEntity(FoodPreferencesDTO foodPreferencesDTO) {
		FoodPreferences foodPreferences = modelMapper.map(foodPreferencesDTO, FoodPreferences.class);
		return foodPreferences;
	}
	
	public FoodItemsDTO convertToDto(FoodItems foodItems) {
		FoodItemsDTO foodItemsDTO = modelMapper.map(foodItems, FoodItemsDTO.class);
		return foodItemsDTO;
	}
	
	public FoodTypesDTO convertToDto(FoodTypes foodTypes) {
		FoodTypesDTO foodTypesDTO = modelMapper.map(foodTypes, FoodTypesDTO.class);
		return foodTypesDTO;
	}

	public FoodPreferencesDTO convertToDto(FoodPreferences foodPreferences) {
		FoodPreferencesDTO foodPreferencesDTO = modelMapper.map(foodPreferences, FoodPreferencesDTO.class);
		return foodPreferencesDTO;
	}

}
