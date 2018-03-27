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
@Table(name="QUESTION_BANK")
public class QuestionBank implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private Integer id;

	@ManyToOne
	@JoinColumn(name="CATEGORY_ID",nullable=false)
	private QuestionCategory category;
	
	@Column(name="QUESTION")
	private String question;
	
	@Column(name="OPTION_A")
	private String optionA;
	
	@Column(name="OPTION_B")
	private String optionB;
	
	@Column(name="OPTION_C")
	private String optionC;
	
	@Column(name="OPTION_D")
	private String optionD;
	
	@Column(name="ANSWER")
	private String answer;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public QuestionCategory getCategory() {
		return category;
	}

	public void setCategory(QuestionCategory category) {
		this.category = category;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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
