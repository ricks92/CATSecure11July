package com.hsc.cat.TO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PeercommentPlusDateListTO {

	private String comment;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Kolkata")
	private Date timestamp;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
