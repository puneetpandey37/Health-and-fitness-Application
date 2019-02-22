package com.supafit.dao.meal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.supafit.dao.meal.model.Meals;
import com.supafit.dao.meal.model.UserMeals;

@Repository("mealManager")
@Transactional(propagation = Propagation.REQUIRED)
public class MealManager {

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public UserMeals createMeal(UserMeals meal) {
		return entityManager.merge(meal);
	}
	
	public List<UserMeals> getUserMeals() {
		return entityManager
	            .createNamedQuery("UserMeals.findAll", UserMeals.class)
	            .getResultList();
	}
	
	public List<Meals> getMeals() {
		return entityManager
	            .createNamedQuery("Meals.findAll", Meals.class)
	            .getResultList();
	}
	
	public UserMeals updateMeal(UserMeals meal) {
		return entityManager.merge(meal);
	}
	
	public void deleteUserMeal(long id) {
		entityManager
		.createNamedQuery("UserMeals.deleteByMealId")
		.setParameter("mealId", id).executeUpdate();
	}
	
	public void deleteMeal(long id) {
		entityManager
		.createNamedQuery("Meals.deleteByMealId")
		.setParameter("mealId", id).executeUpdate();
	}

	public Long getMaxMealId(Long userId) {
		List<Long> results = entityManager
	            .createNamedQuery("UserMeals.findMaxMealIdByUserId", Long.class)
	            .setParameter("userId", userId).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public List<UserMeals> getUserMealsByMealId(Long mealId) {
		return entityManager
	            .createNamedQuery("UserMeals.findByMealId", UserMeals.class)
	            .setParameter("mealId", mealId)
	            .getResultList();
	}
	
	public List<Meals> getMealsByUserId(Long coachId) {
		return entityManager
	            .createNamedQuery("Meals.findByUserId", Meals.class)
	            .setParameter("userId", coachId)
	            .getResultList();
	}

	public Meals createMeal(Meals meals) {
		return entityManager.merge(meals);
	}
}
