/**
**********************************************************************************************************
--  FILENAME		: FetchMapTO3.java
--  DESCRIPTION		: DTO for displaying dashboard of employees
--
--  Copyright		: Copyright (c) 2018.
--  Company			: HSC
--
--  Revision History
-- --------------------------------------------------------------------------------------------------------
-- |VERSION |      Date                              |      Author              |      Reason for Changes                                         |
-- --------------------------------------------------------------------------------------------------------
-- |  0.1   |   June 14, 2018                         |     Richa Anand      |       Initial draft                                                        |
-- --------------------------------------------------------------------------------------------------------
--
************************************************************************************************************
**/


package com.hsc.cat.map3;

import java.util.List;
import java.util.Map;

import com.hsc.cat.TO.SkillTO;

public class FetchMapTO3 {

	private SkillMapResponse3 skillMapResponse;
	private  List<Map<String,String>> listOfselfReviews;
	private List<Map<String,String>> listOfpeerReviews;
	
	public SkillMapResponse3 getSkillMapResponse() {
		return skillMapResponse;
	}
	public void setSkillMapResponse(SkillMapResponse3 skillMapResponse) {
		this.skillMapResponse = skillMapResponse;
	}
	
	public List<Map<String, String>> getListOfselfReviews() {
		return listOfselfReviews;
	}
	public void setListOfselfReviews(List<Map<String, String>> listOfselfReviews) {
		this.listOfselfReviews = listOfselfReviews;
	}
	public List<Map<String, String>> getListOfpeerReviews() {
		return listOfpeerReviews;
	}
	public void setListOfpeerReviews(List<Map<String, String>> listOfpeerReviews) {
		this.listOfpeerReviews = listOfpeerReviews;
	}

	
	
	
}
