package com.supafit.bo.meal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.meal.MealManager;
import com.supafit.dao.meal.model.Meals;
import com.supafit.dao.meal.model.UserMeals;

@Component
public class MealService {

	private MealManager mealManager;
	@Autowired
	public void setMealManager(MealManager mealManager) {
		this.mealManager = mealManager;
	}
	
	public MealRequestDTO createMeal(MealRequestDTO mealRequest) {
		Meals meals = new Meals();
		String mealName = mealRequest.getMealName();
		Long coachId = mealRequest.getCoachId();
		if(coachId != null)
			meals.setDietitianId(coachId);
		if(mealName != null)
			meals.setName(mealName);
		meals = mealManager.createMeal(meals);
		if(meals != null)
			mealRequest.setId(meals.getId());
		List<UserMeals> userMeal = mealRequest.getMeals();
		List<UserMeals> userMealResponse = new ArrayList<UserMeals>();
		if(userMeal  != null) {
			for(UserMeals meal : userMeal) {
//				meal.setMealId(meals.getId());
				meal = mealManager.createMeal(meal);
				userMealResponse.add(meal);
			}
		}
		mealRequest.setMeals(userMealResponse);
		return mealRequest;
	}
	
	public List<MealRequestDTO> getMeals(Long coachId) {
		List<MealRequestDTO> response = new ArrayList<MealRequestDTO>();
		List<Meals> meals = null; 
		if(coachId != null) {
			meals = mealManager.getMealsByUserId(coachId);
		} else {
			meals = mealManager.getMeals();
		}
		if(meals != null) {
			for(Meals meal : meals) {
				MealRequestDTO mealResponse = new MealRequestDTO();
				long mealId = meal.getId();
				if(coachId != null)
					mealResponse.setCoachId(coachId);
				mealResponse.setMealName(meal.getName());
				mealResponse.setId(meal.getId());
				List<UserMeals> userMeals = mealManager.getUserMealsByMealId(mealId);
				if(userMeals != null)
					mealResponse.setMeals(userMeals);
				response.add(mealResponse);
			}
		}
		return response;
	}
	
	public MealRequestDTO updateMeal(MealRequestDTO mealRequest) {
		if(mealRequest != null) {
			String mealName = mealRequest.getMealName();
			long id = mealRequest.getId();
			long coachId = mealRequest.getCoachId();
			Meals meal = new Meals();
			meal.setId(id);
			meal.setName(mealName);
			meal.setDietitianId(coachId);
			mealManager.createMeal(meal);
			List<UserMeals> userMeals = mealRequest.getMeals();
			if(userMeals != null) {
				for(UserMeals userMeal : userMeals) {
					userMeal = mealManager.createMeal(userMeal);
				}
			}
		}
		return mealRequest;
	}
	
	public String deleteMeal(long mealId) {
		String result;
		try {
			mealManager.deleteMeal(mealId);
			mealManager.deleteUserMeal(mealId);
			result = "success";
		} catch (Exception e) {
			result = "failed";
		}
		return result;
	}
}
