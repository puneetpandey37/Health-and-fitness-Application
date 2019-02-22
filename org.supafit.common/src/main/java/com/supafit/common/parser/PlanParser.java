package com.supafit.common.parser;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.exercise.model.Timing;
import com.supafit.dao.food.model.MealTimings;
import com.supafit.dao.plan.model.MealPlanDetail;
import com.supafit.dao.plan.model.MealPlans;
import com.supafit.dao.plan.model.WorkoutPlans;
import com.supafit.model.exercise.TimingDTO;
import com.supafit.model.food.MealTimingsDTO;
import com.supafit.model.plan.MealPlanDetailDTO;
import com.supafit.model.plan.MealPlansDTO;
import com.supafit.model.plan.WorkoutPlansDTO;
@Component("planParser")
public class PlanParser {

	public ModelMapper modelMapper;
	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public MealPlans convertToEntity(MealPlansDTO mealPlan) {
		MealPlans mealPlans = modelMapper.map(mealPlan, MealPlans.class);
	    return mealPlans;
	}
	
	public WorkoutPlans convertToEntity(WorkoutPlansDTO workoutPlan) {
		WorkoutPlans workoutPlans = modelMapper.map(workoutPlan, WorkoutPlans.class);
	    return workoutPlans;
	}
	
	public MealPlanDetailDTO convertToDto(MealPlanDetail mealPlan) {
		MealPlanDetailDTO mealPlanDetailDTO = modelMapper.map(mealPlan,
				MealPlanDetailDTO.class);
		return mealPlanDetailDTO;
	}
	
	public MealTimingsDTO convertToDto(MealTimings plan) {
		MealTimingsDTO mealTimingDTO = modelMapper.map(plan,
				MealTimingsDTO.class);
		return mealTimingDTO;
	}

	public MealPlansDTO convertToDto(MealPlans mealPlan) {
		MealPlansDTO mealPlanDTO = modelMapper.map(mealPlan,
				MealPlansDTO.class);
		return mealPlanDTO;
	}

	public TimingDTO convertToDto(Timing timing) {
		TimingDTO timingDTO = modelMapper.map(timing,
				TimingDTO.class);
		return timingDTO;
	}

	public WorkoutPlansDTO convertToDto(WorkoutPlans workoutPlan) {
		WorkoutPlansDTO workoutDTO = modelMapper.map(workoutPlan,
				WorkoutPlansDTO.class);
		return workoutDTO;
	}

}
