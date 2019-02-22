package com.supafit.controller.medicalcondition;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supafit.bo.medicalcondition.MedicalConditionService;
import com.supafit.common.parser.MedicalConditionParser;
import com.supafit.dao.medicalcondition.model.MedicalCondition;
import com.supafit.model.medicalcondition.MedicalConditionDTO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/medicalconditions")
@Api(value = "medicalconditions", description = "Operations pertaining to medical condition")
@CrossOrigin
public class MedicalConditionController {

	private MedicalConditionService medicalConditionService;
	private MedicalConditionParser mediConditionParser;

	@Autowired
	public void setMediConditionParser(
			MedicalConditionParser mediConditionParser) {
		this.mediConditionParser = mediConditionParser;
	}

	@Autowired
	public void setMedicalConditionService(
			MedicalConditionService medicalConditionService) {
		this.medicalConditionService = medicalConditionService;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get the list of Medical Conditions", notes = "Get the list of Medical Conditions", response = MedicalConditionDTO.class)
	public ResponseEntity<List<MedicalConditionDTO>> getMedicalConditions() {
		List<MedicalCondition> medConditionResponse = medicalConditionService
				.getMedicalConditions();
		List<MedicalConditionDTO> response = medConditionResponse
				.stream()
				.map(medCondition -> mediConditionParser
						.convertToDto(medCondition))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MedicalConditionDTO>>(response,
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Create Medical Conditions", notes = "Create Medical Conditions", response = MedicalConditionDTO.class)
	public ResponseEntity<List<MedicalConditionDTO>> createMedicalConditions(
			List<MedicalConditionDTO> medicalConditionDTO) {
		List<MedicalCondition> medConditionEntities = medicalConditionDTO
				.stream()
				.map(medCondition -> mediConditionParser
						.convertToEntity(medCondition))
				.collect(Collectors.toList());
		List<MedicalCondition> medConditionResponse = medicalConditionService
				.createMedicalConditions(medConditionEntities);
		List<MedicalConditionDTO> response = medConditionResponse
				.stream()
				.map(medCondition -> mediConditionParser
						.convertToDto(medCondition))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MedicalConditionDTO>>(response,
				HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ApiOperation(value = "Update Medical Conditions", notes = "Update Medical Conditions", response = MedicalConditionDTO.class)
	public ResponseEntity<List<MedicalConditionDTO>> updateMedicalConditions(
			List<MedicalConditionDTO> medicalConditionDTO) {
		List<MedicalCondition> medConditionEntities = medicalConditionDTO
				.stream()
				.map(medCondition -> mediConditionParser
						.convertToEntity(medCondition))
				.collect(Collectors.toList());
		List<MedicalCondition> medConditionResponse = medicalConditionService
				.updateMedicalConditions(medConditionEntities);
		List<MedicalConditionDTO> response = medConditionResponse
				.stream()
				.map(medCondition -> mediConditionParser
						.convertToDto(medCondition))
				.collect(Collectors.toList());
		return new ResponseEntity<List<MedicalConditionDTO>>(response,
				HttpStatus.CREATED);
	}

	@RequestMapping(value="/{medConditionId}",method = RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "Delete Medical Conditions", notes = "Delete Medical Conditions")
	public ResponseEntity<Void> deleteMedicalCondition(
			@PathVariable("medConditionId") Long medConditionId) {
		medicalConditionService.deleteMedicalCondition(medConditionId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
