package com.omniwyse.sms.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name ="timeline")
public class Timeline {

	private long id;
	private long lessonsid;
	private long assignmentsid;
	private long worksheetsid;
	private long teacherid;
	
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getLessonsid() {
		return lessonsid;
	}
	public void setLessonsid(long lessonsid) {
		this.lessonsid = lessonsid;
	}
	public long getAssignmentsid() {
		return assignmentsid;
	}
	public void setAssignmentsid(long assignmentsid) {
		this.assignmentsid = assignmentsid;
	}
	public long getWorksheetsid() {
		return worksheetsid;
	}
	public void setWorksheetsid(long worksheetsid) {
		this.worksheetsid = worksheetsid;
	}
	public long getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(long teacherid) {
		this.teacherid = teacherid;
	}
	
	
}
