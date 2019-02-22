package com.supafit.common.parser;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.coach.model.Coach;
import com.supafit.dao.coach.model.CoachMessages;
import com.supafit.dao.coach.model.CoachType;
import com.supafit.model.coach.CoachDTO;
import com.supafit.model.coach.CoachMessagesDTO;
import com.supafit.model.coach.CoachSignupRequestDTO;
import com.supafit.model.coach.CoachSignupResponseDTO;
import com.supafit.model.coach.CoachTypeDTO;
@Component("coachParser")
public class CoachParser {

    public ModelMapper modelMapper;
    @Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public Coach convertToEntity(CoachDTO coachDTO) {
		Coach coach = modelMapper.map(coachDTO, Coach.class);
	    return coach;
	}
	
	public CoachMessages convertToEntity(CoachMessagesDTO coachMessagesRequest) {
		CoachMessages coachMessages = modelMapper.map(coachMessagesRequest, CoachMessages.class);
	    return coachMessages;
	}
	
	public Coach convertToEntity(CoachSignupRequestDTO coachDTO) {
		Coach coach = modelMapper.map(coachDTO, Coach.class);
	    return coach;
	}
	
	public CoachDTO convertToDto(Coach coach) {
		CoachDTO coachDto = modelMapper.map(coach, CoachDTO.class);
	    return coachDto;
	}
	
	public CoachTypeDTO convertToDto(CoachType coachTypes) {
		CoachTypeDTO coachTypeDTO = modelMapper.map(coachTypes, CoachTypeDTO.class);
	    return coachTypeDTO;
	}
	
	public CoachMessagesDTO convertToDto(CoachMessages message) {
		CoachMessagesDTO messageDTO = modelMapper.map(message, CoachMessagesDTO.class);
	    return messageDTO;
	}

	public CoachSignupResponseDTO convertCoachSignupResponseToDto(Coach coachResponse) {
		CoachSignupResponseDTO coachDto = modelMapper.map(coachResponse, CoachSignupResponseDTO.class);
	    return coachDto;
	}
}
