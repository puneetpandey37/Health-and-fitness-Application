package com.supafit.dao.food;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.supafit.dao.food.model.FoodItemMeasure;
import com.supafit.dao.food.model.FoodItems;
import com.supafit.dao.food.model.FoodPreferences;
import com.supafit.dao.food.model.FoodTypes;
import com.supafit.dao.food.model.MealTimings;

@Repository("foodManager")
@Transactional(propagation = Propagation.REQUIRED)
public class FoodManager {

	
	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<FoodItems> getFoods() {
		return entityManager
	            .createNamedQuery("FoodItems.findAll", FoodItems.class).getResultList();
	}

	public FoodItems createUpdateFoods(FoodItems foodItem) {
		return entityManager.merge(foodItem);
	}

	public FoodTypes createUpdateFoodTypes(FoodTypes foodType) {
		return entityManager.merge(foodType);
	}

	public List<FoodTypes> getFoodTypes() {
		return entityManager
	            .createNamedQuery("FoodTypes.findAll", FoodTypes.class).getResultList();
	}

	public FoodItems getFoodDetail(long foodItemId) {
		List<FoodItems> results = entityManager
	            .createNamedQuery("FoodItems.findByItemId", FoodItems.class)
	            .setParameter("foodItemId", foodItemId).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public FoodItemMeasure getItemMeasureByItemId(long measurId) {
		List<FoodItemMeasure> results = entityManager
	            .createNamedQuery("FoodItemMeasure.findById", FoodItemMeasure.class)
	            .setParameter("id", measurId).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public MealTimings getMealTimings(long timingId) {
		List<MealTimings> results = entityManager
	            .createNamedQuery("MealTimings.findById", MealTimings.class)
	            .setParameter("id", timingId).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}
	
	public List<MealTimings> getMealTimings() {
		List<MealTimings> results = entityManager
	            .createNamedQuery("MealTimings.findAll", MealTimings.class)
	            .getResultList();
	      return results;
	}

	public List<FoodPreferences> getFoodPreferences() {
		return entityManager
	            .createNamedQuery("FoodPreferences.findAll", FoodPreferences.class)
	            .getResultList();
	}

	public List<FoodPreferences> createFoodPreferences(
			List<FoodPreferences> foodPreferences) {
		return entityManager.merge(foodPreferences);
	}

	public Long deleteFoodPreferences(Long foodPreferenceId) {
			entityManager
			.createNamedQuery("FoodPreferences.deleteByfoodPreferenceId")
			.setParameter("foodPreferenceId", foodPreferenceId).executeUpdate();
			return foodPreferenceId;
	}

	public FoodPreferences getFoodPreferenceById(long foodPreferenceId) {
		List<FoodPreferences> results = entityManager
	            .createNamedQuery("FoodPreferences.findById", FoodPreferences.class)
	            .setParameter("id", foodPreferenceId).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}
	
}
