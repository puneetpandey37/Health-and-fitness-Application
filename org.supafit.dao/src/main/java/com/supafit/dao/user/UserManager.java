package com.supafit.dao.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.supafit.dao.coach.model.CoachType;
import com.supafit.dao.location.model.Address;
import com.supafit.dao.user.model.PhoneNumber;
import com.supafit.dao.user.model.User;
import com.supafit.dao.user.model.UserFitnessGoal;
import com.supafit.dao.user.model.UserFoodPreferences;
import com.supafit.dao.user.model.UserMedicalCondition;
import com.supafit.dao.user.model.UserPhysic;

@Repository("userManager")
@Transactional(propagation = Propagation.REQUIRED)
//@Scope("request")
public class UserManager {

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public User createUser(User user) {
		return entityManager.merge(user);
	}
	
	public User getUserByUserId(String userId) {
		List<User> results = entityManager
	            .createNamedQuery("User.findByEmialId", User.class)
	            .setParameter("email", userId).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public User getUserByEmailId(String email) {
		List<User> results = entityManager
	            .createNamedQuery("User.findByEmialId", User.class)
	            .setParameter("email", email).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public PhoneNumber createUpdatePhooneNumber(PhoneNumber phoneNumber) {
		return entityManager.merge(phoneNumber);
	}

	public UserPhysic createUpdateUserPhysic(UserPhysic userPhysic) {
		return entityManager.merge(userPhysic);
	}

	public User getUserDetail(Long userId) {
		List<User> results = entityManager
	            .createNamedQuery("User.findByUserId", User.class)
	            .setParameter("userId", userId)
	            .getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public UserPhysic getUserPhysic(Long userId) {
		List<UserPhysic> results = entityManager
	            .createNamedQuery("UserPhysic.findByUserId", UserPhysic.class)
	            .setParameter("userId", userId).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public List<PhoneNumber> getPhoneNumber(Long userId) {
		List<PhoneNumber> results = entityManager
	            .createNamedQuery("PhoneNumber.findByUserId", PhoneNumber.class)
	            .setParameter("userId", userId).getResultList();
	      return results;
	}

	public User getUserByUserNameAndPassword(String userName, String password) {
		List<User> results = entityManager
	            .createNamedQuery("User.findByUserNameAndPassword", User.class)
	            .setParameter("userName", userName)
	            .setParameter("password", password)
	            .getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public User getUserDetail(Long userId, Long coachId) {
		List<User> results = entityManager
	            .createNamedQuery("User.findByUserAndCoachId", User.class)
	            .setParameter("userId", userId)
	            .setParameter("coachId", coachId)
	            .getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public List<CoachType> getCoachTypes() {
	      return entityManager
		            .createNamedQuery("CoachType.findAll", CoachType.class)
		            .getResultList();
	}
	
	public List<User> findUsersUnderthisTrainer(long coachId) {
	      return entityManager
		            .createNamedQuery("User.findUserUnderThisTrainer", User.class)
		            .setParameter("coachId", coachId)
		            .getResultList();
	}
	
	public List<User> findUsersUnderthisDietitian(long dietitanId) {
	      return entityManager
		            .createNamedQuery("User.findUserUnderThisDietitian", User.class)
		            .setParameter("dietitanId", dietitanId)
		            .getResultList();
	}
	
	public List<User> getDietitians(long coachTypeId) {
	      return entityManager
		            .createNamedQuery("User.getDietitians", User.class)
		            .setParameter("coachTypeId", coachTypeId)
		            .getResultList();
	}
	
	public List<User> findUsersUnderCoach(long coachId) {
	      return entityManager
		            .createNamedQuery("User.findUsersByCoachId", User.class)
		            .setParameter("coachId", coachId)
		            .getResultList();
	}
	
	public List<User> findUsersUnderDietition(long deititianId) {
	      return entityManager
		            .createNamedQuery("User.findUsersByDietitanId", User.class)
		            .setParameter("dietitanId", deititianId)
		            .getResultList();
	}

	public void assignCoachToUser(Long coachId, Long userId) {
		entityManager
        .createNamedQuery("User.assignCoachToUser")
        .setParameter("coachId", coachId)
        .setParameter("id", userId)
        .executeUpdate();
	}

	public void assignDietitanToUser(Long dietitanId, Long userId) {
		entityManager
        .createNamedQuery("User.assignDietitanToUser")
        .setParameter("dietitanId", dietitanId)
        .setParameter("id", userId)
        .executeUpdate();
	}
	
	public void assignYogaTrainerToUser(Long yogaTrianerId, Long userId) {
		entityManager
        .createNamedQuery("User.assignYogaTrianerToUser")
        .setParameter("yogaTrainerId", yogaTrianerId)
        .setParameter("id", userId)
        .executeUpdate();
	}

	public void updateGcmId(Long userId, String gcmId) {
		entityManager
        .createNamedQuery("User.updateGcmId")
        .setParameter("gcmId", gcmId)
        .setParameter("userId", userId)
        .executeUpdate();
	}

	public String getGcmId(Long userId) {
		List<String> results = entityManager
	            .createNamedQuery("User.getGcmId", String.class)
	            .setParameter("userId", userId)
	            .getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public List<User> getAllUsers() {
		 return entityManager
		            .createNamedQuery("User.findAll", User.class)
		            .getResultList();
	}

	public List<Address> getAddressesByUserId(Long userId) {
		 return entityManager
		            .createNamedQuery("Address.findByUserId", Address.class)
		            .setParameter("userId", userId)
		            .getResultList();
	}

	public Address createAddress(Address address) {
		return entityManager.merge(address);
	}

	public UserFoodPreferences createUserFoodPreference(
			UserFoodPreferences userFoodPreference) {
		return entityManager.merge(userFoodPreference);
	}

	public void deleteUserFoodPreferences(Long userId) {
		entityManager
		.createNamedQuery("UserFoodPreferences.deleteByUserId")
		.setParameter("userId", userId).executeUpdate();
	}

	public List<UserFoodPreferences> getUserFoodPreferences(Long userId) {
		 return entityManager
		            .createNamedQuery("UserFoodPreferences.findByUserId", UserFoodPreferences.class)
		            .setParameter("userId", userId)
		            .getResultList();
	}

	public UserMedicalCondition createUserMedicalConditions(
			UserMedicalCondition medicalCondition) {
		return entityManager.merge(medicalCondition);
	}
	
	public void deleteUserMedicalCondition(Long userId) {
		entityManager
		.createNamedQuery("UserMedicalCondition.deleteByUserId")
		.setParameter("userId", userId).executeUpdate();
	}

	public List<UserMedicalCondition> getUserUserMedicalConditions(Long userId) {
		 return entityManager
		            .createNamedQuery("UserMedicalCondition.findByUserId", UserMedicalCondition.class)
		            .setParameter("userId", userId)
		            .getResultList();
	}

	public UserFitnessGoal createUserFitnessGoal(UserFitnessGoal userFitnessGoal) {
		return entityManager.merge(userFitnessGoal);
	}

	public void deleteFitnessGoal(Long userId) {
		entityManager
		.createNamedQuery("UserFitnessGoal.deleteByUserId")
		.setParameter("userId", userId).executeUpdate();
	}

	public List<UserFitnessGoal> getUserUserFitnessGoals(Long userId) {
		 return entityManager
		            .createNamedQuery("UserFitnessGoal.findByUserId", UserFitnessGoal.class)
		            .setParameter("userId", userId)
		            .getResultList();
	}

	public CoachType getCoachTypesById(Long coachTypeId) {
		List<CoachType> results = entityManager
	            .createNamedQuery("CoachType.findById", CoachType.class)
	            .setParameter("id", coachTypeId)
	            .getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public List<User> getRecentlyRegisteredUsers(String startDate,
			String endDate) {
		return entityManager
	            .createNamedQuery("User.findUsersByDateRange", User.class)
	            .setParameter("startDate", startDate)
	            .setParameter("endDate", endDate)
	            .getResultList();
	}

	public List<User> getAnyCoachUnassignedUsers() {
		return entityManager
	            .createNamedQuery("User.findWithoutAnyOfCoaches", User.class)
	            .getResultList();
	}

	public List<User> getUnAssignedTrainerUsers() {
		return entityManager
	            .createNamedQuery("User.findUnAssignedTrainerUsers", User.class)
	            .getResultList();
	}

	public List<User> getUnAssignedDietitanUsers() {
		return entityManager
	            .createNamedQuery("User.findUnAssignedDietitanrUsers", User.class)
	            .getResultList();
	}

	public Long getTrainerId(Long coachId) {
		List<Long> results = entityManager
	            .createNamedQuery("User.findTraierIdByUserId", Long.class)
	            .setParameter("coachId", coachId)
	            .getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public Long getDietitianId(Long coachId) {
		List<Long> results = entityManager
	            .createNamedQuery("User.findDietitianIdByUserId", Long.class)
	            .setParameter("dietitanId", coachId)
	            .getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}
	
	public Long getYogaTrainerId(Long yogaTrainer) {
		List<Long> results = entityManager
	            .createNamedQuery("User.findYogaTrainerIdByUserId", Long.class)
	            .setParameter("yogaTrainer", yogaTrainer)
	            .getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public User getUserByIdAndDietitianId(Long userId, Long coachId) {
		List<User> results = entityManager
	            .createNamedQuery("User.findByUserAndDietitianId", User.class)
	            .setParameter("userId", userId)
	            .setParameter("coachId", coachId)
	            .getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public User getUserByIdAndYogaTrainerId(Long userId, Long coachId) {
		List<User> results = entityManager
	            .createNamedQuery("User.findByUserAndYogaTrainerId", User.class)
	            .setParameter("userId", userId)
	            .setParameter("coachId", coachId)
	            .getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public List<User> findUsersUnderYogaTrainer(Long coachId) {
		List<User> results = entityManager
	            .createNamedQuery("User.findUsersBuYogaTrainerId", User.class)
	            .setParameter("coachId", coachId)
	            .getResultList();
	      return results;
	}

	public List<User> findUsersUnderthisTrainerByDate(Long coachId,
			String startDate) {
		  return entityManager
		            .createNamedQuery("User.findUserUnderThisTrainerByDate", User.class)
		            .setParameter("coachId", coachId)
		            .setParameter("date", startDate)
		            .getResultList();
	}

	public List<User> findUsersUnderDietitionByDate(Long coachId, String date) {
		 return entityManager
		            .createNamedQuery("User.findUsersUnderDietitionByDate", User.class)
		            .setParameter("coachId", coachId)
		            .setParameter("date", date)
		            .getResultList();
	}

	public List<User> findUsersUnderYogaTrainerByDate(Long coachId, String date) {
		 return entityManager
		            .createNamedQuery("User.findUsersUnderYogaTrainerByDate", User.class)
		            .setParameter("coachId", coachId)
		            .setParameter("date", date)
		            .getResultList();
	}
}
