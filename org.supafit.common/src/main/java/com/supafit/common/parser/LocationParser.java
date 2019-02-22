package com.supafit.common.parser;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.location.model.Address;
import com.supafit.dao.location.model.City;
import com.supafit.dao.location.model.Country;
import com.supafit.dao.location.model.Locality;
import com.supafit.dao.location.model.State;
import com.supafit.model.address.AddressDTO;
import com.supafit.model.address.CityDTO;
import com.supafit.model.address.CountryDTO;
import com.supafit.model.address.LocalityDTO;
import com.supafit.model.address.StateDTO;
@Component("locationParser")
public class LocationParser {

    public ModelMapper modelMapper;
    @Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	public CountryDTO convertToDto(Country country) {
		CountryDTO foodItemsDTO = modelMapper.map(country, CountryDTO.class);
		return foodItemsDTO;
	}
	public StateDTO convertToDto(State state) {
		StateDTO stateDTO = modelMapper.map(state, StateDTO.class);
		return stateDTO;
	}
	
	public CityDTO convertToDto(City city) {
		CityDTO cityDTO = modelMapper.map(city, CityDTO.class);
		return cityDTO;
	}
	
	public LocalityDTO convertToDto(Locality locality) {
		LocalityDTO localityDTO = modelMapper.map(locality, LocalityDTO.class);
		return localityDTO;
	}
	
	public AddressDTO convertToDto(Address address) {
		AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
		return addressDTO;
	}
}
