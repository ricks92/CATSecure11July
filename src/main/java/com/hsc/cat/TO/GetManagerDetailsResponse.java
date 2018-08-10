package com.hsc.cat.TO;

import java.util.List;

public class GetManagerDetailsResponse  {


	private List<ManagerDetails> managerList;
	private String responseCode;
	private String responseMessage;
	
	public List<ManagerDetails> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<ManagerDetails> managerList) {
		this.managerList = managerList;
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
