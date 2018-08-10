package com.hsc.cat.VO;

import java.util.List;

public class CreateProfileVO {

	private String firstName;
	private String lastName;
	private String userName;
	private String department;
	private String emailId;
	private String projectRole;
	private List<String> selectedDomainSkills;
	private List<String> selectedOtherDomainSkills;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
