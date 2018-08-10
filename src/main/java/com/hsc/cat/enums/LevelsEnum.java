package com.hsc.cat.enums;

public enum LevelsEnum {

	T1(1,"T1"),
	T2(2,"T2"),
	T3(3,"T3"),
	T4(4,"T4"),
	T5(5,"T5"),
	T6(6,"T6");
	
	int level;
	String levelName;
	
	private LevelsEnum(int a,String b) {
		level=a;
		levelName=b;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	
}
