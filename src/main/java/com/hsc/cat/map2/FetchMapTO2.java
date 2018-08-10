package com.hsc.cat.map2;

import java.util.List;
import java.util.Map;

import com.hsc.cat.TO.SkillTO;

public class FetchMapTO2 {

	private SkillMapResponse2 skillMapResponse;
	private  List<Map<String,String>> listOfselfReviews;
	private List<Map<String,String>> listOfpeerReviews;
	
	public SkillMapResponse2 getSkillMapResponse() {
		return skillMapResponse;
	}
	public void setSkillMapResponse(SkillMapResponse2 skillMapResponse) {
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
