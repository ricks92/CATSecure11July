package com.hsc.cat.TO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ViewSkillListTO {

	private List<ViewSkillTO> listOfEmployeeSkills;
	private Set<Integer> listOfSkillId;
	private String selfComment;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Kolkata")
	private Date selfCommentTime;
	//private String peerComment;
	@JsonIgnore
	private List<String> peercomments;
	
	@JsonIgnore
	private List<Date> peercommentsDate;

	
	private List<PeercommentPlusDateListTO> peercommentPlusDateList;
	

	
	public ViewSkillListTO() {
		listOfEmployeeSkills=new ArrayList<>();
		listOfSkillId=new HashSet<>();
		peercommentPlusDateList=new ArrayList<>();
	}
	public List<String> getPeercomments() {
		return peercomments;
	}

	public void setPeercomments(List<String> peercomments) {
		this.peercomments = peercomments;
	}

	public Set<Integer> getListOfSkillId() {
		return listOfSkillId;
	}

	public void setListOfSkillId(Set<Integer> listOfSkillId) {
		this.listOfSkillId = listOfSkillId;
	}

	public List<ViewSkillTO> getListOfEmployeeSkills() {
		return listOfEmployeeSkills;
	}

	public void setListOfEmployeeSkills(List<ViewSkillTO> listOfEmployeeSkills) {
		this.listOfEmployeeSkills = listOfEmployeeSkills;
	}

	public List<Date> getPeercommentsDate() {
		return peercommentsDate;
	}

	public void setPeercommentsDate(List<Date> peercommentsDate) {
		this.peercommentsDate = peercommentsDate;
	}

	public String getSelfComment() {
		return selfComment;
	}

	public void setSelfComment(String selfComment) {
		this.selfComment = selfComment;
	}

	public Date getSelfCommentTime() {
		return selfCommentTime;
	}

	public void setSelfCommentTime(Date selfCommentTime) {
		this.selfCommentTime = selfCommentTime;
	}

	public List<PeercommentPlusDateListTO> getPeercommentPlusDateList() {
		return peercommentPlusDateList;
	}

	public void setPeercommentPlusDateList(List<PeercommentPlusDateListTO> peercommentPlusDateList) {
		this.peercommentPlusDateList = peercommentPlusDateList;
	}

	
	
//	public String getPeerComment() {
//		return peerComment;
//	}
//
//	public void setPeerComment(String peerComment) {
//		this.peerComment = peerComment;
//	}
	
	
}
