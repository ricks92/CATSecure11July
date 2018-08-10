package com.hsc.cat.TO;

import java.util.List;

public class ViewTeamTO {
	
	private List<EmployeeUnderManagerDetails> listOfEmployee ;
	private String responseCode;
	private String responseMessage;
	public List<EmployeeUnderManagerDetails> getListOfEmployee() {
		return listOfEmployee;
	}
	public void setListOfEmployee(List<EmployeeUnderManagerDetails> listOfEmployee) {
		this.listOfEmployee = listOfEmployee;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
}
