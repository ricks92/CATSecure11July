package com.hsc.cat.VO;

import java.util.List;

public class DeleteSkillsFromProfileVO {
	private List<String> skillsToDeleteList;
	private String empId;;

	

	public List<String> getSkillsToDeleteList() {
		return skillsToDeleteList;
	}

	public void setSkillsToDeleteList(List<String> skillsToDeleteList) {
		this.skillsToDeleteList = skillsToDeleteList;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	
}
