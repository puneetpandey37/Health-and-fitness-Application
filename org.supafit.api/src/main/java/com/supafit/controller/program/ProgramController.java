package com.supafit.controller.program;

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

import com.supafit.bo.program.ProgramService;
import com.supafit.common.exceptions.InvalidRequestException;
import com.supafit.common.parser.ProgramParser;
import com.supafit.dao.program.model.Program;
import com.supafit.dao.program.model.ProgramCategory;
import com.supafit.dao.program.model.ProgramSubscription;
import com.supafit.dao.program.model.ProgramType;
import com.supafit.model.program.ProgramCategoryDTO;
import com.supafit.model.program.ProgramDTO;
import com.supafit.model.program.ProgramSubscriptionDTO;
import com.supafit.model.program.ProgramTypeDTO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/programs")
@Api(value = "programs", description = "Operations pertaining to programs")
@CrossOrigin
public class ProgramController {

	private ProgramService programService;
	private ProgramParser programParser;

	@Autowired
	public void setProgramParser(ProgramParser programParser) {
		this.programParser = programParser;
	}

	@Autowired
	public void setProgramService(ProgramService programService) {
		this.programService = programService;
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get categories of programs", notes = "Get categories of programs")
	public ResponseEntity<List<ProgramCategoryDTO>> getProgramCategories() {
		List<ProgramCategory> programCategoryResponse = programService
				.getProgramCategories();
		List<ProgramCategoryDTO> response = programCategoryResponse.stream()
				.map(plan -> programParser.convertToDto(plan))
				.collect(Collectors.toList());
		return new ResponseEntity<List<ProgramCategoryDTO>>(response,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/types", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get types of programs", notes = "Get types of programs")
	public ResponseEntity<List<ProgramTypeDTO>> getProgramTypes() {
		List<ProgramType> programTypeResponse = programService
				.getProgramTypes();
		List<ProgramTypeDTO> response = programTypeResponse.stream()
				.map(program -> programParser.convertToDto(program))
				.collect(Collectors.toList());
		return new ResponseEntity<List<ProgramTypeDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value="/types/{typeId}/categories/{categoryId}",method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get programs", notes = "Get programs")
	public ResponseEntity<List<ProgramDTO>> getPrograms(
			@PathVariable(value = "typeId") Long typeId,
			@PathVariable(value = "categoryId") Long categoryId) {
		List<Program> programResponse = programService.getPrograms(typeId,
				categoryId);
		List<ProgramDTO> response = programResponse.stream()
				.map(program -> programParser.convertToDto(program))
				.collect(Collectors.toList());
		return new ResponseEntity<List<ProgramDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/subscibe", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Subscribe to a programs", notes = "Subscribe to a programs")
	public ResponseEntity<ProgramSubscriptionDTO> subscribe(
			@RequestBody ProgramSubscriptionDTO programSubscriptionDTO) {

		ProgramSubscription subscription = programParser
				.convertToEntity(programSubscriptionDTO);

		ProgramSubscription programSubscriptionResponse = programService
				.subscribe(subscription);

		ProgramSubscriptionDTO subscriptionDTO = programParser
				.convertToDto(programSubscriptionResponse);

		return new ResponseEntity<ProgramSubscriptionDTO>(subscriptionDTO,
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/subscibe", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Assign coach and dietitian to the user ", notes = "Assign coach and dietitian to the user")
	public ResponseEntity<ProgramSubscriptionDTO> updateSubscribtion(
			@RequestBody ProgramSubscriptionDTO programSubscriptionDTO)
			throws InvalidRequestException {
		ProgramSubscription subscription = programParser
				.convertToEntity(programSubscriptionDTO);

		ProgramSubscription programSubscriptionResponse = programService
				.updateSubscribtion(subscription);

		ProgramSubscriptionDTO subscriptionDTO = programParser
				.convertToDto(programSubscriptionResponse);

		return new ResponseEntity<ProgramSubscriptionDTO>(subscriptionDTO,
				HttpStatus.CREATED);
	}
}
