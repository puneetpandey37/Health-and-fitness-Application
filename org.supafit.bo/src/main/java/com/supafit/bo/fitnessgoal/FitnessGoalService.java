package com.supafit.bo.fitnessgoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.goal.FitnessGoalManager;
import com.supafit.dao.goal.model.FitnessGoal;

@Component
public class FitnessGoalService {

	@Autowired
	private FitnessGoalManager fitnessGoalManager;

	public void setFitnessGoalManager(FitnessGoalManager fitnessGoalManager) {
		this.fitnessGoalManager = fitnessGoalManager;
	}
	
	public List<FitnessGoal> getFitnessGoals() {
		return fitnessGoalManager.getFitnessGoals();
	}

	public List<FitnessGoal> createFitnessGoals(
			List<FitnessGoal> fitnessGoal) {
		return fitnessGoalManager.createFitnessGoals(fitnessGoal);
	}

	public Long deleteFitnessGoal(Long fitnessGoalId) {
			return fitnessGoalManager.deleteFitnessGoal(fitnessGoalId);
	}

	public FitnessGoal getFitnessGoalById(long fitnessGoalId) {
		return fitnessGoalManager.getFitnessGoalById(fitnessGoalId);
	}
	
	public List<FitnessGoal> updateFitnessGoals(
			List<FitnessGoal> ftnessGoal) {
		return fitnessGoalManager.createFitnessGoals(ftnessGoal);
	}
}
