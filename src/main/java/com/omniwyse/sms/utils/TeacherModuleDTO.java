package com.omniwyse.sms.utils;

public class TeacherModuleDTO {

	private long id;
	private String subjectname;
	private long gradeid;
	private String sectionname;
	private long gradenumber;
	
	
	public long getGradenumber() {
		return gradenumber;
	}
	public void setGradenumber(long gradenumber) {
		this.gradenumber = gradenumber;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSubjectname() {
		return subjectname;
	}
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}
	public long getGradeid() {
		return gradeid;
	}
	public void setGradeid(long gradeid) {
		this.gradeid = gradeid;
	}
	public String getSectionname() {
		return sectionname;
	}
	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}
	
	
}
