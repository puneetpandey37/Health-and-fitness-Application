package com.supafit.dao.plan.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="meal_plans")
@NamedQueries({
	@NamedQuery(name = "MealPlans.findAll", query = "SELECT m FROM MealPlans m"),
	@NamedQuery(name = "MealPlans.findByUserIdAndDates", query = "SELECT m FROM MealPlans m WHERE m.userId = :userId AND m.dateCreated >= :startDate AND m.dateCreated <= :endDate"),
	@NamedQuery(name = "MealPlans.findByDietitianId", query = "SELECT m FROM MealPlans m WHERE m.dietitianId = :dietitianId")
})
@ApiModel("MealPlans")
public class MealPlans {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="user_id")
	private Long userId;
	@Column(name="dietitian_id")
	private Long dietitianId;
	@Column(name="date_created")
	private String dateCreated;
	@Column(name="dietitian_remarks")
	private String dietitianRemarks;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "meal_plan_id", referencedColumnName = "id")
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonManagedReference
	private List<MealPlanDetail> mealPlanDetails;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getDietitianId() {
		return dietitianId;
	}
	public void setDietitianId(Long dietitianId) {
		this.dietitianId = dietitianId;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDietitianRemarks() {
		return dietitianRemarks;
	}
	public void setDietitianRemarks(String dietitianRemarks) {
		this.dietitianRemarks = dietitianRemarks;
	}
	public List<MealPlanDetail> getMealPlanDetails() {
		return mealPlanDetails;
	}
	public void setMealPlanDetails(List<MealPlanDetail> mealPlanDetails) {
		this.mealPlanDetails = mealPlanDetails;
	}
}
