package com.supafit.common.parser;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.medicalcondition.model.MedicalCondition;
import com.supafit.model.medicalcondition.MedicalConditionDTO;
@Component("mediConditionParser")
public class MedicalConditionParser {

    public ModelMapper modelMapper;
    @Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	public MedicalConditionDTO convertToDto(MedicalCondition medCondition) {
		MedicalConditionDTO medConditionDTO = modelMapper.map(medCondition, MedicalConditionDTO.class);
		return medConditionDTO;
	}
	public MedicalCondition convertToEntity(MedicalConditionDTO medConditionDTO) {
		MedicalCondition medCondition = modelMapper.map(medConditionDTO, MedicalCondition.class);
	    return medCondition;
	}
	
	
}
