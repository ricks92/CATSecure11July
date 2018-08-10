package com.hsc.cat.utilities;

public class RESTURLConstants {

	public static final String BASE_URL="cat";
	public static final String REGISTER_USER=BASE_URL+"/auth/register";
	public static final String SKILLS=BASE_URL+"/skills";
	public static final String UPDATE_SKILL=BASE_URL+"/updateSkill";
	public static final String VIEW_SKILL=BASE_URL+"/viewSkill/{empId}/{viewHistory}";
	public static final String FETCH_ALL_EMPLOYEES=BASE_URL+"/employees";
	public static final String GET_MANAGER_DETAILS=BASE_URL+"/getManagerDetails";
	public static final String VERIFY_MANAGER=BASE_URL+"/verifyManager";
	public static final String VIEW_TEAM=BASE_URL+"/viewTeam/{id}";
	public static final String GET_ALL_SELF_RATED_SKILLS=BASE_URL+"/getAllSelfRatedSkills/{empId}";
	public static final String VALIDATE_SECURITY_QUESTIONS=BASE_URL+"/validateSecurityQuestion";
	public static final String VALIDATE_USER=BASE_URL+"/validateUser";
	public static final String CHANGE_PASSWORD=BASE_URL+"/changePassword";
	public static final String FORGOT_PASSWORD=BASE_URL+"/forgetPassword";
	public static final String CREATE_PROFILE=BASE_URL+"/createProfile";
	public static final String VIEW_PROFILE=BASE_URL+"/profile/{empId}";
	public static final String FETCH_SKILLS_IN_A_CATEGORY=BASE_URL+"/fetchSkillsInACategory/{skillCategory}";   
	public static final String FETCH_SKILLS_IN_A_CATEGORY_SUBCATEGORY=BASE_URL+"/fetchSkillsInACategorySubcategory"; 
	
}
