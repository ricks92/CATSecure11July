package com.hsc.cat.TO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProfileTO {
	private String userName;
	private String firstName;
	private String lastName;
	@JsonIgnore
	private String department;
	private String emailId;
	private String projectRole;
	
	private List<String> selectedSkills;
	private List<String> selectedDomainSkills;
	private List<String> selectedOtherDomainSkills;
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
	public String getProjectRole() {
		return projectRole;
	}
	public void setProjectRole(String projectRole) {
		this.projectRole = projectRole;
	}
	public List<String> getSelectedSkills() {
		return selectedSkills;
	}
	public void setSelectedSkills(List<String> selectedSkills) {
		this.selectedSkills = selectedSkills;
	}
	public List<String> getSelectedDomainSkills() {
		return selectedDomainSkills;
	}
	public void setSelectedDomainSkills(List<String> selectedDomainSkills) {
		this.selectedDomainSkills = selectedDomainSkills;
	}
	public List<String> getSelectedOtherDomainSkills() {
		return selectedOtherDomainSkills;
	}
	public void setSelectedOtherDomainSkills(List<String> selectedOtherDomainSkills) {
		this.selectedOtherDomainSkills = selectedOtherDomainSkills;
	}

	
	
	
	
}
