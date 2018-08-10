package com.hsc.cat.VO;

public class AddSkillVO {

	

	private String skillName;

	private String description;

	private int skillCategory;
	private int skillSubCategory;

	

	public int getSkillCategory() {
		return skillCategory;
	}

	public void setSkillCategory(int skillCategory) {
		this.skillCategory = skillCategory;
	}

	public int getSkillSubCategory() {
		return skillSubCategory;
	}

	public void setSkillSubCategory(int skillSubCategory) {
		this.skillSubCategory = skillSubCategory;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
