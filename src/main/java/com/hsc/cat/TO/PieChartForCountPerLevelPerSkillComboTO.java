package com.hsc.cat.TO;

import java.util.List;

public class PieChartForCountPerLevelPerSkillComboTO {
	private	int sdlcCategory;
	private	int quarter;
	
	List<SkillComboDataTO> skillList;

	public int getSdlcCategory() {
		return sdlcCategory;
	}

	public void setSdlcCategory(int sdlcCategory) {
		this.sdlcCategory = sdlcCategory;
	}

	public int getQuarter() {
		return quarter;
	}

	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}

	public List<SkillComboDataTO> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<SkillComboDataTO> skillList) {
		this.skillList = skillList;
	}
	
	
}
