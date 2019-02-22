package com.supafit.bo.user;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.supafit.common.exceptions.InvalidRequestException;
import com.supafit.common.notification.EmailNotification;
import com.supafit.common.security.AuthorizationCodeServices;
import com.supafit.common.security.LoggedInUserUtil;
import com.supafit.common.security.SupafitAuthorization;
import com.supafit.common.security.SupafitCoachLoginResponse;
import com.supafit.common.security.SupafitUserLoginResponse;
import com.supafit.dao.coach.CoachManager;
import com.supafit.dao.coach.model.Coach;
import com.supafit.dao.coach.model.CoachAddress;
import com.supafit.dao.coach.model.CoachPhoneNumber;
import com.supafit.dao.coach.model.CoachType;
import com.supafit.dao.food.FoodManager;
import com.supafit.dao.food.model.FoodPreferences;
import com.supafit.dao.goal.FitnessGoalManager;
import com.supafit.dao.goal.model.FitnessGoal;
import com.supafit.dao.location.model.Address;
import com.supafit.dao.medicalcondition.MedicalConditionManagaer;
import com.supafit.dao.medicalcondition.model.MedicalCondition;
import com.supafit.dao.program.ProgramManager;
import com.supafit.dao.program.model.ProgramSubscription;
import com.supafit.dao.user.UserManager;
import com.supafit.dao.user.model.PhoneNumber;
import com.supafit.dao.user.model.User;
import com.supafit.dao.user.model.UserFitnessGoal;
import com.supafit.dao.user.model.UserFoodPreferences;
import com.supafit.dao.user.model.UserMedicalCondition;
import com.supafit.dao.user.model.UserPhysic;

@Component
public class UserService {

	private final String TAG = this.getClass().getName();
	Logger logger = Logger.getLogger( UserService.class.getName() );
	private AuthorizationCodeServices authorizationCodeServices;
	private UserManager userManager;
	private SupafitAuthorization supafitAuthorization;
	private LoggedInUserUtil loggedInUserUtil;
	private FoodManager foodManagaer;
	private MedicalConditionManagaer medicalConditionManagaer;
	private FitnessGoalManager fitnessGoalManager;
	private CoachManager coachManager;
	private ProgramManager programManager;
	
	
	@Autowired
	public void setProgramManager(ProgramManager programManager) {
		this.programManager = programManager;
	}

	@Autowired
	public void setCoachManager(CoachManager coachManager) {
		this.coachManager = coachManager;
	}

	@Autowired
	public void setFitnessGoalManager(FitnessGoalManager fitnessGoalManager) {
		this.fitnessGoalManager = fitnessGoalManager;
	}

	@Autowired
	public void setMedicalConditionManagaer(
			MedicalConditionManagaer medicalConditionManagaer) {
		this.medicalConditionManagaer = medicalConditionManagaer;
	}

	@Autowired
	public void setFoodManagaer(FoodManager foodManagaer) {
		this.foodManagaer = foodManagaer;
	}

	@Autowired
	public void setLoggedInUserUtil(LoggedInUserUtil loggedInUserUtil) {
		this.loggedInUserUtil = loggedInUserUtil;
	}

	@Autowired
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	@Autowired
	public void setAuthorizationCodeServices(
			AuthorizationCodeServices authorizationCodeServices) {
		this.authorizationCodeServices = authorizationCodeServices;
	}

	@Autowired
	public void setSupafitAuthorization(
			SupafitAuthorization supafitAuthorization) {
		this.supafitAuthorization = supafitAuthorization;
	}

	public SupafitUserLoginResponse socialLogin(String oauthServer,
			String authorizationCode, String clientCredentials) throws Exception {
		return authorizationCodeServices.socialLogin(oauthServer,
				authorizationCode);
	}
	
	public SupafitUserLoginResponse socialLogin(String oauthServer,
			String authorizationCode, String clientCredentials, String origin) throws Exception {
		return authorizationCodeServices.socialLogin(oauthServer,
				authorizationCode, clientCredentials, origin);
	}

	@Transactional
	public User supafitSignup(User user, String clientCredentials) throws Exception {
		
		authorizationCodeServices.validateClient(clientCredentials);
		supafitAuthorization.emailValidation(user);
		user.setUserId(user.getEmail());
		user.setDateCreated(getCurrentDate());
		User createdUser = userManager.createUser(user);
		String weight;
		String height;
		Integer age;
		;
		String bmi;
		;
		UserPhysic userPhysic = user.getUserPhysic();
		if (userPhysic != null) {
			weight = userPhysic.getWeight();
			height = userPhysic.getHeight();
//			age = calculateAge(user.getDob());
			bmi = getBmi(weight, height);
//			userPhysic.setAge(age);
			userPhysic.setBmi(bmi);
			userPhysic.setUserId(createdUser.getId());
			createdUser.setUserPhysic(userManager
					.createUpdateUserPhysic(userPhysic));
		}

		List<PhoneNumber> phoneNumbers = user.getPhoneNumbers();
		List<PhoneNumber> resultPhnNumber = new ArrayList<PhoneNumber>();
		if(phoneNumbers != null) {
			for (PhoneNumber phoneNumber : phoneNumbers) {
				phoneNumber.setUserId(createdUser.getId());
				phoneNumber = userManager.createUpdatePhooneNumber(phoneNumber);
				resultPhnNumber.add(phoneNumber);
			}
		}
		createdUser.setPhoneNumbers(resultPhnNumber);
		
		List<Address> addresses = user.getUserAddresses();
		List<Address> userAddresss = new ArrayList<Address>();
		if(addresses != null) {
			for(Address address : addresses) {
				address.setUserId(createdUser.getId());
				address = userManager.createAddress(address);
				userAddresss.add(address);
			}
		}
		createdUser.setUserAddresses(userAddresss);
		EmailNotification emailNotification = new EmailNotification(createdUser, null);
		emailNotification.sendLocalSignupMail();
		return createdUser;
	}

	private String getBmi(String weight, String height) {
		String bmi = null;
		Double dBmi = null;
		if (weight != null && height != null) {
			Double dWeight = Double.valueOf(weight);
			Double dHeight = Double.valueOf(height);
			dBmi = (dWeight / (dHeight * dHeight)) * 1000;
			bmi = String.valueOf(dBmi);
		}
		return bmi;
	}

	@Transactional
	public User updateUser(User user) throws Exception {

//		long userId = loggedInUserUtil.getLoggedInUserId();
		user.setDateUpdated(getCurrentDate());
		long userId = user.getId();
		user.setId(userId);
		String dob = user.getDob();
		List<PhoneNumber> phoneNumbers = user.getPhoneNumbers();
		if(phoneNumbers != null) {
			for (PhoneNumber phoneNumber : phoneNumbers) {
				phoneNumber.setUserId(userId);
				userManager.createUpdatePhooneNumber(phoneNumber);
			}
		}
		UserPhysic userPhysic = user.getUserPhysic();
		if (userPhysic != null) {
			userPhysic.setUserId(userId);
			if (dob != null) {
//				userPhysic.setAge(calculateAge(dob));
				userPhysic.setAge(Integer.valueOf(dob));
			}
			String weight = userPhysic.getWeight();
			String height = userPhysic.getHeight();
			if (weight != null && height != null) {
				userPhysic.setBmi(getBmi(weight, height));
			}
			userManager.createUpdateUserPhysic(userPhysic);
		}
		userManager.createUser(user);
		return user;
	}

	public User getUserDetail(Long userId) {

		User user = null;
		UserPhysic userPhysic = null;
		List<PhoneNumber> phoneNumbers = null;
	/*	if (userId == null) {
			user = loggedInUserUtil.getLoggedInUser();
			userId = user.getId();
		} else {*/
			user = userManager.getUserDetail(userId);
//		}
		List<Address> addresses = userManager.getAddressesByUserId(userId);
		if(user != null) {
			user.setUserAddresses(addresses);
			phoneNumbers = userManager.getPhoneNumber(userId);
			user.setPhoneNumbers(phoneNumbers);
			userPhysic = userManager.getUserPhysic(userId);
			user.setUserPhysic(userPhysic);
			user.setFoodPreferences(getUserFoodPreferences(userId));
			user.setGoals(getUserFitnessGoals(userId));
			user.setMedicalConditions(getUserMedicalConditions(userId));
		}
		return user;
	}

	public SupafitUserLoginResponse supafitLogIn(String userCredentials)
			throws InvalidRequestException, ClientProtocolException,
			IOException, URISyntaxException {
		return authorizationCodeServices.supafitLogIn(userCredentials);
	}
	
	public SupafitCoachLoginResponse coachSignIn(String userCredentials)
			throws InvalidRequestException, ClientProtocolException,
			IOException, URISyntaxException {
		return authorizationCodeServices.supafitCoachLogIn(userCredentials);
	}

	public int calculateAge(String dob) {
		int years = 0;
		int months = 0;
		int days = 0;
		Date birthDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			birthDate = formatter.parse(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar birthDay = Calendar.getInstance();
		birthDay.setTimeInMillis(birthDate.getTime());
		long currentTime = System.currentTimeMillis();
		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(currentTime);
		years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
		int currMonth = now.get(Calendar.MONTH) + 1;
		int birthMonth = birthDay.get(Calendar.MONTH) + 1;
		months = currMonth - birthMonth;
		if (months < 0) {
			years--;
			months = 12 - birthMonth + currMonth;
			if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
				months--;
		} else if (months == 0
				&& now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
			years--;
			months = 11;
		}
		if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
			days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
		else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
			int today = now.get(Calendar.DAY_OF_MONTH);
			now.add(Calendar.MONTH, -1);
			days = now.getActualMaximum(Calendar.DAY_OF_MONTH)
					- birthDay.get(Calendar.DAY_OF_MONTH) + today;
		} else {
			days = 0;
			if (months == 12) {
				years++;
				months = 0;
			}
		}
		return years;
	}

	public User getUserDetail(Long userId, Long coachId) {
		return userManager.getUserDetail(userId, coachId);
	}

	public List<User> findUsersUnderthisTrainer(long trainerId) {
		
		List<User> users = userManager.findUsersUnderthisTrainer(trainerId);
		List<User> result = new ArrayList<User>();
		if(users != null) {
			for(User user : users) {
				Long userId = user.getId();
				List<Address> addresses = userManager.getAddressesByUserId(userId);
				user.setUserAddresses(addresses);
				List<PhoneNumber> phoneNumbers = userManager.getPhoneNumber(userId);
				user.setPhoneNumbers(phoneNumbers);
				UserPhysic userPhysic = userManager.getUserPhysic(userId);
				user.setUserPhysic(userPhysic);
				user.setFoodPreferences(getUserFoodPreferences(userId));
				user.setGoals(getUserFitnessGoals(userId));
				user.setMedicalConditions(getUserMedicalConditions(userId));
				result.add(user);
			}
		}
		return result;
	}

	
	public List<User> findUsersUnderthisDietitian(long dietitianId) {
		
		List<User> users = userManager.findUsersUnderthisDietitian(dietitianId);
		List<User> result = new ArrayList<User>();
		if(users != null) {
			for(User user : users) {
				Long userId = user.getId();
				List<Address> addresses = userManager.getAddressesByUserId(userId);
				user.setUserAddresses(addresses);
				List<PhoneNumber> phoneNumbers = userManager.getPhoneNumber(userId);
				user.setPhoneNumbers(phoneNumbers);
				UserPhysic userPhysic = userManager.getUserPhysic(userId);
				user.setUserPhysic(userPhysic);
				user.setFoodPreferences(getUserFoodPreferences(userId));
				user.setGoals(getUserFitnessGoals(userId));
				user.setMedicalConditions(getUserMedicalConditions(userId));
				result.add(user);
			}
		}
		return result;
	}
	
	public List<User> getDietitians(long userTypeId) {
		return userManager.getDietitians(userTypeId);
	}

	public List<User> findUsersUnderCoach(long coachId) {
		List<User> users =  userManager.findUsersUnderCoach(coachId);
		List<User> result = new ArrayList<User>();
		if(users != null) {
			for(User user : users) {
				Long userId = user.getId();
				List<Address> addresses = userManager.getAddressesByUserId(userId);
				user.setUserAddresses(addresses);
				result.add(user);
			}
		}
		return result;
	}

	public List<User> findUsersUnderDietition(long deititianId) {
		List<User> users =   userManager.findUsersUnderDietition(deititianId);
		List<User> result = new ArrayList<User>();
		if(users != null) {
			for(User user : users) {
				Long userId = user.getId();
				List<Address> addresses = userManager.getAddressesByUserId(userId);
				user.setUserAddresses(addresses);
				result.add(user);
			}
		}
		return result;
	}

	public User assignCoachToUser(Long coachId, Long userId, Long coachTypeId) throws InvalidRequestException {
		Coach coach = coachManager.getCoachesById(coachId);
		User result = new User();
			if(coach != null) {
//				List<CoachType> coachTypes = coach.getCoachTypes();
//				if(coachTypes != null) {
//					CoachType type = coachManager.getCoachTypeById(coachTypeId);
//					boolean isValid = false;
//					for(CoachType coachType : coachTypes) {
//						if(type.getType().equalsIgnoreCase(coachType.getType())){
//							isValid = true;
//							break;
//						}
//					}
//					if(isValid) {
//						String cType = type.getType();
//						if(("Trainer").equalsIgnoreCase(cType)) {
//							userManager.assignCoachToUser(coachId, userId);
//						} else if(("Dietitian").equalsIgnoreCase(cType)) {
//							userManager.assignDietitanToUser(coachId, userId);
//						} else if(("Yoga Trainer").equalsIgnoreCase(cType)) {
//							userManager.assignYogaTrainerToUser(coachId, userId);
//						}
//						result = userManager.getUserDetail(userId);
//					} else {
//						if("Admin".equalsIgnoreCase(type.getType()))
//							throw new InvalidRequestException("The Coach is not an "+type.getType());
//						else
//							throw new InvalidRequestException("The Coach is not a "+type.getType());
//					}
//				}
			}
		return result;
	}
	
	public String assignDietitanToUser(Long dietitanId, Long userId) throws InvalidRequestException {
		String result = null;
		try {
			userManager.assignDietitanToUser(dietitanId, userId);
			result = "success";
		} catch (Exception e) {
			throw new InvalidRequestException("Cannot assign Dietitan to user");
		}
		return result;
	}

	/*public String manageUserGcmId(String gcmId) {
		User user = loggedInUserUtil.getLoggedInUser();
		Long userId = user.getId();
		if(gcmId != null && gcmId != "") {
			userManager.updateGcmId(userId, gcmId);
		} else {
			gcmId = userManager.getGcmId(userId);
		}
		return gcmId;
	}*/

	public List<User> getAllUsers() {
		List<User> users = userManager.getAllUsers();
		List<User> result = new ArrayList<User>();
		if(users != null) {
			for(User user : users) {
				Long userId = user.getId();
				List<PhoneNumber> phoneNumbers = userManager.getPhoneNumber(userId);
				user.setPhoneNumbers(phoneNumbers);
				List<Address> addresses = userManager.getAddressesByUserId(userId);
				user.setGoals(getUsersFitnessGoals(userId));
				user.setMedicalConditions(getUserMedicalConditions(userId));
				user.setFoodPreferences(getUserFoodPreferences(userId));
				user.setUserAddresses(addresses);
				result.add(user);
			}
		}
		return result;
	}
	
	private List<FitnessGoal> getUsersFitnessGoals(Long userId) {
		List<UserFitnessGoal> userGoals = userManager.getUserUserFitnessGoals(userId);
		List<FitnessGoal> goals = new ArrayList<FitnessGoal>();
		if(userGoals != null) {
			for(UserFitnessGoal userGoal : userGoals) {
				Long goalId = userGoal.getFitnessGoalId();
				FitnessGoal goal = fitnessGoalManager.getFitnessGoalById(goalId);
				goals.add(goal);
			}
			
		}
		return goals;
	}
	
	public User getUserByCredential(String userName, String password) {
		return userManager.getUserByUserNameAndPassword(userName, password);
	}

	public List<FoodPreferences> createUserFoodPreferences(Long userId, List<FoodPreferences> foodPreferences) {
		List<FoodPreferences> response = new ArrayList<FoodPreferences>();
		if(foodPreferences != null) {
			for(FoodPreferences foodPreference : foodPreferences) {
				UserFoodPreferences userFoodPreference = new UserFoodPreferences();
				userFoodPreference.setUserId(userId);
				userFoodPreference = userManager.createUserFoodPreference(userFoodPreference);
				response.add(foodPreference);
			}
		}
		return response;
	}

	public List<FoodPreferences> updateUserFoodPreferences(Long userId,
			List<FoodPreferences> foodPreferences) {
		userManager.deleteUserFoodPreferences(userId);
		return createUserFoodPreferences(userId, foodPreferences);
	}

	public List<FoodPreferences> getUserFoodPreferences(Long userId) {
		List<UserFoodPreferences> userFoodPreferences = userManager.getUserFoodPreferences(userId);
		List<FoodPreferences> response = new ArrayList<FoodPreferences>();
		if(userFoodPreferences != null) {
			for(UserFoodPreferences userFoodPreference : userFoodPreferences) {
				long foodPreferenceId = userFoodPreference.getFoodPreferenceId();
				FoodPreferences foodPreferences = foodManagaer.getFoodPreferenceById(foodPreferenceId);
				response.add(foodPreferences);
			}
		}
		return response;
	}

	public List<MedicalCondition> createUserMedicalConditions(Long userId,
			List<MedicalCondition> medicalConditions) {
		List<MedicalCondition> response = new ArrayList<MedicalCondition>();
		if(medicalConditions != null) {
			for(MedicalCondition medicalCondition : medicalConditions) {
				UserMedicalCondition userMedicalCondition = new UserMedicalCondition();
				userMedicalCondition.setUserId(userId);
				userMedicalCondition = userManager.createUserMedicalConditions(userMedicalCondition);
				response.add(medicalCondition);
			}
		}
		return response;
	}

	public List<MedicalCondition> updateUserMedicalConditions(Long userId,
			List<MedicalCondition> medicalConditions) {
		userManager.deleteUserMedicalCondition(userId);
		return createUserMedicalConditions(userId, medicalConditions);
	}

	public List<MedicalCondition> getUserMedicalConditions(Long userId) {
		List<UserMedicalCondition> userMedicalConditions = userManager.getUserUserMedicalConditions(userId);
		List<MedicalCondition> response = new ArrayList<MedicalCondition>();
		if(userMedicalConditions != null) {
			for(UserMedicalCondition userMedicalCondition : userMedicalConditions) {
				long medicalConditionId = userMedicalCondition.getMedicalConditionId();
				MedicalCondition medicalCondition = medicalConditionManagaer.getMedicalConditionById(medicalConditionId);
				response.add(medicalCondition);
			}
		}
		return response;
	}

	public List<FitnessGoal> createUserFitnessGoal(Long userId,
			List<FitnessGoal> fitnessGoals) {
		List<FitnessGoal> response = new ArrayList<FitnessGoal>();
		if(fitnessGoals != null) {
			for(FitnessGoal fitnessGoal : fitnessGoals) {
				UserFitnessGoal userFitnessGoal = new UserFitnessGoal();
				userFitnessGoal.setUserId(userId);
				userFitnessGoal = userManager.createUserFitnessGoal(userFitnessGoal);
				response.add(fitnessGoal);
			}
		}
		return response;
	}

	public List<FitnessGoal> updateUserFitnessGoals(Long userId,
			List<FitnessGoal> fitnessGoals) {
		userManager.deleteFitnessGoal(userId);
		return createUserFitnessGoal(userId, fitnessGoals);
	}

	public List<FitnessGoal> getUserFitnessGoals(Long userId) {
		List<UserFitnessGoal> userFitnessGoals = userManager.getUserUserFitnessGoals(userId);
		List<FitnessGoal> response = new ArrayList<FitnessGoal>();
		if(userFitnessGoals != null) {
			for(UserFitnessGoal userFitnessGoal : userFitnessGoals) {
				long fitnessGoalId = userFitnessGoal.getFitnessGoalId();
				FitnessGoal fitnessGoal = fitnessGoalManager.getFitnessGoalById(fitnessGoalId);
				response.add(fitnessGoal);
			}
		}
		return response;
	}

	@Transactional
	public Coach supafitCoachSignup(Coach coach, String clientCredentials) throws Exception {
		
		authorizationCodeServices.validateClient(clientCredentials);
		supafitAuthorization.coachEmailValidation(coach);
		coach.setCoachId(coach.getEmail());
		Coach createdCoach = coachManager.createCoach(coach);
		List<CoachPhoneNumber> phoneNumbers = coach.getPhoneNumbers();
		List<CoachPhoneNumber> resultPhnNumber = new ArrayList<CoachPhoneNumber>();
		if(phoneNumbers != null) {
			for (CoachPhoneNumber phoneNumber : phoneNumbers) {
				phoneNumber.setCoachId(createdCoach.getId());
				phoneNumber = coachManager.createUpdatePhooneNumber(phoneNumber);
				resultPhnNumber.add(phoneNumber);
			}
		}
		createdCoach.setPhoneNumbers(resultPhnNumber);
		List<CoachAddress> addresses = coach.getAddresses();
		List<CoachAddress> userAddresss = new ArrayList<CoachAddress>();
		if(addresses != null) {
			for(CoachAddress address : addresses) {
				address.setCoachId(createdCoach.getId());
				address = coachManager.createAddress(address);
				userAddresss.add(address);
			}
		}
		createdCoach.setAddresses(userAddresss);
		logger.log(Level.INFO, TAG +" "+ "Signing up the Coach , email :" + coach.getEmail());
		EmailNotification emailNotification = new EmailNotification(null, createdCoach);
		emailNotification.sendLocalSignupMail();
		return createdCoach;
	}

	public List<User> getRecentlyRegisteredUsers(Integer days) {
		String startDate = getDateRange(days, null);
		String endDate = getDateRange(days, "currentDate");
		return userManager.getRecentlyRegisteredUsers(startDate, endDate);
	}
	
	private String getDateRange(Integer days, String type) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -days);
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = s.format(new Date(cal.getTimeInMillis()));
		Calendar currentCal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String endDate = sdf.format(currentCal.getTime());
		if("currentDate".equalsIgnoreCase(type))
			return endDate;
		else
			return startDate;
	}
	private String getCurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return df.format(date);
	}

	public List<ProgramSubscription> getRecentlySubscribedUsers(Integer days, Long coachId) {
		String startDate = getDateRange(days, null);
		List<ProgramSubscription> subscriptions = null;
		if(coachId == null)
			subscriptions = programManager.getRecentlySubscribedUsers(startDate);
		else {
			subscriptions = programManager.getRecentlySubscribedUsersByCoachId(startDate, coachId);
			if(subscriptions == null)
				subscriptions = programManager.getRecentlySubscribedUsersByDietitianId(startDate, coachId);
		}
		return subscriptions;
	}

	public List<User> getUnassignedCoachUsers(Long coachTypeId) {
		List<User> result = null;
		if(coachTypeId == null) {
			result = userManager.getAnyCoachUnassignedUsers();
		} else {
			CoachType coachType = coachManager.getCoachTypeById(coachTypeId);		
			if(coachType != null) {
				String type = coachType.getType();
				if(type != null) {
					if("Trainer".equalsIgnoreCase(type)) {
						result = userManager.getUnAssignedTrainerUsers();
					} else if("Dietitian".equalsIgnoreCase(type)) {
						result = userManager.getUnAssignedDietitanUsers();
					}
				}
			}
		}
		return result;
	}

	public List<User> getUsersUnderThsiTrainer(Long trainerId) {
		return findUsersUnderthisTrainer(trainerId);
	}

	public List<User> getUsersUnderThsiDietitian(Long dietitianId) {
		return findUsersUnderthisDietitian(dietitianId);
	}

	public User getUserByIdAndDietitianId(Long userId, Long id) {
		return userManager.getUserByIdAndDietitianId(userId, id);
	}

	public User getUserByIdAndYogaTrainerId(Long userId, Long id) {
		return userManager.getUserByIdAndYogaTrainerId(userId, id);
	}
}
