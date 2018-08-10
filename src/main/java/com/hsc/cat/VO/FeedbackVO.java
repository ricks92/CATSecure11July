package com.hsc.cat.VO;

public class FeedbackVO {

	
	private String empId;
	private int weekNumber;
	private String ratingDoneBy;
	private String ratingDoneByEmpId;
	private String comment;
	
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
	public String getRatingDoneBy() {
		return ratingDoneBy;
	}
	public void setRatingDoneBy(String ratingDoneBy) {
		this.ratingDoneBy = ratingDoneBy;
	}
	public String getRatingDoneByEmpId() {
		return ratingDoneByEmpId;
	}
	public void setRatingDoneByEmpId(String ratingDoneByEmpId) {
		this.ratingDoneByEmpId = ratingDoneByEmpId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
