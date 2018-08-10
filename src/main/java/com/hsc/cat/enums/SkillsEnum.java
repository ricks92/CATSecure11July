package com.hsc.cat.enums;

public enum SkillsEnum {

	JAVA(1,"Java"),
	C(2,"C"),
	PYTHON(3,"Python"),
	C_PLUS_PLUS(4,"C++"),
	SPRING_CORE(5,"Spring Core"),
	SPRING_MVC(6,"Spring MVC"),
	SPRING_BOOT(6,"Spring Boot"),
	SPRING_SECURITY(6,"Spring Security"),
	HIBERNATE(7,"Hibernate"),
	JPA(7,"JPA"),
	HTML(7,"HTML"),
	BOOTSTRAP(7,"Bootstrap"),
	ANGULAR(7,"Angular"),
	MONGODB(6,"MongoDB"),
	IBATIS(6,"Ibatis"),
	CSS(6,"CSS")
	;
	
	
	private int value;
	private String name;
	
	private SkillsEnum(int a,String b) {
		value=a;
		name=b;
	}
}
