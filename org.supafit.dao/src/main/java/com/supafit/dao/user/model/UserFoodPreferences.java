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
@Table(name="user_food_preferences")
@NamedQueries({
    @NamedQuery(name = "UserFoodPreferences.findByUserId", query = "SELECT p FROM UserFoodPreferences p where p.userId = :userId"),
    @NamedQuery(name = "UserFoodPreferences.deleteByUserId", query = "DELETE FROM UserFoodPreferences m where m.userId = :userId")
    })
@ApiModel("UserFoodPreferences")
public class UserFoodPreferences {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="user_id")
	private long userId;
	@Column(name="food_preference_id")
	private long foodPreferenceId;
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
	public long getFoodPreferenceId() {
		return foodPreferenceId;
	}
	public void setFoodPreferenceId(long foodPreferenceId) {
		this.foodPreferenceId = foodPreferenceId;
	}
}
