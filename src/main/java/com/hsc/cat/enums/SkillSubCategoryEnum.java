package com.hsc.cat.enums;

public enum SkillSubCategoryEnum {

	TECHNOLOGY(1,"Technology"),FRAMEWORK(2,"Framework"),OTHERS(5,"Others");
	private int value;
	private String name;
	
	
	private SkillSubCategoryEnum(int a,String b) {
		value=a;
		name=b;
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
	
	
	public static int getTechnologyFromName(String name) {
		SkillSubCategoryEnum skillSubCategoryEnumArray[]= SkillSubCategoryEnum.values();
		int value=5;
		for(SkillSubCategoryEnum sk:skillSubCategoryEnumArray) {
			if(sk.getName().equalsIgnoreCase(name)) {
				value=sk.getValue();
			}
		}
		
		return value;
	}
	
	
	public static String getTechnologyNameFromNumber(int v) {
		SkillSubCategoryEnum skillSubCategoryEnumArray[]= SkillSubCategoryEnum.values();
		String name="Others";
		for(SkillSubCategoryEnum sk:skillSubCategoryEnumArray) {
			if(sk.getValue()==v) {
				name=sk.getName();
			}
		}
		
		return name;
	}
}
