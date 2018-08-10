package com.hsc.cat.TO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EmployeeTO {

	private String empid;
	private String firstName;
	private String lastName;
	@JsonIgnore
	private String department;
	private Date creationDate;
	private Date updationDate;
	private String securityQues1;
	private String securityAns1;
	private String securityQues2;
	private String securityAns2;
	private String managerId;
	private String email;
	private String approvalStatus;
	@JsonIgnore
	private String issue;



	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdationDate() {
		return updationDate;
	}

	public void setUpdationDate(Date updationDate) {
		this.updationDate = updationDate;
	}



	public String getSecurityQues1() {
		return securityQues1;
	}



	public void setSecurityQues1(String securityQues1) {
		this.securityQues1 = securityQues1;
	}



	public String getSecurityAns1() {
		return securityAns1;
	}



	public void setSecurityAns1(String securityAns1) {
		this.securityAns1 = securityAns1;
	}



	public String getSecurityQues2() {
		return securityQues2;
	}



	public void setSecurityQues2(String securityQues2) {
		this.securityQues2 = securityQues2;
	}



	public String getSecurityAns2() {
		return securityAns2;
	}



	public void setSecurityAns2(String securityAns2) {
		this.securityAns2 = securityAns2;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}
	
	

}
