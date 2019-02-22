package com.supafit.controller.summary;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supafit.bo.summary.SummaryService;
import com.supafit.common.parser.SummaryParser;
import com.supafit.dao.summary.model.ActivitySummary;
import com.supafit.model.summary.ActivitySummaryDTO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/summary")
@Api(value = "summary", description = "Operations pertaining to Summary of activity of a user for the day")
@CrossOrigin
public class ActivitySummaryController {

	private SummaryService summaryService;
	private SummaryParser summaryParser;

	@Autowired
	public void setSummaryParser(SummaryParser summaryParser) {
		this.summaryParser = summaryParser;
	}

	@Autowired
	public void setSummaryService(SummaryService summaryService) {
		this.summaryService = summaryService;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Create summary of user's activity and health data of the day", notes = "Create summary of user's activity and health data of the day", response = ActivitySummaryDTO.class)
	public ResponseEntity<ActivitySummaryDTO> createSummary(
			@RequestBody ActivitySummaryDTO summary) {

		ActivitySummary summaryEntity = summaryParser.convertToEntity(summary);
		ActivitySummary summaryResponse = summaryService
				.createSummary(summaryEntity);
		ActivitySummaryDTO response = summaryParser
				.convertToDto(summaryResponse);
		return new ResponseEntity<ActivitySummaryDTO>(response,
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get List of Summaries of Activity of a user", notes = "Get List of Summaries of Activity of a user", response = ActivitySummaryDTO.class)
	public ResponseEntity<List<ActivitySummaryDTO>> getUsersSummary(
			@PathVariable("userId") Long userId) {
		List<ActivitySummary> summaryResponse = summaryService
				.getUsersSummary(userId);
		List<ActivitySummaryDTO> response = summaryResponse.stream()
				.map(summary -> summaryParser.convertToDto(summary))
				.collect(Collectors.toList());
		return new ResponseEntity<List<ActivitySummaryDTO>>(response,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/bydate/users/{userId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get Summary of Activity of a user for given date, summary of the day is returned if the date is not passed", notes = "Get Summary of Activity of a user for given date, summary of the day is returned if the date is not passed", response = ActivitySummaryDTO.class)
	public ResponseEntity<ActivitySummaryDTO> getTodaysSummary(
			@PathVariable("userId") Long userId,
			@RequestParam(value = "date", required = false) String date) {
		ActivitySummary summaryResponse = summaryService.getTodaysSummary(
				userId, date);
		ActivitySummaryDTO response = summaryParser
				.convertToDto(summaryResponse);
		return new ResponseEntity<ActivitySummaryDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update summary of user's activity and health data of the day", notes = "Update summary of user's activity and health data of the day", response = ActivitySummaryDTO.class)
	public ResponseEntity<ActivitySummaryDTO> updateSummary(
			@RequestBody ActivitySummaryDTO summary) {
		ActivitySummary summaryEntity = summaryParser.convertToEntity(summary);
		ActivitySummary summaryResponse = summaryService
				.createSummary(summaryEntity);
		ActivitySummaryDTO response = summaryParser
				.convertToDto(summaryResponse);
		return new ResponseEntity<ActivitySummaryDTO>(response, HttpStatus.OK);
	}
}
