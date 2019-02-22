package com.supafit.bo.plan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.bo.coach.CoachService;
import com.supafit.bo.exercise.ExerciseService;
import com.supafit.bo.food.FoodService;
import com.supafit.bo.user.UserService;
import com.supafit.common.exceptions.InvalidRequestException;
import com.supafit.common.security.LoggedInUserUtil;
import com.supafit.dao.exercise.model.Timing;
import com.supafit.dao.food.model.MealTimings;
import com.supafit.dao.plan.PlanManagaer;
import com.supafit.dao.plan.model.MealPlanDetail;
import com.supafit.dao.plan.model.MealPlans;
import com.supafit.dao.plan.model.MealTemplate;
import com.supafit.dao.plan.model.WorkoutPlanDetail;
import com.supafit.dao.plan.model.WorkoutPlans;
import com.supafit.dao.plan.model.WorkoutTemplate;
import com.supafit.dao.user.model.User;
//import com.supafit.model.plan.MealPlan;
//import com.supafit.model.plan.MealPlanResponse;
//import com.supafit.model.plan.MealPlans;
//import com.supafit.model.plan.UserMealPlans;
//import com.supafit.model.exercise.WorkoutPlan;

@Component
public class PlanService {
	private final String TAG = this.getClass().getName();
	Logger logger = Logger.getLogger( UserService.class.getName() );
	private PlanManagaer planManagaer;
	private LoggedInUserUtil loggedInUserUtil;
	private UserService userService;
	private FoodService foodService;
	private ExerciseService exerciseService;
	private CoachService coachService;
	
	@Autowired
	public void setCoachService(CoachService coachService) {
		this.coachService = coachService;
	}

	@Autowired
	public void setExerciseService(ExerciseService exerciseService) {
		this.exerciseService = exerciseService;
	}

	@Autowired
	public void setFoodService(FoodService foodService) {
		this.foodService = foodService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setLoggedInUserUtil(LoggedInUserUtil loggedInUserUtil) {
		this.loggedInUserUtil = loggedInUserUtil;
	}

	@Autowired
	public void setPlanManagaer(PlanManagaer planManagaer) {
		this.planManagaer = planManagaer;
	}
	
	public MealTemplate createUserMealPlan(MealTemplate meal) {
//		meal.setCoachId(loggedInUserUtil.getLoggedInUserId());
		return planManagaer.createUpdateUserMealPlan(meal);
	}

	public MealTemplate updateUserMealPlan(MealTemplate meal) {
		return createUserMealPlan(meal);
	}

	
	public WorkoutTemplate createUserWorkoutPlan(WorkoutTemplate workout) throws InvalidRequestException {
		
		WorkoutTemplate result = null;
//		User user = loggedInUserUtil.getLoggedInUser();
//		long userId = user.getId();
//		Long coachId = user.getCoachId();
		if(workout.getUserId() == null) {
//			workout.setUserId(userId);
//			workout.setCoachId(coachId);
			result = planManagaer.createUpdateUserWorkoutPlan(workout);
		} else {
			Long uId = workout.getUserId();
			User inputUser = userService.getUserDetail(uId);
			Long inputCoachId = inputUser.getTrainer().getId();
//			if(inputCoachId != null) {
//				if(inputUser.getCoachId() == userId) {
					result = planManagaer.createUpdateUserWorkoutPlan(workout);
				/*} else {
					throw new InvalidRequestException(
							"Can not create/update other's status.");
				}
			} else {
				throw new InvalidRequestException(
						"Can not create/update other's status.");
			}*/
			}
		logger.log(Level.INFO, TAG +" "+ "Workout plan creation");
		return result;
	}
	
	public WorkoutTemplate updateUserWorkoutPlan(WorkoutTemplate workout) throws InvalidRequestException {
		return createUserWorkoutPlan(workout);
	}
	
	
	public List<User> getMealPlanStatusOfLastNDaysOfAllUsers(Integer days) {
		return null;
	}
	
	public List<User> getWorkoutPlanStatusOfLastNDaysOfAllUsers(Long coachId,
			Integer days) {
		return null;
	}
	
	public String getLastNthDate(Integer days) {
		String output = null;
		if(days != null) {
			Calendar cal = Calendar.getInstance();
			days = days - (days*2);
			cal.add(Calendar.DATE, days);
			SimpleDateFormat df = new SimpleDateFormat( "YYYY-MM-dd" );
			output = df.format(cal.getTime());
		}
		return output;
	}
	
	public String getCurrentDate() {
		String output = null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
		output = df.format(cal.getTime());
		return output;
	}
	
	public List<MealTimings> getDefinedMealTimings() {
		return foodService.getMealTimings();
	}

	public List<Timing> getDefinedWorkoutTimings() {
		return exerciseService.getAllTimings();
	}

	public List<MealPlans> getUserMealPlan(Long userId, String startDate,
			String endDate) {
		return planManagaer.getUserMealPlan(userId, startDate, endDate);
	}

	public List<WorkoutPlans> getUserWorkoutPlan(Long userId, String startDate,
			String endDate) {
		return planManagaer.getUserWorkoutPlan(userId, startDate, endDate);
	}

	public List<MealPlans> createUserMealPlan(List<MealPlans> mealPlans) {
		List<MealPlans> response = new ArrayList<MealPlans>();
		if(mealPlans != null) {
			for(MealPlans plan : mealPlans) {
				MealPlans plans = planManagaer.createUserMealPlan(plan);
				response.add(plans);
			}
		}
		return response;
	}

	public List<WorkoutPlans> createUserWorkoutPlan(
			List<WorkoutPlans> workoutPlans) {
		List<WorkoutPlans> response = new ArrayList<WorkoutPlans>();
		if(workoutPlans != null) {
			for(WorkoutPlans plan : workoutPlans) {
				WorkoutPlans wPlan = planManagaer.createUserWorkoutPlan(plan);
				response.add(wPlan);
			}
		}
		return response;
	}

	public List<MealPlans> getUserMealPlanOfAllUsersUnderADietitan(Long dietitianId) {
		return planManagaer.getUserMealPlanOfAllUsersUnderADietitan(dietitianId);
	}

	public List<WorkoutPlans> getUserMealPlanOfAllUsersUnderATrainer(Long trainerId) {
		return planManagaer.getUserMealPlanOfAllUsersUnderATrainer(trainerId);
	}

	public List<MealPlanDetail> getNotCopletedMealPlanStatusOfNDays(
			Long dietitianId, Integer days) {
		String startDate = getLastNthDate(days);
		String endDate = getCurrentDate();
		return planManagaer.getNotCopletedMealPlanStatusOfNDays(dietitianId, startDate, endDate);
	}

	public List<MealPlanDetail> getCopletedMealPlanStatusOfNDays(Long dietitianId,
			Integer days) {
		String startDate = getLastNthDate(days);
		String endDate = getCurrentDate();
		return planManagaer.getCopletedMealPlanStatusOfNDays(dietitianId, startDate, endDate);
	}

	public List<WorkoutPlanDetail> getNotCopletedWorkoutPlanStatusOfNDays(
			Long trainerId, Integer days) {
		String startDate = getLastNthDate(days);
		String endDate = getCurrentDate();
		return planManagaer.getNotCopletedWorkoutPlanStatusOfNDays(trainerId, startDate, endDate);
	}

	public List<WorkoutPlanDetail> getCopletedWorkoutPlanStatusOfNDays(
			Long trainerId, Integer days) {
		String startDate = getLastNthDate(days);
		String endDate = getCurrentDate();
		return planManagaer.getCopletedWorkoutPlanStatusOfNDays(trainerId, startDate, endDate);
	}
}
