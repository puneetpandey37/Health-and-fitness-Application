package com.supafit.dao.user.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.supafit.dao.coach.model.Coach;
import com.supafit.dao.food.model.FoodPreferences;
import com.supafit.dao.goal.model.FitnessGoal;
import com.supafit.dao.location.model.Address;
import com.supafit.dao.medicalcondition.model.MedicalCondition;
import com.supafit.dao.program.model.ProgramSubscription;
import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="user")
@NamedQueries({
	@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u where u.id = :userId"),
    @NamedQuery(name = "User.updateGcmId", query = "UPDATE User u SET u.gcmId = :gcmId WHERE u.id = :userId"),
    @NamedQuery(name = "User.getGcmId", query = "SELECT u.gcmId FROM User u where u.id = :userId"),
    @NamedQuery(name = "User.findUsersByCoachId", query = "SELECT u FROM User u where u.trainer.id = :coachId"),
    @NamedQuery(name = "User.assignCoachToUser", query = "UPDATE User u SET u.trainer.id = :coachId where u.id = :id"),
    @NamedQuery(name = "User.assignDietitanToUser", query = "UPDATE User u SET u.dietitan.id = :dietitanId where u.id = :id"),
    @NamedQuery(name = "User.findUsersByDietitanId", query = "SELECT u FROM User u where u.dietitan.id = :dietitanId"),
    @NamedQuery(name = "User.findByEmialId", query= "SELECT u FROM User u where u.email = :email"),
    @NamedQuery(name = "User.findByUserAndCoachId", query= "SELECT u FROM User u where u.id = :userId and u.trainer.id = :coachId"),
    @NamedQuery(name = "User.findByUserNameAndPassword", query= "SELECT u FROM User u where u.email = :userName and u.password = :password"),
    @NamedQuery(name=  "User.findUsersByDateRange", query = "SELECT u FROM User u where u.dateCreated >= :startDate AND u.dateCreated <= :endDate"),
    @NamedQuery(name = "User.findWithoutAnyOfCoaches", query = "SELECT u FROM User u WHERE u.dietitan.id IS NULL OR u.trainer.id IS NULL"),
    @NamedQuery(name = "User.findUnAssignedTrainerUsers", query = "SELECT u FROM User u WHERE u.trainer.id IS NULL"),
    @NamedQuery(name = "User.findUnAssignedDietitanrUsers", query = "SELECT u FROM User u WHERE u.dietitan.id IS NULL"),
    @NamedQuery(name = "User.findUserUnderThisTrainer", query = "SELECT u from User u WHERE u.trainer.id = :coachId"),
    @NamedQuery(name = "User.findUserUnderThisDietitian", query = "SELECT u from User u WHERE u.dietitan.id = :dietitanId"),
    @NamedQuery(name = "User.findTraierIdByUserId", query = "SELECT u FROM User u WHERE u.trainer.id = :coachId"),
    @NamedQuery(name = "User.findDietitianIdByUserId", query = "SELECT u FROM User u WHERE u.dietitan.id = :dietitanId"),
//    @NamedQuery(name = "User.findYogaTrainerIdByUserId", query = "SELECT u FROM User u WHERE u.yogaTrainer.id = :yogaTrainer"),
//    @NamedQuery(name = "User.assignYogaTrianerToUser", query = "UPDATE User u SET u.yogaTrainer.id = :yogaTrainerId where u.id = :id"),
    @NamedQuery(name = "User.findByUserAndDietitianId", query= "SELECT u FROM User u where u.id = :userId and u.dietitan.id = :coachId"),
//    @NamedQuery(name = "User.findByUserAndYogaTrainerId", query= "SELECT u FROM User u where u.id = :userId and u.yogaTrainer.id = :coachId"),
//    @NamedQuery(name = "User.findUsersBuYogaTrainerId", query= "SELECT u FROM User u where u.yogaTrainer.id = :coachId"),
    @NamedQuery(name = "User.findUserUnderThisTrainerByDate", query = "SELECT u from User u WHERE u.trainer.id = :coachId and u.dateCreated >= :date"),
    @NamedQuery(name = "User.findUsersUnderDietitionByDate", query = "SELECT u FROM User u where u.dietitan.id = :coachId and u.dateCreated >= :date"),
//    @NamedQuery(name = "User.findUsersUnderYogaTrainerByDate", query= "SELECT u FROM User u where u.yogaTrainer.id = :coachId and u.dateCreated >= :date")
    })
@ApiModel("user")
public class User {
	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="user_id")
	private String userId;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coach_id", referencedColumnName = "id")
	private Coach trainer;
	@Column(name="name")
	private String name;
	@Column(name="dob")
	private String dob;
	@Column(name="email")
	private String email;
	@Column(name="otp")
	private String otp;
	@Column(name="gcm_id")
	private String gcmId;
	@Column(name="password")
	@JsonIgnore
	private String password;
	@Column(name="image_url")
	private String imageURL;
	@Column(name="gender")
	private String gender;
	@Transient
	private List<PhoneNumber> PhoneNumbers;
	@Transient
	private UserPhysic userPhysic;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dietician_id", referencedColumnName = "id")
	private Coach dietitan;
	@Column(name="alternate_email_id")
	private String alternateEmailId;
	@Column(name="about_yourself")
	private String fieldOfWork;
	@Column(name="lifestyle")
	private String lifestyle;
	@Transient
	List<Address> userAddresses;
	@Transient
	List<FitnessGoal> goals;
	@Transient
	List<MedicalCondition> medicalConditions;
	@Transient
	List<FoodPreferences> foodPreferences;
	@Column(name="date_created")
	private String dateCreated;
	@Column(name="date_updated")
	private String dateUpdated;
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "yoga_trainer_id", referencedColumnName = "id")
//	private Coach yogaTrainer;
	@OneToMany
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	private List<ProgramSubscription> subscriptions;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getGcmId() {
		return gcmId;
	}
	public void setGcmId(String gcmId) {
		this.gcmId = gcmId;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<PhoneNumber> getPhoneNumbers() {
		return PhoneNumbers;
	}
	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		PhoneNumbers = phoneNumbers;
	}
	public UserPhysic getUserPhysic() {
		return userPhysic;
	}
	public void setUserPhysic(UserPhysic userPhysic) {
		this.userPhysic = userPhysic;
	}
	public String getAlternateEmailId() {
		return alternateEmailId;
	}
	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}
	public String getFieldOfWork() {
		return fieldOfWork;
	}
	public void setFieldOfWork(String fieldOfWork) {
		this.fieldOfWork = fieldOfWork;
	}
	public String getLifestyle() {
		return lifestyle;
	}
	public void setLifestyle(String lifestyle) {
		this.lifestyle = lifestyle;
	}
	public List<Address> getUserAddresses() {
		return userAddresses;
	}
	public void setUserAddresses(List<Address> userAddresses) {
		this.userAddresses = userAddresses;
	}
	public List<FitnessGoal> getGoals() {
		return goals;
	}
	public void setGoals(List<FitnessGoal> goals) {
		this.goals = goals;
	}
	public List<MedicalCondition> getMedicalConditions() {
		return medicalConditions;
	}
	public void setMedicalConditions(List<MedicalCondition> medicalConditions) {
		this.medicalConditions = medicalConditions;
	}
	public List<FoodPreferences> getFoodPreferences() {
		return foodPreferences;
	}
	public void setFoodPreferences(List<FoodPreferences> foodPreferences) {
		this.foodPreferences = foodPreferences;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public Coach getDietitan() {
		return dietitan;
	}
	public void setDietitan(Coach dietitan) {
		this.dietitan = dietitan;
	}
//	public Coach getYogaTrainer() {
//		return yogaTrainer;
//	}
//	public void setYogaTrainer(Coach yogaTrainer) {
//		this.yogaTrainer = yogaTrainer;
//	}
	public List<ProgramSubscription> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(List<ProgramSubscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	public Coach getTrainer() {
		return trainer;
	}
	public void setTrainer(Coach trainer) {
		this.trainer = trainer;
	}
}
