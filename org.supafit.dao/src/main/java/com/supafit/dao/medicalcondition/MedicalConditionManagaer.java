package com.supafit.dao.medicalcondition;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.supafit.dao.medicalcondition.model.MedicalCondition;

@Repository("medicalConditionManager")
@Transactional(propagation = Propagation.REQUIRED)
public class MedicalConditionManagaer {

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<MedicalCondition> getMedicalConditions() {
		return entityManager
	            .createNamedQuery("MedicalCondition.findAll", MedicalCondition.class)
	            .getResultList();
	}

	public List<MedicalCondition> createMedicalConditions(
			List<MedicalCondition> medicalCondition) {
		return entityManager.merge(medicalCondition);
	}

	public Long deleteMedicalCondition(Long medConditionId) {
			entityManager
			.createNamedQuery("MedicalCondition.deleteByMedicalConditionId")
			.setParameter("medicalConditionId", medConditionId).executeUpdate();
			return medConditionId;
	}

	public MedicalCondition getMedicalConditionById(long medicalConditionId) {
		List<MedicalCondition> results = entityManager
	            .createNamedQuery("MedicalCondition.findById", MedicalCondition.class)
	            .setParameter("id", medicalConditionId).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}
}
