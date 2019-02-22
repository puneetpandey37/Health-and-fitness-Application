package com.supafit.bo.food;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.food.FoodManager;
import com.supafit.dao.food.model.FoodItemMeasure;
import com.supafit.dao.food.model.FoodItems;
import com.supafit.dao.food.model.FoodPreferences;
import com.supafit.dao.food.model.FoodTypes;
import com.supafit.dao.food.model.MealTimings;

@Component
public class FoodService {

	private FoodManager foodManager;
	@Autowired
	public void setFoodManager(FoodManager foodManager) {
		this.foodManager = foodManager;
	}
	public List<FoodItems> getFoods() {
		return foodManager.getFoods();
	}
	public List<FoodItems> createFoods(List<FoodItems> foodItems) {
		
		List<FoodItems> result = new ArrayList<FoodItems>();
		for(FoodItems foodItem : foodItems) {
			FoodItems food = foodManager.createUpdateFoods(foodItem);
			result.add(food);
		}
		return result;
	}
	public List<FoodItems> updateFoods(List<FoodItems> foodItems) {
		return createFoods(foodItems);
	}
	public List<FoodTypes> createFoodTypes(List<FoodTypes> foodTypes) {
		List<FoodTypes> result = new ArrayList<FoodTypes>();
		for(FoodTypes foodType : foodTypes) {
			FoodTypes food = foodManager.createUpdateFoodTypes(foodType);
			result.add(food);
		}
		return result;
	}
	public List<FoodTypes> getFoodTypes() {
		return foodManager.getFoodTypes();
	}
	public List<FoodTypes> updateFoodTypes(List<FoodTypes> foodTypes) {
		return createFoodTypes(foodTypes);
	}
	public FoodItems getFoodDetail(long foodItemId) {
		return foodManager.getFoodDetail(foodItemId);
	}
	public FoodItemMeasure getItemMeasureByItemId(long measurId) {
		return foodManager.getItemMeasureByItemId(measurId);
	}
	public MealTimings getMealTimings(long timingId) {
		return foodManager.getMealTimings(timingId);
	}
	public List<MealTimings> getMealTimings() {
		return foodManager.getMealTimings();
	}
	public List<FoodPreferences> getFoodPreferences() {
		return foodManager.getFoodPreferences();
	}
	public List<FoodPreferences> createFoodPreferences(
			List<FoodPreferences> foodPreferences) {
		return foodManager.createFoodPreferences(foodPreferences);
	}
	public List<FoodPreferences> updateFoodPreferences(
			List<FoodPreferences> foodPreferences) {
		return foodManager.createFoodPreferences(foodPreferences);
	}
	public Long deleteFoodPreferences(Long foodPreferenceId) {
		return foodManager.deleteFoodPreferences(foodPreferenceId);
	}
}
