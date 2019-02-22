package com.supafit.model.question;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("AnswerOptions")
public class AnswerOptionsDTO {

	private long id;
	private String questionId;
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
