package com.hsc.cat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="login_details")
public class UserDetailsEntity {

	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password")
    private String password;
	
	@Column(name="role")
    private String role;
	
	@OneToOne
	(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH,CascadeType.REMOVE },mappedBy="userDetails")
	@JsonIgnore
	
	private EmployeeDetails employeeDetails;
	public UserDetailsEntity() {
		
	}


	public UserDetailsEntity(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public EmployeeDetails getEmployeeDetails() {
		return employeeDetails;
	}


	public void setEmployeeDetails(EmployeeDetails employeeDetails) {
		this.employeeDetails = employeeDetails;
	}


	@Override
	public String toString() {
		return "UserDetailsEntity [username=" + username + ", password=" + password + ", role=" + role + "]";
	}


	
	
	
}
