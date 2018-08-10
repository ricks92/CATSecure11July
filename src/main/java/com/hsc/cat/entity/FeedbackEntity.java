package com.hsc.cat.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feedback_details")
public class FeedbackEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name = "emp_id")
	private String empId;
	
	@Column(name="week_number")
	private int weekNumber;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="RATING_DONE_BY_EMP_ID")
	private String ratingDoneByEmpId;
	
	@Column(name = "creation_date")
	private Date creationDate;
	
	@Column(name="rating_done_by")
	private String ratingDoneBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public int getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRatingDoneByEmpId() {
		return ratingDoneByEmpId;
	}

	public void setRatingDoneByEmpId(String ratingDoneByEmpId) {
		this.ratingDoneByEmpId = ratingDoneByEmpId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getRatingDoneBy() {
		return ratingDoneBy;
	}

	public void setRatingDoneBy(String ratingDoneBy) {
		this.ratingDoneBy = ratingDoneBy;
	}
	
	
}
