package com.supafit.common.parser;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.questions.model.Answers;
import com.supafit.dao.questions.model.QuestionTypes;
import com.supafit.dao.questions.model.Questions;
import com.supafit.model.question.AnswersDTO;
import com.supafit.model.question.QuestionTypesDTO;
import com.supafit.model.question.QuestionsDTO;
@Component("questionParser")
public class QuestionParser {

    public ModelMapper modelMapper;
	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public QuestionTypes convertToEntity(QuestionTypesDTO questionTypeDTO) {
		QuestionTypes questionType = modelMapper.map(questionTypeDTO, QuestionTypes.class);
		return questionType;
	}
	
	public Questions convertToEntity(QuestionsDTO questionDTO) {
		Questions question = modelMapper.map(questionDTO, Questions.class);
		return question;
	}
	
	public Answers convertToEntity(AnswersDTO answersDTO) {
		Answers answers = modelMapper.map(answersDTO, Answers.class);
		return answers;
	}
	
	public QuestionTypesDTO convertToDto(QuestionTypes questionType) {
		QuestionTypesDTO questionTypeDTO = modelMapper.map(questionType, QuestionTypesDTO.class);
		return questionTypeDTO;
	}
	
	public QuestionsDTO convertToDto(Questions question) {
		QuestionsDTO questionsDTO = modelMapper.map(question, QuestionsDTO.class);
		return questionsDTO;
	}

	public AnswersDTO convertToDto(Answers answer) {
		AnswersDTO answersDTO = modelMapper.map(answer, AnswersDTO.class);
		return answersDTO;
	}
	
}
