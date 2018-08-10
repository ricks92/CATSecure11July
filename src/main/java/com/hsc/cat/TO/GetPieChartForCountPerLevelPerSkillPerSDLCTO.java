package com.hsc.cat.TO;

import java.util.List;

public class GetPieChartForCountPerLevelPerSkillPerSDLCTO {

private int skillId;
private	String skillName;
private	int sdlcCategory;
private	int quarter;
private	List<SelfDataPieChartTO> selfData;
private	List<SelfDataPieChartTO> peerData;
	


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
	public List<SelfDataPieChartTO> getSelfData() {
		return selfData;
	}
	public void setSelfData(List<SelfDataPieChartTO> selfData) {
		this.selfData = selfData;
	}
	public List<SelfDataPieChartTO> getPeerData() {
		return peerData;
	}
	public void setPeerData(List<SelfDataPieChartTO> peerData) {
		this.peerData = peerData;
	}
	
	
}
