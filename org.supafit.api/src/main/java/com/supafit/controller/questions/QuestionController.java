package com.supafit.controller.questions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supafit.bo.questions.QuestionsService;
import com.supafit.common.exceptions.InvalidRequestException;
import com.supafit.common.parser.QuestionParser;
import com.supafit.dao.questions.model.Answers;
import com.supafit.dao.questions.model.QuestionTypes;
import com.supafit.dao.questions.model.Questions;
import com.supafit.model.question.AnswersDTO;
import com.supafit.model.question.QuestionTypesDTO;
import com.supafit.model.question.QuestionsDTO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/questions")
@Api(value = "questions", description = "Operations pertaining to questions")
@CrossOrigin
public class QuestionController {

	private QuestionsService questionsService;
	private QuestionParser questionParser;

	@Autowired
	public void setQuestionParser(QuestionParser questionParser) {
		this.questionParser = questionParser;
	}

	@Autowired
	public void setQuestionsService(QuestionsService questionsService) {
		this.questionsService = questionsService;
	}

	@RequestMapping(value = "/types", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get types of questions", notes = "Get types of questions")
	public ResponseEntity<List<QuestionTypesDTO>> getQuestionTypes() {
		List<QuestionTypes> questionTypesResponse = questionsService
				.getQuestionTypes();
		List<QuestionTypesDTO> response = questionTypesResponse.stream()
				.map(questionType -> questionParser.convertToDto(questionType))
				.collect(Collectors.toList());
		return new ResponseEntity<List<QuestionTypesDTO>>(response,
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get questions", notes = "Get questions")
	public ResponseEntity<List<QuestionsDTO>> getQuestions() {
		List<Questions> questionTypesResponse = questionsService
				.getQuestions();
		List<QuestionsDTO> response = questionTypesResponse.stream()
				.map(question -> questionParser.convertToDto(question))
				.collect(Collectors.toList());
		return new ResponseEntity<List<QuestionsDTO>>(response, HttpStatus.OK);
	}

	
	@RequestMapping(value="/types/{typeId}",method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get questions", notes = "Get questions")
	public ResponseEntity<List<QuestionsDTO>> getQuestionsByTypes(
			@PathVariable(value = "typeId") Long typeId) {
		List<Questions> questionTypesResponse = questionsService
				.getQuestionsByType(typeId);
		List<QuestionsDTO> response = questionTypesResponse.stream()
				.map(question -> questionParser.convertToDto(question))
				.collect(Collectors.toList());
		return new ResponseEntity<List<QuestionsDTO>>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/answers/{userId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get answers of helth queries", notes = "Get answers of helth queries", response = AnswersDTO.class, responseContainer = "List")
	public ResponseEntity<List<AnswersDTO>> getAnswers(
			@PathVariable(value = "userId") Long userId)
			throws InvalidRequestException {
		List<Answers> answersResponse = questionsService.getAnswers(userId);
		List<AnswersDTO> response = answersResponse.stream()
				.map(answer -> questionParser.convertToDto(answer))
				.collect(Collectors.toList());
		return new ResponseEntity<List<AnswersDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/answers", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Create answers of helth queries(This takes List of Answers as Input)", notes = "Create answers of helth queries(This takes List of Answers as Input)", response = AnswersDTO.class, responseContainer = "List")
	public ResponseEntity<List<AnswersDTO>> createAnswers(
			@RequestBody List<AnswersDTO> answersDTOs)
			throws InvalidRequestException {
		List<Answers> answersEntities = answersDTOs.stream()
				.map(answer -> questionParser.convertToEntity(answer))
				.collect(Collectors.toList());
		List<Answers> answersResponse = questionsService
				.createAnswers(answersEntities);
		List<AnswersDTO> response = answersResponse.stream()
				.map(answer -> questionParser.convertToDto(answer))
				.collect(Collectors.toList());
		return new ResponseEntity<List<AnswersDTO>>(response,
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/answers", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update answers of helth queries", notes = "Update answers of helth queries", response = AnswersDTO.class, responseContainer = "List")
	public ResponseEntity<List<AnswersDTO>> updateAnswers(
			@RequestBody List<AnswersDTO> answersDTOs)
			throws InvalidRequestException {
		List<Answers> answersEntities = answersDTOs.stream()
				.map(answer -> questionParser.convertToEntity(answer))
				.collect(Collectors.toList());
		List<Answers> answersResponse = questionsService
				.updateAnswers(answersEntities);
		List<AnswersDTO> response = answersResponse.stream()
				.map(answer -> questionParser.convertToDto(answer))
				.collect(Collectors.toList());
		return new ResponseEntity<List<AnswersDTO>>(response, HttpStatus.OK);
	}
}
