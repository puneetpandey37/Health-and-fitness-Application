package com.supafit.dao.questions.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="question")
@NamedQueries({
	@NamedQuery(name = "Questions.findAll", query = "select q from Questions q"),
	@NamedQuery(name = "Questions.findByType", query = "select q from Questions q where q.questionTypeId = :typeId")
})
@ApiModel("questions")
public class Questions {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="question_type_id")
	private long questionTypeId;
	@Column(name = "question")
	private String question;
	@OneToOne
	@JoinColumn(name="answer_type_id")
	private AnswerTypes type;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "question_id", referencedColumnName = "id")
	private List<AnswerOptions> options;
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
	public AnswerTypes getType() {
		return type;
	}
	public void setType(AnswerTypes type) {
		this.type = type;
	}
	public List<AnswerOptions> getOptions() {
		return options;
	}
	public void setOptions(List<AnswerOptions> options) {
		this.options = options;
	}
}
