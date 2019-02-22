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
@Table(name="answer_options")
@NamedQueries({
	@NamedQuery(name = "AnswerOptions.findAll", query = "select q from AnswerOptions q")
})
@ApiModel("AnswerOptions")
public class AnswerOptions {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="question_id")
	private String questionId;
	@Column(name="answer_option")
	private String answerOption;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getAnswerOption() {
		return answerOption;
	}
	public void setAnswerOption(String answerOption) {
		this.answerOption = answerOption;
	}
}
