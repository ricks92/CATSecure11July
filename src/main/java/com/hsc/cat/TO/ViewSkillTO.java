package com.hsc.cat.TO;

import java.util.Date;

public class ViewSkillTO {
	private String empId;
	private int skillId;
	private String skillName;
	private String description;
	private String weekNumber;
	private int rating;
	private String ratingDoneBy;
	private String ratingDoneByEmpId;
	private Date creationDate;
	private String creationDateString;
	private int sdlcCategory;
	
	
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
	
	public String getWeekNumber() {
		return weekNumber;
	}
	public void setWeekNumber(String weekNumber) {
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
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getRatingDoneByEmpId() {
		return ratingDoneByEmpId;
	}
	public void setRatingDoneByEmpId(String ratingDoneByEmpId) {
		this.ratingDoneByEmpId = ratingDoneByEmpId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
