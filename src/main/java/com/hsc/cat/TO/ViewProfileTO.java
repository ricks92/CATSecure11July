package com.hsc.cat.TO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ViewProfileTO {
	private String userName;
	private String firstName;
	private String lastName;
	@JsonIgnore
	private String department;
	private String emailId;
	private int projectRole;
	@JsonIgnore
	private List<String> selectedSkills;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getProjectRole() {
		return projectRole;
	}
	public void setProjectRole(int projectRole) {
		this.projectRole = projectRole;
	}
	public List<String> getSelectedSkills() {
		return selectedSkills;
	}
	public void setSelectedSkills(List<String> selectedSkills) {
		this.selectedSkills = selectedSkills;
	}
	

	
	
	
	
}
