package com.omniwyse.sms.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sections")
public class Sections {

	private long id;
	private long classid;
	private String sectionname;

	private long classteacherid;

	public long getClassteacherid() {
		return classteacherid;
	}

	public void setClassteacherid(long classteacherid) {
		this.classteacherid = classteacherid;
	}

	public String getSectionname() {
		return sectionname;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getClassid() {
		return classid;
	}

	public void setClassid(long classid) {
		this.classid = classid;
	}

	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}

}
