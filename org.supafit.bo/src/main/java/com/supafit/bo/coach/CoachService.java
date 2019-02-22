package com.supafit.bo.coach;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.coach.CoachManager;
import com.supafit.dao.coach.model.Coach;
import com.supafit.dao.coach.model.CoachAddress;
import com.supafit.dao.coach.model.CoachMessages;
import com.supafit.dao.coach.model.CoachPhoneNumber;
import com.supafit.dao.coach.model.CoachType;
import com.supafit.dao.user.UserManager;
import com.supafit.dao.user.model.User;

@Component
public class CoachService {

	private CoachManager coachManager;
	private UserManager userManager;

	@Autowired
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	@Autowired
	public void setCoachManager(CoachManager coachManager) {
		this.coachManager = coachManager;
	}
	
	public Coach createCoach(Coach coach) {
		return coachManager.createCoach(coach);
	}
	
	public List<Coach> getAllCoaches() {
		List<Coach> coaches = coachManager.getAllCoaches();
		List<Coach> result = new ArrayList<Coach>();
		if(coaches != null) {
			for(Coach coach : coaches) {
				Long coachId = coach.getId();
				List<CoachAddress> addresses = coachManager.getAddressesByCoachId(coachId);
				List<CoachPhoneNumber> phoneNumbers = coachManager.getPhoneNumbersByCoachId(coachId);
				coach.setPhoneNumbers(phoneNumbers);
				coach.setAddresses(addresses);
				List<CoachTypeAndUsersDTO> coachTypeAndUsers = getAllUsersUnderThisCoach(coachId);
				List<User> users = new ArrayList<User>();
				if(coachTypeAndUsers != null)
					for(CoachTypeAndUsersDTO coachTypeAndUser : coachTypeAndUsers) {
						List<User> userResult = coachTypeAndUser.getUsers();
						if(userResult != null)
							users.addAll(userResult);
					}
				int numberOfUsers = 0;
				if(users != null) {
					coach.setUsers(users);
					numberOfUsers = users.size();
				}
				coach.setNumberOfUsers(numberOfUsers);
				result.add(coach);
			}
		}
		return result;
	}
	
	public List<Coach> getCoachesByType(Long coachTypeId) {
		List<Coach> coaches = coachManager.getCoachesByType(coachTypeId);
		List<Coach> result = new ArrayList<Coach>();
		if(coaches != null) {
			for(Coach coach : coaches) {
				Long coachId = coach.getId();
				List<CoachAddress> addresses = coachManager.getAddressesByCoachId(coachId);
				List<CoachPhoneNumber> phoneNumbers = coachManager.getPhoneNumbersByCoachId(coachId);
				coach.setPhoneNumbers(phoneNumbers);
				coach.setAddresses(addresses);
				List<CoachTypeAndUsersDTO> coachTypeAndUsers = getAllUsersUnderThisCoach(coachId);
				List<User> users = new ArrayList<User>();
				if(coachTypeAndUsers != null)
					for(CoachTypeAndUsersDTO coachTypeAndUser : coachTypeAndUsers) {
						List<User> userResult = coachTypeAndUser.getUsers();
						if(userResult != null)
							users.addAll(userResult);
					}
				int numberOfUsers = 0;
				if(users != null) {
					coach.setUsers(users);
					numberOfUsers = users.size();
				}
				coach.setNumberOfUsers(numberOfUsers);
				result.add(coach);
			}
		}
		return result;
	}
	
	public Coach getCoachesById(Long coachId) {
		Coach coach =  coachManager.getCoachesById(coachId);
		if(coach != null) {
			List<CoachAddress> addresses = coachManager.getAddressesByCoachId(coachId);
			List<CoachPhoneNumber> phoneNumbers = coachManager.getPhoneNumbersByCoachId(coachId);
			coach.setPhoneNumbers(phoneNumbers);
			coach.setAddresses(addresses);
			List<CoachTypeAndUsersDTO> coachTypeAndUsers = getAllUsersUnderThisCoach(coachId);
			List<User> users = new ArrayList<User>();
			if(coachTypeAndUsers != null)
				for(CoachTypeAndUsersDTO coachTypeAndUser : coachTypeAndUsers) {
					List<User> userResult = coachTypeAndUser.getUsers();
					if(userResult != null)
						users.addAll(userResult);
				}
			int numberOfUsers = 0;
			if(users != null) {
				coach.setUsers(users);
				numberOfUsers = users.size();
			}
			coach.setNumberOfUsers(numberOfUsers);
		}
		return coach;
	}
	
	public List<CoachType> getCoachTypes() {
		return coachManager.getCoachTypes();
	}
	
	public CoachType getCoachTypeById(Long id) {
		return coachManager.getCoachTypeById(id);
	}

	public Coach updateCoachDetail(Coach coach) {
		return createCoach(coach);
	}

	public List<CoachTypeAndUsersDTO> getAllUsersUnderThisCoach(Long coachId) {
		Coach coach =  coachManager.getCoachesById(coachId);
		List<CoachTypeAndUsersDTO> result = new ArrayList<CoachTypeAndUsersDTO>();
		if(coach != null) {
//			List<CoachType> coachTypes = coach.getCoachTypes();
//			if(coachTypes != null) {
//				for(CoachType coachType : coachTypes) {
//					CoachTypeAndUsersDTO coachTypeAndUsers = new CoachTypeAndUsersDTO();
//						String type = coachType.getType();
//						List<User> users = null;
//						if("Trainer".equalsIgnoreCase(type)) {
//							users = userManager.findUsersUnderthisTrainer(coachId);
//						} else if("Dietitian".equalsIgnoreCase(type)) {
//							users = userManager.findUsersUnderDietition(coachId);
//						}
//						coachTypeAndUsers.setType(type);
//						coachTypeAndUsers.setUsers(users);
//						result.add(coachTypeAndUsers);
//				}
//			}
		}
		return result;
	}
	
	public List<User> getUsersUnderThisCoach(Long coachId, Integer days) {
		boolean isDayPassed = false;
		String startDate = null;
		String endDate = null;
		boolean isAdmin = false;
		if(days != null) {
			startDate = getLastNthDate(days);
			endDate = getCurrentDate();
			isDayPassed = true;
		}
		Coach coach =  coachManager.getCoachesById(coachId);
		List<User> result = new ArrayList<User>();
		if(coach != null) {
//			List<CoachType> coachTypes = coach.getCoachTypes();
//			if(coachTypes != null) {
//				for(CoachType coachType : coachTypes) {
//						String type = coachType.getType();
//						List<User> users = null;
//						if("Trainer".equalsIgnoreCase(type)) {
//							if(isDayPassed)
//								users = userManager.findUsersUnderthisTrainerByDate(coachId, startDate);
//							else
//								users = userManager.findUsersUnderthisTrainer(coachId);
//						} else if("Dietitian".equalsIgnoreCase(type)) {
//							if(isDayPassed)
//								users = userManager.findUsersUnderDietitionByDate(coachId, startDate);
//							else
//								users = userManager.findUsersUnderDietition(coachId);
//						} else if("Yoga Trainer".equalsIgnoreCase(type)) {
//							if(isDayPassed)
//								users = userManager.findUsersUnderYogaTrainerByDate(coachId, startDate);
//							else
//								users = userManager.findUsersUnderYogaTrainer(coachId);
//						} else if("Admin".equalsIgnoreCase(type)) {
//							if(isDayPassed)
//								users = userManager.getAllUsers();
//							else
//								users = userManager.getRecentlyRegisteredUsers(startDate, endDate);
//							
//							isAdmin = true;
//						}
//						if(users != null)
//							result.addAll(users);
//				}
//			}
		}
		return result;
	}
	
	public String getLastNthDate(Integer days) {
		String output = null;
		if(days != null) {
			Calendar cal = Calendar.getInstance();
			days = days - (days*2);
			cal.add(Calendar.DATE, days);
			SimpleDateFormat df = new SimpleDateFormat( "YYYY-MM-dd" );
			output = df.format(cal.getTime());
		}
		return output;
	}
	
	public String getCurrentDate() {
		String output = null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
		output = df.format(cal.getTime());
		return output;
	}

	public CoachMessages createCoachMessages(CoachMessages coachMessagesRequest) {
		String currentDateTime = getCurrentDateTime();
		coachMessagesRequest.setDateCreated(currentDateTime);
		coachMessagesRequest.setDateUpdated(currentDateTime);
		return coachManager.createCoachMessages(coachMessagesRequest);
	}

	public List<CoachMessages> getCoachMessages(Long userId) {
		List<CoachMessages> result = new ArrayList<CoachMessages>();
		List<CoachMessages> messages = coachManager.getCoachMessagesByUser(userId);
		if(messages != null)
			for(CoachMessages message : messages) {
				Long coachId = message.getCoachId();
				Coach coach = coachManager.getCoachesById(coachId);
				message.setCoachName(coach.getName());
				message.setImageURL(coach.getImageURL());
				result.add(message);
			}
		return result;
	}

	public CoachMessages updateCoachMessages(CoachMessages coachMessagesRequest) {
		String currentDateTime = getCurrentDateTime();
		coachMessagesRequest.setDateUpdated(currentDateTime);
		return  coachManager.createCoachMessages(coachMessagesRequest);
	}

	public String deleteCoachMessages(Long messageId) {
		String result = null;
		Long deleteFlag = coachManager.deleteCoachMessages(messageId);
		if(deleteFlag != null)
			result = "success";
		else
			result = "Fail";
		return result;
	}
	
	private String getCurrentDateTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		return df.format(date);
	}
}
