package com.supafit.dao.meal.model;

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

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("Meals")
@Entity
@Table(name="meals")
@NamedQueries({
	@NamedQuery(name = "Meals.findAll", query = "select m from Meals m"),
	@NamedQuery(name = "Meals.findByUserId", query = "select m from Meals m WHERE m.dietitianId = :userId"),
	@NamedQuery(name = "Meals.deleteByMealId", query = "DELETE FROM Meals m where m.id = :mealId")
})
public class Meals {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="meal_name")
	private String name;
	@Column(name="user_id")
	private Long dietitianId;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "meal_id", referencedColumnName = "id")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<UserMeals> meals;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getDietitianId() {
		return dietitianId;
	}
	public void setDietitianId(Long dietitianId) {
		this.dietitianId = dietitianId;
	}
	public List<UserMeals> getMeals() {
		return meals;
	}
	public void setMeals(List<UserMeals> meals) {
		this.meals = meals;
	}
}
