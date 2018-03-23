package com.akpro.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.akpro.util.DateUtils;

@Entity
@Table(name="EXAM_DETAILS")
public class ExamDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="USER_ID",nullable=false)
	private Users user;

	@ManyToOne
	@JoinColumn(name="CATEGORY_ID",nullable=false)
	private QuestionCategory category;
	
	@Column(name="QUESTION_ATTEMPT")
	private Integer attempt;
	
	@Column(name="QUESTION_RIGHT")
	private Integer right;
	
	@Column(name="MARKS")
	private Integer marks;
	
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

	public QuestionCategory getCategory() {
		return category;
	}

	public void setCategory(QuestionCategory category) {
		this.category = category;
	}

	public Integer getAttempt() {
		return attempt;
	}

	public void setAttempt(Integer attempt) {
		this.attempt = attempt;
	}

	public Integer getRight() {
		return right;
	}

	public void setRight(Integer right) {
		this.right = right;
	}

	public Integer getMarks() {
		return marks;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
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
