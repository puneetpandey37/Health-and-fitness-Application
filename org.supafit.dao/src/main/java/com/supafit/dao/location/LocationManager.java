package com.supafit.dao.location;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.supafit.dao.location.model.City;
import com.supafit.dao.location.model.Country;
import com.supafit.dao.location.model.Locality;
import com.supafit.dao.location.model.State;

@Repository("locationManager")
@Transactional(propagation = Propagation.REQUIRED)
public class LocationManager {

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Country> getCountries() {
		return entityManager
	            .createNamedQuery("Country.findAll", Country.class).getResultList();
	}

	public List<State> getStates(Long countryId) {
		return entityManager
	            .createNamedQuery("State.findByCountry", State.class).setParameter("countryId", countryId).getResultList();
	}

	public List<City> getCities(Long stateId) {
		return entityManager
	            .createNamedQuery("City.findByState", City.class).setParameter("stateId", stateId).getResultList();
	}

	public List<Locality> getLocalities(Long cityId) {
		return entityManager
	            .createNamedQuery("Locality.findByCity", Locality.class).setParameter("cityId", cityId).getResultList();
	}
}
