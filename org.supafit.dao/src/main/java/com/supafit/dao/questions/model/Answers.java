package com.supafit.dao.questions.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="user_answer")
@NamedQueries({
    @NamedQuery(name = "Answers.findByUserId", query = "SELECT a FROM Answers a where a.userId = :userId"),
    @NamedQuery(name = "Answers.findByUserAndCoachId", query = "SELECT a FROM Answers a where a.userId = :userId and a.coachId = :coachId"),
    @NamedQuery(name = "Answers.findAll", query= "SELECT a FROM Answers a")
    })
@ApiModel("Answers")
public class Answers {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private Long id;
	@Column(name="user_id")
	private Long userId;
	@Column(name="coach_id")
	private Long coachId;
	@Column(name="question")
	private String question;
	@Column(name="answer")
	private String answer;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCoachId() {
		return coachId;
	}
	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
