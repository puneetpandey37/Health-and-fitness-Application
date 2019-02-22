package com.supafit.dao.coach;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.supafit.dao.coach.model.Coach;
import com.supafit.dao.coach.model.CoachAddress;
import com.supafit.dao.coach.model.CoachMessages;
import com.supafit.dao.coach.model.CoachPhoneNumber;
import com.supafit.dao.coach.model.CoachType;

@Repository("coachManager")
@Transactional(propagation = Propagation.REQUIRED)
public class CoachManager {

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Coach createCoach(Coach coach) {
		return entityManager.merge(coach);
	}
	
	public List<Coach> getAllCoaches() {
		return entityManager
	            .createNamedQuery("Coach.findAll", Coach.class)
	            .getResultList();
	}
	
	/*public List<Coach> getCoachesByType(Long coachTypeId) {
		return entityManager
	            .createNamedQuery("Coach.findByType", Coach.class)
	            .setParameter("id", coachTypeId)
	            .getResultList();
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Coach> getCoachesByType(Long coachTypeId) {
		String sqlQuery = "SELECT c.* FROM supafit.coach c, supafit.coach_type_mapping m, coach_type ct"
							+" where c.id = m.coach_id"
								+" and ct.id = m.coach_type_id"
									+" and m.coach_type_id = "+coachTypeId;
			Query q = entityManager.createNativeQuery(sqlQuery, Coach.class);
			return q.getResultList();
	}
	
	public List<CoachType> getCoachTypes() {
		return entityManager
	            .createNamedQuery("CoachType.findAll", CoachType.class)
	            .getResultList();
	}
	
	public CoachType getCoachTypeById(Long id) {
		List<CoachType> result = entityManager
	            .createNamedQuery("CoachType.findById", CoachType.class)
	            .setParameter("id", id)
	            .getResultList();
		return result.isEmpty() ? null : result.get(0);
	}

	public Coach getCoachesById(Long coachId) {
		List<Coach> results = entityManager
	            .createNamedQuery("Coach.findById", Coach.class)
	            .setParameter("id", coachId).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public List<CoachAddress> getAddressesByCoachId(Long coachId) {
		return entityManager
	            .createNamedQuery("CoachAddress.findByCoachId", CoachAddress.class)
	            .setParameter("coachId", coachId)
	            .getResultList();
	}

	public List<CoachPhoneNumber> getPhoneNumbersByCoachId(Long coachId) {
		return entityManager
	            .createNamedQuery("CoachPhoneNumber.findByCoachId", CoachPhoneNumber.class)
	            .setParameter("coachId", coachId)
	            .getResultList();
	}

	public Coach getCoachByEmailId(String email) {
		List<Coach> results = entityManager
	            .createNamedQuery("Coach.findByEmialId", Coach.class)
	            .setParameter("email", email).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public CoachPhoneNumber createUpdatePhooneNumber(
			CoachPhoneNumber phoneNumber) {
		return entityManager.merge(phoneNumber);
	}

	public CoachAddress createAddress(CoachAddress address) {
		return entityManager.merge(address);
	}

	public Coach getCoachByUserNameAndPassword(String userName, String password) {
		List<Coach> results = entityManager
	            .createNamedQuery("Coach.findByUserNameAndPassword", Coach.class)
	            .setParameter("userName", userName)
	            .setParameter("password", password)
	            .getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public Coach getUserByUserId(String userId) {
		List<Coach> results = entityManager
	            .createNamedQuery("Coach.findByEmialId", Coach.class)
	            .setParameter("email", userId).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public CoachMessages createCoachMessages(CoachMessages coachMessagesRequest) {
		return entityManager.merge(coachMessagesRequest);
	}

	public List<CoachMessages> getCoachMessagesByUserAndCoach(Long coachId, Long userId) {
		List<CoachMessages> results = entityManager
	            .createNamedQuery("CoachMessages.findByCoachIdAndUserId", CoachMessages.class)
	            .setParameter("userId", userId)
	            .setParameter("coachId", coachId)
	            .getResultList();
	      return results;
	}
	
	public List<CoachMessages> getCoachMessagesByUser(Long userId) {
		List<CoachMessages> results = entityManager
	            .createNamedQuery("CoachMessages.findByUserId", CoachMessages.class)
	            .setParameter("userId", userId)
	            .getResultList();
	      return results;
	}

	public Long deleteCoachMessages(Long messageId) {
		entityManager
		.createNamedQuery("CoachMessages.deleteById")
		.setParameter("id", messageId).executeUpdate();
		return messageId;
	}
}
