package com.supafit.model.question;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("questions")
public class QuestionsDTO {
	private long id;
	private long questionTypeId;
	private String question;
	private AnswerTypesDTO type;
	private List<AnswerOptionsDTO> options;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getQuestionTypeId() {
		return questionTypeId;
	}
	public void setQuestionTypeId(long questionTypeId) {
		this.questionTypeId = questionTypeId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public AnswerTypesDTO getType() {
		return type;
	}
	public void setType(AnswerTypesDTO type) {
		this.type = type;
	}
	public List<AnswerOptionsDTO> getOptions() {
		return options;
	}
	public void setOptions(List<AnswerOptionsDTO> options) {
		this.options = options;
	}
}
