package com.supafit.dao.location.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="user_address")
@NamedQueries({
	@NamedQuery(name = "Address.findByUserId", query = "SELECT a FROM Address a where a.userId = :userId") 
	})
@ApiModel("Addresses")
public class Address {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="user_id")
	private Long userId;
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
	public long getLocationId() {
		return locationId;
	}
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
}
