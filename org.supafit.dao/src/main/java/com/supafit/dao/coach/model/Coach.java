package com.supafit.dao.coach.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.supafit.dao.user.model.User;
import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="coach")
@NamedQueries({
	@NamedQuery(name = "Coach.findAll", query = "SELECT u FROM Coach u"),
    @NamedQuery(name = "Coach.findById", query = "SELECT u FROM Coach u where u.id = :id"),
    @NamedQuery(name = "Coach.findByEmialId", query= "SELECT u FROM Coach u where u.email = :email"),
    @NamedQuery(name = "Coach.findByUserNameAndPassword", query= "SELECT u FROM Coach u where u.email = :userName and u.password = :password")
    })
@ApiModel("Coach")
public class Coach {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="coach_id")
	private String coachId;
	@Column(name="name")
	private String name;
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonManagedReference
//	@JoinTable(
//			   name = "coach_type_mapping", 
//			   joinColumns = @JoinColumn(name = "coach_id"), 
//			   inverseJoinColumns = @JoinColumn(name = "coach_type_id")
//			 )
//	private List<CoachType> coachTypes;
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
	private List<CoachPhoneNumber> PhoneNumbers;
	@Column(name="alternate_email_id")
	private String alternateEmailId;
	@Column(name="years_of_experience")
	private String yearsOfExperience;
	@Column(name="last_experience")
	private String lastExperience;
	@Column(name="languages_known")
	private String languagesKnown;
	@Column(name="about_yourself")
	private String aboutYourself;
	@Column(name="core_competence")
	private String coreCompetence;
	@Transient
	List<CoachAddress> addresses;
	@Transient
	private List<User> users;
	@Transient
	private int numberOfUsers;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCoachId() {
		return coachId;
	}
	public void setCoachId(String coachId) {
		this.coachId = coachId;
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
	/*public Long getCoachTypeId() {
		return coachTypeId;
	}
	public void setCoachTypeId(Long coachTypeId) {
		this.coachTypeId = coachTypeId;
	}*/
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
	public List<CoachPhoneNumber> getPhoneNumbers() {
		return PhoneNumbers;
	}
	public void setPhoneNumbers(List<CoachPhoneNumber> phoneNumbers) {
		PhoneNumbers = phoneNumbers;
	}
	public String getAlternateEmailId() {
		return alternateEmailId;
	}
	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}
	public String getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	public String getLastExperience() {
		return lastExperience;
	}
	public void setLastExperience(String lastExperience) {
		this.lastExperience = lastExperience;
	}
	public String getLanguagesKnown() {
		return languagesKnown;
	}
	public void setLanguagesKnown(String languagesKnown) {
		this.languagesKnown = languagesKnown;
	}
	public String getAboutYourself() {
		return aboutYourself;
	}
	public void setAboutYourself(String aboutYourself) {
		this.aboutYourself = aboutYourself;
	}
	public String getCoreCompetence() {
		return coreCompetence;
	}
	public void setCoreCompetence(String coreCompetence) {
		this.coreCompetence = coreCompetence;
	}
	public List<CoachAddress> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<CoachAddress> addresses) {
		this.addresses = addresses;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public int getNumberOfUsers() {
		return numberOfUsers;
	}
	public void setNumberOfUsers(int numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}
//	public List<CoachType> getCoachTypes() {
//		return coachTypes;
//	}
//	public void setCoachTypes(List<CoachType> coachTypes) {
//		this.coachTypes = coachTypes;
//	}
}
