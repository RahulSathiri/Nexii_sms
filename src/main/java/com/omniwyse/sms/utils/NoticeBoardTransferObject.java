package com.omniwyse.sms.utils;

import java.sql.Date;

public class NoticeBoardTransferObject {
	
	private String gradename;

	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public String getSyllabustype() {
		return syllabustype;
	}

	public void setSyllabustype(String syllabustype) {
		this.syllabustype = syllabustype;
	}

	public Date getNoticeddate() {
		return noticeddate;
	}

	public void setNoticeddate(Date noticeddate) {
		this.noticeddate = noticeddate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String syllabustype;
	private Date noticeddate;
	private String description;

}
