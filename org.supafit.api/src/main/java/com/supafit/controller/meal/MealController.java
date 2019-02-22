package com.supafit.controller.meal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supafit.bo.meal.MealRequestDTO;
import com.supafit.bo.meal.MealService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/meals")
@Api(value = "meals", description = "Operations pertaining to meals")
@CrossOrigin
public class MealController {

	private MealService mealService;
	@Autowired
	public void setMealService(MealService mealService) {
		this.mealService = mealService;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Create Meal", notes = "Create Meal")
	public MealRequestDTO createMeal(@RequestBody MealRequestDTO mealRequest) throws Exception {
		return mealService.createMeal(mealRequest);
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update Meal", notes = "Update Meal")
	public MealRequestDTO updateMeal(@RequestBody MealRequestDTO mealRequest) throws Exception {
		return mealService.updateMeal(mealRequest);
	}
	
	@RequestMapping(value="/{coachId}",method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get Meals", notes = "Get Meals")
	public List<MealRequestDTO> getMeal(@PathVariable("coachId") Long coachId) throws Exception {
		return mealService.getMeals(coachId);
	}
	
	@RequestMapping(value="/{mealId}",method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Delete Meals", notes = "Delete Meals")
	public String deleteMeal(@PathVariable("mealId") Long mealId) throws Exception {
		return mealService.deleteMeal(mealId);
	}
}
