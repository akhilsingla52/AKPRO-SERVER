package com.akpro.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.akpro.util.DateUtils;

@Entity
@Table(name="WORK_EXPERIENCE")
public class WorkExperience implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="USER_ID",nullable=false)
	private Users user;
	
	@Column(name="COMPANY_NAME")
	private String company;
	
	@Column(name="DURATION")
	private String duration;
	
	@Column(name="TECHNOLOGY")
	private String technology;
	
	@Column(name="TIME_CREATED")
	private Long timeCreated;
	
	@Column(name="TIME_MODIFIED")
	private Long timeModified;
	
	@PrePersist
	public void prePersist() {
		this.setTimeCreated(DateUtils.getCurrentDateAndTimeInUTCTimeZone());
		this.setTimeModified(DateUtils.getCurrentDateAndTimeInUTCTimeZone());
	}

	@PreUpdate
	public void preUpdate() {
		this.setTimeModified(DateUtils.getCurrentDateAndTimeInUTCTimeZone());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public Long getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Long timeCreated) {
		this.timeCreated = timeCreated;
	}

	public Long getTimeModified() {
		return timeModified;
	}

	public void setTimeModified(Long timeModified) {
		this.timeModified = timeModified;
	}
}
