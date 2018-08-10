package com.hsc.cat.TO;

import org.springframework.stereotype.Component;


public class ManagerDetails {
	
	private String empId;
	private String firstName;
	private String lastName;
	private String emailId;

	public ManagerDetails() {
		
	}
	
	public ManagerDetails(String empId, String firstName, String lastName, String emailId) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
