package com.supafit.bo.medicalcondition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.medicalcondition.MedicalConditionManagaer;
import com.supafit.dao.medicalcondition.model.MedicalCondition;

@Component
public class MedicalConditionService {

	private MedicalConditionManagaer medicalConditionManager;
	@Autowired
	public void setMedicalConditionManager(
			MedicalConditionManagaer medicalConditionManager) {
		this.medicalConditionManager = medicalConditionManager;
	}
	
	public List<MedicalCondition> getMedicalConditions() {
		return medicalConditionManager.getMedicalConditions();
	}

	public List<MedicalCondition> createMedicalConditions(
			List<MedicalCondition> medicalCondition) {
		return medicalConditionManager.createMedicalConditions(medicalCondition);
	}

	public Long deleteMedicalCondition(Long medConditionId) {
		medicalConditionManager.deleteMedicalCondition(medConditionId);
		return medConditionId;
	}

	public MedicalCondition getMedicalConditionById(long medicalConditionId) {
		return medicalConditionManager.getMedicalConditionById(medicalConditionId);
	}

	public List<MedicalCondition> updateMedicalConditions(
			List<MedicalCondition> medCondition) {
		return medicalConditionManager.createMedicalConditions(medCondition);
	}
}
