package com.supafit.controller.food;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supafit.bo.food.FoodService;
import com.supafit.common.parser.FoodParser;
import com.supafit.dao.food.model.FoodItems;
import com.supafit.dao.food.model.FoodPreferences;
import com.supafit.dao.food.model.FoodTypes;
import com.supafit.model.food.FoodItemsDTO;
import com.supafit.model.food.FoodPreferencesDTO;
import com.supafit.model.food.FoodTypesDTO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/foods")
@Api(value = "foods", description = "Operations pertaining to food")
@CrossOrigin
public class FoodController {

	private FoodService foodService;
	private FoodParser foodParser;

	@Autowired
	public void setFoodParser(FoodParser foodParser) {
		this.foodParser = foodParser;
	}

	@Autowired
	public void setFoodService(FoodService foodService) {
		this.foodService = foodService;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Create foods", notes = "Create foods")
	public ResponseEntity<List<FoodItemsDTO>> createFoods(
			@RequestBody List<FoodItemsDTO> foodItemsDTOs) {
		List<FoodItems> foodItemsEntities = foodItemsDTOs.stream()
				.map(foodItems -> foodParser.convertToEntity(foodItems))
				.collect(Collectors.toList());
		List<FoodItems> foodItemsResponse = foodService
				.createFoods(foodItemsEntities);
		List<FoodItemsDTO> response = foodItemsResponse.stream()
				.map(foodItems -> foodParser.convertToDto(foodItems))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FoodItemsDTO>>(response,
				HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get the List of foods", notes = "Get the List of foods")
	public ResponseEntity<List<FoodItemsDTO>> getFoods() {
		List<FoodItems> foodItemsResponse = foodService.getFoods();
		List<FoodItemsDTO> response = foodItemsResponse.stream()
				.map(foodItems -> foodParser.convertToDto(foodItems))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FoodItemsDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "update foods", notes = "update foods", response = FoodItemsDTO.class, responseContainer = "List")
	public ResponseEntity<List<FoodItemsDTO>> updateFoods(
			@RequestBody List<FoodItemsDTO> foodItemsDTOs) {
		List<FoodItems> foodItemsEntities = foodItemsDTOs.stream()
				.map(foodItems -> foodParser.convertToEntity(foodItems))
				.collect(Collectors.toList());
		List<FoodItems> foodItemsResponse = foodService
				.updateFoods(foodItemsEntities);
		List<FoodItemsDTO> response = foodItemsResponse.stream()
				.map(foodItems -> foodParser.convertToDto(foodItems))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FoodItemsDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/types", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Create food types", notes = "Create food types", response = FoodTypesDTO.class, responseContainer = "List")
	public ResponseEntity<List<FoodTypesDTO>> createFoodTypes(
			@RequestBody List<FoodTypesDTO> foodTypeDTOs) {
		List<FoodTypes> foodTypeEntities = foodTypeDTOs.stream()
				.map(foodItems -> foodParser.convertToEntity(foodItems))
				.collect(Collectors.toList());
		List<FoodTypes> foodTypeResponse = foodService
				.createFoodTypes(foodTypeEntities);
		List<FoodTypesDTO> response = foodTypeResponse.stream()
				.map(foodTypes -> foodParser.convertToDto(foodTypes))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FoodTypesDTO>>(response,
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/types", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get food types", notes = "Get food types")
	public ResponseEntity<List<FoodTypesDTO>> getFoodTypes() {
		List<FoodTypes> foodTypesResponse = foodService.getFoodTypes();
		List<FoodTypesDTO> response = foodTypesResponse.stream()
				.map(foodItems -> foodParser.convertToDto(foodItems))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FoodTypesDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/types", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update food types", notes = "Update food types", response = FoodTypesDTO.class, responseContainer = "List")
	public ResponseEntity<List<FoodTypesDTO>> updateFoodTypes(
			@RequestBody List<FoodTypesDTO> foodTypesDTO) {
		List<FoodTypes> foodTypesEntities = foodTypesDTO.stream()
				.map(foodItems -> foodParser.convertToEntity(foodItems))
				.collect(Collectors.toList());
		List<FoodTypes> foodItemsResponse = foodService
				.updateFoodTypes(foodTypesEntities);
		List<FoodTypesDTO> response = foodItemsResponse.stream()
				.map(foodItems -> foodParser.convertToDto(foodItems))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FoodTypesDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/preferences", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get the list of food preferences", notes = "Get the list of food preferences")
	public ResponseEntity<List<FoodPreferencesDTO>> getFoodPreferences() {
		List<FoodPreferences> foodPreferencesResponse = foodService
				.getFoodPreferences();
		List<FoodPreferencesDTO> response = foodPreferencesResponse
				.stream()
				.map(foodPreferences -> foodParser
						.convertToDto(foodPreferences))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FoodPreferencesDTO>>(response,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/preferences", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Create food preferences", notes = "Create food preferences")
	public ResponseEntity<List<FoodPreferencesDTO>> createFoodPreferences(
			List<FoodPreferencesDTO> foodPreferencesDTOs) {
		List<FoodPreferences> foodPreferencesEntities = foodPreferencesDTOs
				.stream()
				.map(foodItems -> foodParser.convertToEntity(foodItems))
				.collect(Collectors.toList());
		List<FoodPreferences> foodPreferencesResponse = foodService
				.createFoodPreferences(foodPreferencesEntities);
		List<FoodPreferencesDTO> response = foodPreferencesResponse.stream()
				.map(foodTypes -> foodParser.convertToDto(foodTypes))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FoodPreferencesDTO>>(response,
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/preferences", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update food preferences", notes = "Update food preferences")
	public ResponseEntity<List<FoodPreferencesDTO>> updateFoodPreferences(
			List<FoodPreferencesDTO> foodPreferencesDTO) {
		List<FoodPreferences> foodPreferencesEntities = foodPreferencesDTO
				.stream()
				.map(foodPreferences -> foodParser
						.convertToEntity(foodPreferences))
				.collect(Collectors.toList());
		List<FoodPreferences> foodPreferenceResponse = foodService
				.updateFoodPreferences(foodPreferencesEntities);
		List<FoodPreferencesDTO> response = foodPreferenceResponse.stream()
				.map(foodItems -> foodParser.convertToDto(foodItems))
				.collect(Collectors.toList());
		return new ResponseEntity<List<FoodPreferencesDTO>>(response,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/preferences/{foodPreferenceId}", method = RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "Delete food preferences", notes = "Delete food preferences")
	public ResponseEntity<Void> deleteFoodPreferences(
			@PathVariable("foodPreferenceId") Long foodPreferenceId) {
		foodService.deleteFoodPreferences(foodPreferenceId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
