package com.akpro.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.akpro.util.DateUtils;

@Entity
@Table(name="QUESTION_CATEGORY")
public class QuestionCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="CATEGORY_ID")
	private Integer id;
	
	@Column(name="CATEGORY")
	private String category;
	
	@Column(name="TIME_CREATED")
	private Long timeCreated;
	
	@Column(name="TIME_MODIFIED")
	private Long timeModified;
	
	@OneToMany(mappedBy="category")
	private List<QuestionBank> questions;
	
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public List<QuestionBank> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionBank> questions) {
		this.questions = questions;
	}
}
