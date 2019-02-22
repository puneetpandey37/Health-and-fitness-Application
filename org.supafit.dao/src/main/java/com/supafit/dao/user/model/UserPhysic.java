package com.supafit.dao.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="user_physic")
@NamedQueries({
    @NamedQuery(name = "UserPhysic.findByUserId", query = "SELECT u FROM UserPhysic u where u.userId = :userId")})
@ApiModel("UserPhysic")
public class UserPhysic {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="user_id")
	private long userId;
	@Column(name="age")
	private int age;
	@Column(name="weight")
	private String weight;
	@Column(name="height")
	private String height;
	@Column(name="bmi")
	private String bmi;
	@Column(name="bmr")
	private String bmr;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getBmi() {
		return bmi;
	}
	public void setBmi(String bmi) {
		this.bmi = bmi;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBmr() {
		return bmr;
	}
	public void setBmr(String bmr) {
		this.bmr = bmr;
	}
}
