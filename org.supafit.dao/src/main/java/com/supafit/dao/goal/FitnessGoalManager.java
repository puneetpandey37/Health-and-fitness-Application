package com.supafit.dao.goal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.supafit.dao.goal.model.FitnessGoal;

@Repository("fitnessGoalManager")
@Transactional(propagation = Propagation.REQUIRED)
public class FitnessGoalManager {

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<FitnessGoal> getFitnessGoals() {
		return entityManager
	            .createNamedQuery("FitnessGoal.findAll", FitnessGoal.class)
	            .getResultList();
	}

	public List<FitnessGoal> createFitnessGoals(
			List<FitnessGoal> fitnessGoal) {
		return entityManager.merge(fitnessGoal);
	}

	public Long deleteFitnessGoal(Long fitnessGoalId) {
			entityManager
			.createNamedQuery("FitnessGoal.deleteByFitnessGoalId")
			.setParameter("fitnessGoalId", fitnessGoalId).executeUpdate();
			return fitnessGoalId;
	}

	public FitnessGoal getFitnessGoalById(long fitnessGoalId) {
		List<FitnessGoal> results = entityManager
	            .createNamedQuery("FitnessGoal.findById", FitnessGoal.class)
	            .setParameter("id", fitnessGoalId).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}
}
