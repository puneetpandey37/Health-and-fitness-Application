package com.supafit.dao.questions;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.supafit.dao.questions.model.Answers;
import com.supafit.dao.questions.model.QuestionTypes;
import com.supafit.dao.questions.model.Questions;

@Repository("questionsManager")
@Transactional(propagation = Propagation.REQUIRED)
public class QuestionsManager {

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<QuestionTypes> getQuestionTypes() {
		TypedQuery<QuestionTypes> query = entityManager.createNamedQuery(
				"QuestionTypes.findAll", QuestionTypes.class);
		return query.getResultList();
	}

	public List<Questions> getQuestionsByType(Long typeId) {
		return entityManager
				.createNamedQuery("Questions.findByType", Questions.class)
				.setParameter("typeId", typeId).getResultList();
	}

	public List<Questions> getAllQuestions() {
		return entityManager
				.createNamedQuery("Questions.findAll", Questions.class)
				.getResultList();
	}

	public List<Answers> getAnswers(Long userId) {
		return entityManager
				.createNamedQuery("Answers.findByUserId", Answers.class)
				.setParameter("userId", userId)
				.getResultList();
	}

	public Answers createAnswers(Answers answer) {
		return entityManager.merge(answer);
	}

}
