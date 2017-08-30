package com.omniwyse.sms.utils;

public class ParentDTO {
	private long id;
	private String name;
	private String sectionname;
	private String gradename;
	private String syllabustype;
	private long classid;
	private long studentid;
	private long gradeid;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSectionname() {
		return sectionname;
	}
	public long getClassid() {
		return classid;
	}
	public long getStudentid() {
		return studentid;
	}
	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}
	public void setClassid(long classid) {
		this.classid = classid;
	}
	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}
	public long getGradeid() {
		return gradeid;
	}
	public void setGradeid(long gradeid) {
		this.gradeid = gradeid;
	}
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
	
	

}
