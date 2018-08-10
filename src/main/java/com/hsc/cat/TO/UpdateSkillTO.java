package com.hsc.cat.TO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UpdateSkillTO {

	private int id;
	private int skillId;
	private String empId;
	private int weekNumber;
	private int rating;
	private String ratingDoneBy;
	private String comment;
	private String ratingDoneByEmpId;
	@JsonIgnore
	private String problem;
	@JsonIgnore
	public int issue;
	
	private int sdlcCategory;
	private Date creationDate;
	
	private String creationDateString;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
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
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
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
	public int getIssue() {
		return issue;
	}
	public void setIssue(int issue) {
		this.issue = issue;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getCreationDateString() {
		return creationDateString;
	}
	public void setCreationDateString(String creationDateString) {
		this.creationDateString = creationDateString;
	}
	public int getSdlcCategory() {
		return sdlcCategory;
	}
	public void setSdlcCategory(int sdlcCategory) {
		this.sdlcCategory = sdlcCategory;
	}
	
	
	
}
