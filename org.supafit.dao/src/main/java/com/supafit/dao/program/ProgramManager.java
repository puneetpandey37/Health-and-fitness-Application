package com.supafit.dao.program;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.supafit.dao.program.model.Program;
import com.supafit.dao.program.model.ProgramCategory;
import com.supafit.dao.program.model.ProgramSubscription;
import com.supafit.dao.program.model.ProgramType;

@Repository("programManager")
@Transactional(propagation = Propagation.REQUIRED)
public class ProgramManager {

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Program> getPrograms(String programQuery) {
		TypedQuery<Program> query = entityManager.createQuery(programQuery,
				Program.class);
		return query.getResultList();
	}

	public List<ProgramCategory> getProgramCategories() {
		TypedQuery<ProgramCategory> query = entityManager.createNamedQuery(
				"ProgramCategory.findAll", ProgramCategory.class);
		return query.getResultList();
	}

	public List<ProgramType> getProgramTypes() {
		TypedQuery<ProgramType> query = entityManager.createNamedQuery(
				"ProgramType.findAll", ProgramType.class);
		return query.getResultList();
	}

	public ProgramSubscription subscribe(ProgramSubscription subscription) {
		return entityManager.merge(subscription);
	}

	public List<ProgramSubscription> getRecentlySubscribedUsers(String startDate) {
		List<ProgramSubscription> results = entityManager
	            .createNamedQuery("ProgramSubscription.findByDate", ProgramSubscription.class)
	            .setParameter("startDate", startDate)
	            .getResultList();
	      return results;
	}

	public List<ProgramSubscription> getRecentlySubscribedUsersByCoachId(
			String startDate, Long coachId) {
		List<ProgramSubscription> results = entityManager
	            .createNamedQuery("ProgramSubscription.findByCoachId", ProgramSubscription.class)
	            .setParameter("startDate", startDate)
	            .setParameter("coachId", coachId)
	            .getResultList();
	      return results;
	}
	
	public List<ProgramSubscription> getRecentlySubscribedUsersByDietitianId(
			String startDate, Long dietitianId) {
		List<ProgramSubscription> results = entityManager
	            .createNamedQuery("ProgramSubscription.findByDietitianId", ProgramSubscription.class)
	            .setParameter("startDate", startDate)
	            .setParameter("dietitianId", dietitianId)
	            .getResultList();
	      return results;
	}
	
}
