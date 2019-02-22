package com.supafit.controller.location;

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

import com.supafit.bo.location.LocationService;
import com.supafit.common.parser.LocationParser;
import com.supafit.dao.location.model.City;
import com.supafit.dao.location.model.Country;
import com.supafit.dao.location.model.Locality;
import com.supafit.dao.location.model.State;
import com.supafit.model.address.CityDTO;
import com.supafit.model.address.CountryDTO;
import com.supafit.model.address.LocalityDTO;
import com.supafit.model.address.StateDTO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/locations")
@Api(value = "locations", description = "Operations pertaining to locations")
@CrossOrigin
public class LocationController {

	private LocationService locationService;
	private LocationParser locationParser;

	@Autowired
	public void setLocationParser(LocationParser locationParser) {
		this.locationParser = locationParser;
	}

	@Autowired
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	@RequestMapping(value = "/countries", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get the List of Countries", notes = "This list contains all the countries in the world")
	public ResponseEntity<List<CountryDTO>> getCountries() {
		List<Country> countryResponse = locationService.getCountries();
		List<CountryDTO> response = countryResponse.stream()
				.map(country -> locationParser.convertToDto(country))
				.collect(Collectors.toList());
		return new ResponseEntity<List<CountryDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/countries/{countryId}/states", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get the List of States of a country", notes = "All states and union territories of a given country")
	public ResponseEntity<List<StateDTO>> getStates(
			@ApiParam(name = "country_id", value = "The Id of the Country to get States from", required = true) @PathVariable("countryId") Long countryId) {
		List<State> stateResponse = locationService.getStates(countryId);
		List<StateDTO> response = stateResponse.stream()
				.map(state -> locationParser.convertToDto(state))
				.collect(Collectors.toList());
		return new ResponseEntity<List<StateDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/states/{stateId}/cities", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get the List of Cities of a state", notes = "All cities of a given state")
	public ResponseEntity<List<CityDTO>> getCities(
			@ApiParam(name = "state_id", value = "The Id of the state to get cities from", required = true) @PathVariable("stateId") Long stateId) {
		List<City> cityResponse = locationService.getCities(stateId);
		List<CityDTO> response = cityResponse.stream()
				.map(city -> locationParser.convertToDto(city))
				.collect(Collectors.toList());
		return new ResponseEntity<List<CityDTO>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/cities/{cityId}/localities", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Get the List of localities of a city", notes = "All localities of a given city")
	public ResponseEntity<List<LocalityDTO>> getLocalities(
			@ApiParam(name = "city_id", value = "The Id of the city to get localities from", required = true) @PathVariable("cityId") Long cityId) {
		List<Locality> localityResponse = locationService.getLocalities(cityId);
		List<LocalityDTO> response = localityResponse.stream()
				.map(locality -> locationParser.convertToDto(locality))
				.collect(Collectors.toList());
		return new ResponseEntity<List<LocalityDTO>>(response, HttpStatus.OK);
	}
}
