package com.supafit.bo.questions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.bo.coach.CoachService;
import com.supafit.bo.user.UserService;
import com.supafit.common.exceptions.InvalidRequestException;
import com.supafit.common.security.LoggedInUserUtil;
import com.supafit.dao.coach.model.Coach;
import com.supafit.dao.coach.model.CoachType;
import com.supafit.dao.questions.QuestionsManager;
import com.supafit.dao.questions.model.Answers;
import com.supafit.dao.questions.model.QuestionTypes;
import com.supafit.dao.questions.model.Questions;
import com.supafit.dao.user.model.User;

@Component
public class QuestionsService {

	private QuestionsManager questionsManager;
	private LoggedInUserUtil loggedInUserUtil;
	private UserService userService;
	private CoachService coachService;
	@Autowired
	public void setCoachService(CoachService coachService) {
		this.coachService = coachService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setLoggedInUserUtil(LoggedInUserUtil loggedInUserUtil) {
		this.loggedInUserUtil = loggedInUserUtil;
	}
	@Autowired
	public void setQuestionsManager(QuestionsManager questionsManager) {
		this.questionsManager = questionsManager;
	}
	public List<QuestionTypes> getQuestionTypes() {
		return questionsManager.getQuestionTypes();
	}
	public List<Questions> getQuestions() {
		List<Questions> result = null;
			result = questionsManager.getAllQuestions();
		return result;
	}
	
	public List<Questions> getQuestionsByType(Long typeId) {
		List<Questions> result = null;
			result = questionsManager.getQuestionsByType(typeId);
		return result;
	}
	
	public List<Answers> getAnswers(Long userId) throws InvalidRequestException {
		
//		User loggedInUser = loggedInUserUtil.getLoggedInUser();
		/*if(loggedInUser == null)
			throw new InvalidRequestException(
					"User not logged in");
		Long loggedInUserId = loggedInUser.getId();*/
		List<Answers> result = null;
		/*if(userId == null) {
			result = questionsManager.getAnswers(loggedInUserId);
		} else {
			User inputUser = userService.getUserDetail(userId);
			Long coachId = inputUser.getCoachId();
			if(coachId != null) {
				if(coachId == userId) {*/
					result = questionsManager.getAnswers(userId);
				/*} else {
					throw new InvalidRequestException(
							"Can not get other's answers.");
				}
			} else {
				throw new InvalidRequestException(
						"Not a coach!");
			}
					
		}*/
		return result;
	}
	public List<Answers> createAnswers(List<Answers> answers) throws InvalidRequestException {
		List<Answers> result = new ArrayList<Answers>();
//		User loggedInUser = loggedInUserUtil.getLoggedInUser();
//		Long loggedInUserId = loggedInUser.getId();
//		Long userTypeId = loggedInUser.getUserTypeId();
		String type = "coach";
		Long userId = answers.get(0).getUserId();
		/*if(userTypeId == 1) {
			id = loggedInUserId;
			type = "user";
		} else if (userTypeId == 2) {
			id = loggedInUserId;
			type = "coach";
		}*/
		for(Answers answer : answers) {
			if("coach".equalsIgnoreCase(type)) {
				Long id = answer.getCoachId();
				answer.setCoachId(id);
				User userByUserAndCoachId = userService.getUserDetail(answer.getUserId(), id);
				User userByUserAndDietitianId = userService.getUserByIdAndDietitianId(answer.getUserId(), id);
				User userByUserAndYogaTrainerId = userService.getUserByIdAndYogaTrainerId(answer.getUserId(), id);
				Coach coach = coachService.getCoachesById(answer.getCoachId());
				boolean isAdmin = false;
//				if(coach != null) {
//						List<CoachType> coachTypes = coach.getCoachTypes();
//						if(coachTypes != null)
//							for(CoachType cType : coachTypes) {
//								if(cType.getType().equalsIgnoreCase("Admin"))
//									isAdmin = true;
//									break;
//							}
//				}
				if((userByUserAndCoachId == null && userByUserAndDietitianId == null && userByUserAndYogaTrainerId == null) && isAdmin == false)
					throw new InvalidRequestException(
							"Unauthorized - Not your user!");
			} else if("user".equalsIgnoreCase(type)) {
				answer.setUserId(userId);
			}
			answer = questionsManager.createAnswers(answer);
			result.add(answer);
		}
		return result;
	}
	public List<Answers> updateAnswers(List<Answers> answers) throws InvalidRequestException {
		return createAnswers(answers);
	}
}
