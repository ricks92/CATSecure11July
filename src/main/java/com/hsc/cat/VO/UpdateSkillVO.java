package com.hsc.cat.VO;

public class UpdateSkillVO {

	
	private int skillId;
	private String empId;
	private int weekNumber;
	private int rating;
	private String ratingDoneBy;
	private String comment;
	private String ratingDoneByEmpId;
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
	public int getSdlcCategory() {
		return sdlcCategory;
	}
	public void setSdlcCategory(int sdlcCategory) {
		this.sdlcCategory = sdlcCategory;
	}
	
	
}
