package com.hsc.cat.enums;

public enum ApprovalStatusEnum {

	 NA(0,"NA"),PENDING(1,"Pending"),APPROVED(1,"Approved"),REJECTED(2,"Rejected");
	
	private int value;
	private String status;
	
	private ApprovalStatusEnum(int value,String text){
		this.value=value;
		status=text;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
