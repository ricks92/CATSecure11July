package com.hsc.cat.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_skills")
public class EmployeeSkillEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "emp_id")
	private String empId;
	
	@Column(name = "skill_id")
	private int skillId;
	
	@Column(name="week_number")
	private int weekNumber;
	
	@Column(name="rating")
	private String rating;
	
	@Column(name="rating_done_by")
	private String ratingDoneBy;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="RATING_DONE_BY_EMP_ID")
	private String ratingDoneByEmpId;
	
	
	@Column(name = "creation_date")
	private Date creationDate;

	@Column(name = "sdlc_category")
	private String sdlcCategory;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRatingDoneBy() {
		return ratingDoneBy;
	}

	public void setRatingDoneBy(String ratingDoneBy) {
		this.ratingDoneBy = ratingDoneBy;
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

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	
	

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	

	public String getSdlcCategory() {
		return sdlcCategory;
	}

	public void setSdlcCategory(String sdlcCategory) {
		this.sdlcCategory = sdlcCategory;
	}

	@Override
	public String toString() {
		return "EmployeeSkillEntity [id=" + id + ", empId=" + empId + ", skillId=" + skillId + ", weekNumber="
				+ weekNumber + ", rating=" + rating + ", ratingDoneBy=" + ratingDoneBy + ", comment=" + comment
				+ ", ratingDoneByEmpId=" + ratingDoneByEmpId + ", creationDate=" + creationDate + ", sdlcCategory="
				+ sdlcCategory + "]";
	}

	

	
	
	
	
}
