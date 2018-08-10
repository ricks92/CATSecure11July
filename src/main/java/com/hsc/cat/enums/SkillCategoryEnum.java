package com.hsc.cat.enums;

public enum SkillCategoryEnum {

	BACKEND(1,"Backend"),FRONTEND(2,"Frontend"),AUTOMATION(3,"Automation"),MANUAL(4,"Manual"),OTHERS(5,"Others"),FULLSTACK(6,"Fullstack");
	private int value;
	private String name;
	
	private SkillCategoryEnum(int a,String b) {
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
		SkillCategoryEnum skillCategoryEnumArray[]= SkillCategoryEnum.values();
		int value=5;
		for(SkillCategoryEnum sk:skillCategoryEnumArray) {
			if(sk.getName().equalsIgnoreCase(name)) {
				value=sk.getValue();
			}
		}
		
		return value;
	}
	
	
	public static String getTechnologyNameFromNumber(int v) {
		SkillCategoryEnum skillCategoryEnumArray[]= SkillCategoryEnum.values();
		String name="Others";
		for(SkillCategoryEnum sk:skillCategoryEnumArray) {
			if(sk.getValue()==v) {
				name=sk.getName();
			}
		}
		
		return name;
	}
}
