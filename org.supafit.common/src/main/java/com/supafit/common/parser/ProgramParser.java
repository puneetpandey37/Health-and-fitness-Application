package com.supafit.common.parser;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.program.model.Program;
import com.supafit.dao.program.model.ProgramCategory;
import com.supafit.dao.program.model.ProgramSubscription;
import com.supafit.dao.program.model.ProgramType;
import com.supafit.model.program.ProgramCategoryDTO;
import com.supafit.model.program.ProgramDTO;
import com.supafit.model.program.ProgramSubscriptionDTO;
import com.supafit.model.program.ProgramTypeDTO;
@Component("programParser")
public class ProgramParser {

    public ModelMapper modelMapper;
    @Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	public ProgramCategoryDTO convertToDto(ProgramCategory plan) {
		ProgramCategoryDTO ProgramCategoryDTO = modelMapper.map(plan,
				ProgramCategoryDTO.class);
		return ProgramCategoryDTO;
	}
	public ProgramTypeDTO convertToDto(ProgramType program) {
		ProgramTypeDTO ProgramTypeDTO = modelMapper.map(program,
				ProgramTypeDTO.class);
		return ProgramTypeDTO;
	}
	public ProgramDTO convertToDto(Program program) {
		ProgramDTO ProgramDTO = modelMapper.map(program,
				ProgramDTO.class);
		return ProgramDTO;
	}
	public ProgramSubscriptionDTO convertToDto(
			ProgramSubscription subscription) {
		ProgramSubscriptionDTO programSubscriptionDTO = modelMapper.map(subscription,
				ProgramSubscriptionDTO.class);
		return programSubscriptionDTO;
	}
	
	public ProgramSubscription convertToEntity(ProgramSubscriptionDTO subscriptionDTO) {
		ProgramSubscription programSubscription = modelMapper.map(subscriptionDTO, ProgramSubscription.class);
	    return programSubscription;
	}
	
	
}
