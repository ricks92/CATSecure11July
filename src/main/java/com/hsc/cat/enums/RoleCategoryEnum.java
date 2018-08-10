package com.hsc.cat.enums;


//New class added
public enum RoleCategoryEnum {
	BACKEND_DEVELOPER(1, "BACKEND DEVELOPER"), FRONTEND_DEVELOPER(2, "FRONTEND DEVELOPER"), FULLSTACK_DEVELOPER(3,
			"FULLSTACK DEVELOPER"), MANUAL_TESTER(4, "MANUAL TESTER"), AUTOMATION_TESTER(5, "AUTOMATION TESTER"),INVALID(6,"INVALID");
	int value;
	String name;

	private RoleCategoryEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public static String getProjectRoleNameFromNumber(int v) {
		RoleCategoryEnum roleCategoryEnumArray[]= RoleCategoryEnum.values();
		String name="INVALID";
		for(RoleCategoryEnum sk:roleCategoryEnumArray) {
			if(sk.getValue()==v) {
				name=sk.getName();
			}
		}
		
		return name;
	}

	public static int getProjectRoleNumberFromName(String name) {
		RoleCategoryEnum roleCategoryEnumArray[]= RoleCategoryEnum.values();
		
		int num=6;
		for(RoleCategoryEnum sk:roleCategoryEnumArray) {
			if(sk.name.equalsIgnoreCase(name)) {
				num=sk.value;
			}
		}
		
		return num;
	}
}
