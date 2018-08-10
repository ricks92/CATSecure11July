package com.hsc.cat.VO;

import java.util.List;

public class PieChartForCountPerLevelPerSkillComboVO {

	private	List<Integer> skillIdList;
	private	int sdlcCategory;
	private	int quarter;
	
	public List<Integer> getSkillIdList() {
		return skillIdList;
	}
	public void setSkillIdList(List<Integer> skillIdList) {
		this.skillIdList = skillIdList;
	}
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
	
	
	
}
