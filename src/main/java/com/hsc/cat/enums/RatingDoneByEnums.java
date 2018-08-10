package com.hsc.cat.enums;

public enum RatingDoneByEnums {

	SELF("Self"),MANAGER("Manager"),PEER("Peer");
	
	String type;
	
	private RatingDoneByEnums(String input) {
		type=input;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
