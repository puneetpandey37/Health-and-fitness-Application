package com.supafit.bo.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supafit.dao.location.LocationManager;
import com.supafit.dao.location.model.City;
import com.supafit.dao.location.model.Country;
import com.supafit.dao.location.model.Locality;
import com.supafit.dao.location.model.State;
@Component
public class LocationService {
	
	@Autowired
	private LocationManager locationManager;
	public void setLocationManager(LocationManager locationManager) {
		this.locationManager = locationManager;
	}
	public List<Country> getCountries() {
		return locationManager.getCountries();
	}
	public List<State> getStates(Long countryId) {
		return locationManager.getStates(countryId);
	}
	public List<City> getCities(Long stateId) {
		return locationManager.getCities(stateId);
	}
	public List<Locality> getLocalities(Long cityId) {
		return locationManager.getLocalities(cityId);
	}

}
