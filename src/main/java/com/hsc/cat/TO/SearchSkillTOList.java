package com.hsc.cat.TO;

import java.util.List;

import com.hsc.cat.entity.Skill;

public class SearchSkillTOList {
private boolean exists;

private List<Skill> skills;
public boolean isExists() {
	return exists;
}
public void setExists(boolean exists) {
	this.exists = exists;
}
public List<Skill> getSkills() {
	return skills;
}
public void setSkills(List<Skill> skills) {
	this.skills = skills;
}


}
