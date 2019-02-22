package com.supafit.dao.coach.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wordnik.swagger.annotations.ApiModel;

@Entity
@Table(name="coach_messaging")
@NamedQueries({
	@NamedQuery(name = "CoachMessages.findAll", query = "SELECT u FROM CoachMessages u"),
    @NamedQuery(name = "CoachMessages.findById", query = "SELECT u FROM CoachMessages u where u.id = :id"),
    @NamedQuery(name = "CoachMessages.findByCoachIdAndUserId", query = "SELECT u FROM CoachMessages u where u.userId = :userId AND u.coachId = :coachId ORDER BY u.id desc"),
    @NamedQuery(name = "CoachMessages.deleteById", query = "DELETE FROM CoachMessages m where m.id = :id"),
    @NamedQuery(name = "CoachMessages.findByUserId", query = "SELECT u FROM CoachMessages u where u.userId = :userId ORDER BY u.id desc")
    })
@ApiModel("CoachMessages")
public class CoachMessages {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "coach_id")
	private Long coachId;
	@Transient
	private String coachName;
	@Transient
	private String imageURL;
	@Column(name = "message")
	private String message;
	@Column(name = "date_created")
	private String dateCreated;
	@Column(name = "date_updated")
	private String dateUpdated;
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
	public Long getCoachId() {
		return coachId;
	}
	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
}
