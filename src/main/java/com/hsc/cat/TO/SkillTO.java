package com.hsc.cat.TO;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SkillTO {
	private int skillId;

	private String skillName;

	private String description;

	@JsonIgnore
	private Date creationDate;

	@JsonIgnore
	private Date updationDate;

	@JsonIgnore
	private String issue;
	
	
	private String skillCategory;
	private String skillSubCategory;

	public String getSkillCategory() {
		return skillCategory;
	}

	public void setSkillCategory(String skillCategory) {
		this.skillCategory = skillCategory;
	}

	public String getSkillSubCategory() {
		return skillSubCategory;
	}

	public void setSkillSubCategory(String skillSubCategory) {
		this.skillSubCategory = skillSubCategory;
	}
	
	
	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
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

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	
	
}
