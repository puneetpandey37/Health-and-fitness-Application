package com.supafit.model.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.supafit.model.address.AddressDTO;
import com.supafit.model.coach.CoachDTO;
import com.supafit.model.food.FoodPreferencesDTO;
import com.supafit.model.goal.FitnessGoalDTO;
import com.supafit.model.medicalcondition.MedicalConditionDTO;
import com.supafit.model.program.ProgramSubscriptionDTO;
import com.wordnik.swagger.annotations.ApiModel;

@ApiModel("User")
public class UserDTO {
	private long id;
	private String userId;
	private CoachDTO trainer;
	private String name;
	private String dob;
	private String email;
	private String otp;
	private String gcmId;
	private String password;
	private String imageURL;
	private String gender;
	private List<PhoneNumberDTO> PhoneNumbers;
	private UserPhysicDTO userPhysic;
	private CoachDTO dietitan;
	private String alternateEmailId;
	private String fieldOfWork;
	private String lifestyle;
	List<AddressDTO> userAddresses;
	List<FitnessGoalDTO> goals;
	List<MedicalConditionDTO> medicalConditions;
	List<FoodPreferencesDTO> foodPreferences;
	private String dateCreated;
	private String dateUpdated;
	private CoachDTO yogaTrainer;
	@JsonManagedReference
	private List<ProgramSubscriptionDTO> subscriptions;
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
	public List<PhoneNumberDTO> getPhoneNumbers() {
		return PhoneNumbers;
	}
	public void setPhoneNumbers(List<PhoneNumberDTO> phoneNumbers) {
		PhoneNumbers = phoneNumbers;
	}
	public UserPhysicDTO getUserPhysic() {
		return userPhysic;
	}
	public void setUserPhysic(UserPhysicDTO userPhysic) {
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
	public List<AddressDTO> getUserAddresses() {
		return userAddresses;
	}
	public void setUserAddresses(List<AddressDTO> userAddresses) {
		this.userAddresses = userAddresses;
	}
	public List<FitnessGoalDTO> getGoals() {
		return goals;
	}
	public void setGoals(List<FitnessGoalDTO> goals) {
		this.goals = goals;
	}
	public List<MedicalConditionDTO> getMedicalConditions() {
		return medicalConditions;
	}
	public void setMedicalConditions(List<MedicalConditionDTO> medicalConditions) {
		this.medicalConditions = medicalConditions;
	}
	public List<FoodPreferencesDTO> getFoodPreferences() {
		return foodPreferences;
	}
	public void setFoodPreferences(List<FoodPreferencesDTO> foodPreferences) {
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
	public CoachDTO getDietitan() {
		return dietitan;
	}
	public void setDietitan(CoachDTO dietitan) {
		this.dietitan = dietitan;
	}
	public CoachDTO getYogaTrainer() {
		return yogaTrainer;
	}
	public void setYogaTrainer(CoachDTO yogaTrainer) {
		this.yogaTrainer = yogaTrainer;
	}
	public List<ProgramSubscriptionDTO> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(List<ProgramSubscriptionDTO> subscriptions) {
		this.subscriptions = subscriptions;
	}
	public CoachDTO getTrainer() {
		return trainer;
	}
	public void setTrainer(CoachDTO trainer) {
		this.trainer = trainer;
	}
}
