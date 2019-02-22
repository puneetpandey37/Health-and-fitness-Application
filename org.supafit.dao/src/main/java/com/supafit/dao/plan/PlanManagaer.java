package com.supafit.dao.plan;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.supafit.dao.plan.model.MealPlanDetail;
import com.supafit.dao.plan.model.MealPlanStatusDetail;
import com.supafit.dao.plan.model.MealPlans;
import com.supafit.dao.plan.model.MealTemplate;
import com.supafit.dao.plan.model.WorkoutPlanDetail;
import com.supafit.dao.plan.model.WorkoutPlans;
import com.supafit.dao.plan.model.WorkoutTemplate;
import com.supafit.dao.workout.model.UserWorkout;

@Repository("planManager")
@Transactional(propagation = Propagation.REQUIRED)
public class PlanManagaer {

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<MealTemplate> getUserMealPlan(Long userId) {
		List<MealTemplate> results = entityManager
	            .createNamedQuery("MealTemplate.findByUserId", MealTemplate.class)
	            .setParameter("userId", userId).getResultList();
	      return results;
	}

	public MealTemplate createUpdateUserMealPlan(MealTemplate meal) {
		return entityManager.merge(meal);
	}

	public MealTemplate getMealPlanByPlanId(long planId) {
		List<MealTemplate> results = entityManager
	            .createNamedQuery("MealTemplate.findByPlanId", MealTemplate.class).setParameter("id", planId).getResultList();
		return results.isEmpty() ? null : results.get(0);
	}

	public List<WorkoutTemplate> getUserWorkoutPlans(Long userId) {
		List<WorkoutTemplate> results = entityManager
	            .createNamedQuery("WorkoutTemplate.findByUserId", WorkoutTemplate.class)
	            .setParameter("userId", userId).getResultList();
	      return results;
	}

	public WorkoutTemplate createUpdateUserWorkoutPlan(WorkoutTemplate workout) {
		return entityManager.merge(workout);
	}

	public WorkoutTemplate getWorkoutPlanByPlanId(long planId) {
		List<WorkoutTemplate> results = entityManager
	            .createNamedQuery("WorkoutTemplate.findByPlanId", WorkoutTemplate.class).setParameter("id", planId).getResultList();
		return results.isEmpty() ? null : results.get(0);
	}

	public List<MealTemplate> getUserMealPlanByDate(Long userId,
			String startDate, long timingId) {
		List<MealTemplate> results = entityManager
	            .createNamedQuery("MealTemplate.findByUserIdAndDate", MealTemplate.class)
	            .setParameter("userId", userId)
	            .setParameter("date", startDate)
	            .setParameter("mealTimingsId", timingId)
	            .getResultList();
	      return results;
	}

	public List<WorkoutTemplate> getUserWorkoutPlanByDate(Long userId,
			String startDate, long timingId) {
		List<WorkoutTemplate> results = entityManager
	            .createNamedQuery("WorkoutTemplate.findByUserIdAndDate", WorkoutTemplate.class)
	            .setParameter("userId", userId)
	            .setParameter("date", startDate)
	            .setParameter("exerciseTimingId", timingId)
	            .getResultList();
	      return results;
	}

	public UserWorkout getWorkoutDetails(Long workoutId) {
		List<UserWorkout> results = entityManager
	            .createNamedQuery("UserWorkout.findByWorkoutId", UserWorkout.class)
	            .setParameter("workoutId", workoutId)
	            .getResultList();
		return results.isEmpty() ? null : results.get(0);
	}

	public List<MealPlans> getUserMealPlan(Long userId, String startDate,
			String endDate) {
		List<MealPlans> results = entityManager
		            .createNamedQuery("MealPlans.findByUserIdAndDates", MealPlans.class)
		            .setParameter("userId", userId)
		            .setParameter("startDate", startDate)
		            .setParameter("endDate", endDate)
		            .getResultList();
		return results;
	}

	public List<MealPlanStatusDetail> getUserMealPlanStatusDetail(
			Long mealPlanStatusId) {
		List<MealPlanStatusDetail> results = entityManager
	            .createNamedQuery("MealPlanStatusDetail.findByPlanStatusId", MealPlanStatusDetail.class)
	            .setParameter("mealPlanStatusId", mealPlanStatusId)
	            .getResultList();
	return results;
	}

	public List<WorkoutPlans> getUserWorkoutPlan(Long userId, String startDate,
			String endDate) {
		List<WorkoutPlans> results = entityManager
	            .createNamedQuery("WorkoutPlans.findByUserIdAndDates", WorkoutPlans.class)
	            .setParameter("userId", userId)
	            .setParameter("startDate", startDate)
	            .setParameter("endDate", endDate)
	            .getResultList();
		return results;
	}

	public MealPlans createUserMealPlan(MealPlans mealPlans) {
		return entityManager.merge(mealPlans);
	}

	public WorkoutPlans createUserWorkoutPlan(WorkoutPlans plan) {
		return entityManager.merge(plan);
	}

	public List<MealPlans> getUserMealPlanOfAllUsersUnderADietitan(
			Long dietitianId) {
		List<MealPlans> results = entityManager
	            .createNamedQuery("MealPlans.findByDietitianId", MealPlans.class)
	            .setParameter("dietitianId", dietitianId)
	            .getResultList();
		return results;
	}

	public List<WorkoutPlans> getUserMealPlanOfAllUsersUnderATrainer(
			Long trainerId) {
		List<WorkoutPlans> results = entityManager
	            .createNamedQuery("WorkoutPlans.findByTrainerId", WorkoutPlans.class)
	            .setParameter("trainerId", trainerId)
	            .getResultList();
		return results;
	}

	public List<MealPlanDetail> getNotCopletedMealPlanStatusOfNDays(Long dietitianId, String startDate, String endDate) {
		List<MealPlanDetail> results = entityManager
	            .createNamedQuery("MealPlanDetail.findUncompletedByDietitianIdAndDate", MealPlanDetail.class)
	            .setParameter("dietitianId", dietitianId)
	            .setParameter("startDate", startDate)
	            .setParameter("endDate", endDate)
	            .getResultList();
		return results;
	}

	public List<MealPlanDetail> getCopletedMealPlanStatusOfNDays(Long dietitianId,
			String startDate, String endDate) {
		List<MealPlanDetail> results = entityManager
	            .createNamedQuery("MealPlanDetail.findCompletedByDietitianIdAndDate", MealPlanDetail.class)
	            .setParameter("dietitianId", dietitianId)
	            .setParameter("startDate", startDate)
	            .setParameter("endDate", endDate)
	            .getResultList();
		return results;
	}

	public List<WorkoutPlanDetail> getNotCopletedWorkoutPlanStatusOfNDays(
			Long trainerId, String startDate, String endDate) {
		List<WorkoutPlanDetail> results = entityManager
	            .createNamedQuery("WorkoutPlanDetail.findUncompletedByTrainerIdAndDate", WorkoutPlanDetail.class)
	            .setParameter("trainerId", trainerId)
	            .setParameter("startDate", startDate)
	            .setParameter("endDate", endDate)
	            .getResultList();
		return results;
	}

	public List<WorkoutPlanDetail> getCopletedWorkoutPlanStatusOfNDays(
			Long trainerId, String startDate, String endDate) {
		List<WorkoutPlanDetail> results = entityManager
	            .createNamedQuery("WorkoutPlanDetail.findCompletedByTrainerIdAndDate", WorkoutPlanDetail.class)
	            .setParameter("trainerId", trainerId)
	            .setParameter("startDate", startDate)
	            .setParameter("endDate", endDate)
	            .getResultList();
		return results;
	}
}
