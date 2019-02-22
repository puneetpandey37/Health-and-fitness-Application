package com.supafit.dao.coach.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="coach_address")
@NamedQueries({
	@NamedQuery(name = "CoachAddress.findAll", query = "SELECT a FROM CoachAddress a"),
	@NamedQuery(name = "CoachAddress.findByCoachId", query = "SELECT a FROM CoachAddress a WHERE a.coachId = :coachId"), 
	@NamedQuery(name = "CoachAddress.findById", query = "SELECT a FROM CoachAddress a WHERE a.id = :id")
	})
@ApiModel("CoachAddress")
public class CoachAddress {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="coach_id")
	private Long coachId;
	@Column(name="location_id")
	private long locationId;
	@Column(name="address")
	private String address;
	@Column(name="landmark")
	private String landmark;
	@Column(name="phone")
	private String phoneNumber;
	@Column(name="address_type")
	private String addressType;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getCoachId() {
		return coachId;
	}
	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}
	public long getLocationId() {
		return locationId;
	}
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
}
