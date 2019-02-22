package com.supafit.dao.summary;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.supafit.dao.summary.model.ActivitySummary;

@Repository("summaryManager")
@Transactional(propagation = Propagation.REQUIRED)
public class SummaryManager {

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public ActivitySummary createSummary(ActivitySummary summary) {
		return entityManager.merge(summary);
	}
	
	public ActivitySummary getTodaysSummary(Long userId, String date) {
		List<ActivitySummary> results = entityManager
	            .createNamedQuery("ActivitySummary.findByDateAndUserId", ActivitySummary.class)
	            .setParameter("userId", userId)
	            .setParameter("date", date)
	            .getResultList();
		return results.isEmpty() ? null : results.get(0);
	}
	
	public List<ActivitySummary> getUsersSummary(Long userId) {
		List<ActivitySummary> results = entityManager
	            .createNamedQuery("ActivitySummary.findByUserId", ActivitySummary.class)
	            .setParameter("userId", userId)
	            .getResultList();
		return results;
	}
	
}
