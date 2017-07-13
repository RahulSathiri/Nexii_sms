package com.omniwyse.sms.utils;

public class ClassSectionTransferObject {

	private String sectionname;
	private long gradeid;
	private long id;
	private String syllabustype;
	private String teachername;
	private String subjectname;
	private long classteacherid;
	private long academicyear;
	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	private String gradename;
	public long getGradenumber() {
		return gradenumber;
	}

	public void setGradenumber(long gradenumber) {
		this.gradenumber = gradenumber;
	}

	private long syllabusid;
	private long gradenumber;
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

	public long getSyllabusid() {
		return syllabusid;
	}

	public void setSyllabusid(long syllabusid) {
		this.syllabusid = syllabusid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAcademicyear() {
		return academicyear;
	}

	public void setAcademicyear(long academicyear) {
		this.academicyear = academicyear;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public String getSyllabustype() {
		return syllabustype;
	}

	public void setSyllabustype(String syllabustype) {
		this.syllabustype = syllabustype;
	}

	public long getClassteacherid() {
		return classteacherid;
	}

	public void setClassteacherid(long classteacherid) {
		this.classteacherid = classteacherid;
	}

	

	public String getSectionname() {
		return sectionname;
	}

	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}

}