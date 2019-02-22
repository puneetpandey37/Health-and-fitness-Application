package com.supafit.common.parser;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.summary.model.ActivitySummary;
import com.supafit.model.summary.ActivitySummaryDTO;
@Component("summaryParser")
public class SummaryParser {

	
    public ModelMapper modelMapper;
    @Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public ActivitySummary convertToEntity(ActivitySummaryDTO coachDTO) {
		ActivitySummary result = modelMapper.map(coachDTO, ActivitySummary.class);
	    return result;
	}
	
	public ActivitySummaryDTO convertToDto(ActivitySummary summary) {
		ActivitySummaryDTO result = modelMapper.map(summary, ActivitySummaryDTO.class);
	    return result;
	}
}
