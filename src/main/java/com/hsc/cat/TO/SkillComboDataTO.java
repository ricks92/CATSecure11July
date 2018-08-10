package com.hsc.cat.TO;

import java.util.List;

public class SkillComboDataTO {

	private int skillId;
	private	String skillName;
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
