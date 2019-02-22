package com.supafit.dao.plan.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.supafit.dao.food.model.MealTimings;
import com.supafit.dao.meal.model.Meals;
import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="meal_plan_detail")
@NamedQueries({
	@NamedQuery(name = "MealPlanDetail.findAll", query = "select m from MealPlanDetail m"),
	@NamedQuery(name = "MealPlanDetail.findByPlanId", query = "select m from MealPlanDetail m where m.mealPlans.id = :planId"),
	@NamedQuery(name = "MealPlanDetail.findUncompletedByDietitianIdAndDate", query = "SELECT s FROM MealPlanDetail s "
			+ "WHERE s.mealPlans.dietitianId = :dietitianId "
			+ "AND s.planDate >= :startDate "
			+ "AND s.planDate <= :endDate "
			+ "AND s.planStatus != 1"),
	@NamedQuery(name = "MealPlanDetail.findCompletedByDietitianIdAndDate", query = "SELECT s FROM MealPlanDetail s "
			+ "WHERE s.mealPlans.dietitianId = :dietitianId "
			+ "AND s.planDate >= :startDate "
			+ "AND s.planDate <= :endDate "
			+ "AND s.planStatus = 1")		
})
@ApiModel("MealPlanDetail")
public class MealPlanDetail {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="plan_date")
	private String planDate;
	/*@Column(name="meal_plan_id")
	private Long mealPlanId;*/
	@ManyToOne
	@JoinColumn(name = "meal_plan_id", referencedColumnName = "id")
	@JsonBackReference
	private MealPlans mealPlans;
	/*@Column(name="meal_id")
	private Long mealId;*/
	@OneToOne
	@JoinColumn(name = "meal_id", referencedColumnName = "id")
	private Meals meal;
	/*@Column(name="meal_timing_id")
	private Long meal_timing_id;*/
	@OneToOne
	@JoinColumn(name = "meal_timing_id", referencedColumnName = "id")
	private MealTimings mealTiming;
	@Column(name="special_instructions")
	private String specialInstructions;
	@Column(name="coach_remarks")
	private String coachRemarks;
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "meal_plan_detail_id", referencedColumnName = "id")
//	@Fetch(value = FetchMode.SUBSELECT)
//	private List<MealPlanStatus> mealPlanStatus;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "meal_plan_detail_id", referencedColumnName = "id")
	@Fetch(value = FetchMode.SUBSELECT)
//	@JsonManagedReference
	private List<MealPlanStatusDetail> mealPlanStatus;
	@Column(name="plan_status")
	private Integer planStatus;
	@Column(name="user_remarks")
	private String userRemarks;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPlanDate() {
		return planDate;
	}
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	/*public Long getMealPlanId() {
		return mealPlanId;
	}
	public void setMealPlanId(Long mealPlanId) {
		this.mealPlanId = mealPlanId;
	}*/
	public Meals getMeal() {
		return meal;
	}
	public MealPlans getMealPlans() {
		return mealPlans;
	}
	public void setMealPlans(MealPlans mealPlans) {
		this.mealPlans = mealPlans;
	}
	public void setMeal(Meals meal) {
		this.meal = meal;
	}
	public MealTimings getMealTiming() {
		return mealTiming;
	}
	public void setMealTiming(MealTimings mealTiming) {
		this.mealTiming = mealTiming;
	}
	public String getSpecialInstructions() {
		return specialInstructions;
	}
	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}
	public String getCoachRemarks() {
		return coachRemarks;
	}
	public void setCoachRemarks(String coachRemarks) {
		this.coachRemarks = coachRemarks;
	}
	/*public List<MealPlanStatus> getMealPlanStatus() {
		return mealPlanStatus;
	}
	public void setMealPlanStatus(List<MealPlanStatus> mealPlanStatus) {
		this.mealPlanStatus = mealPlanStatus;
	}*/
	/*public MealPlanStatus getMealPlanStatus() {
		return mealPlanStatus;
	}
	public void setMealPlanStatus(MealPlanStatus mealPlanStatus) {
		this.mealPlanStatus = mealPlanStatus;
	}*/
	/*public List<MealPlanStatus> getMealPlanStatus() {
		return mealPlanStatus;
	}
	public void setMealPlanStatus(List<MealPlanStatus> mealPlanStatus) {
		this.mealPlanStatus = mealPlanStatus;
	}*/
	public Integer getPlanStatus() {
		return planStatus;
	}
	public List<MealPlanStatusDetail> getMealPlanStatus() {
		return mealPlanStatus;
	}
	public void setMealPlanStatus(List<MealPlanStatusDetail> mealPlanStatus) {
		this.mealPlanStatus = mealPlanStatus;
	}
	public void setPlanStatus(Integer planStatus) {
		this.planStatus = planStatus;
	}
	public String getUserRemarks() {
		return userRemarks;
	}
	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}
	
}
