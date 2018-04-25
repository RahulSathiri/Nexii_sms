package com.omniwyse.sms.utils;

public class TestSubjectsDisplay {
	private long id;
	private long testid;
	private long maxmarks;
	private long marks;
	public long getMaxmarks() {
		return maxmarks;
	}
	public void setMaxmarks(long maxmarks) {
		this.maxmarks = maxmarks;
	}
	public String getSyllabus() {
		return syllabus;
	}
	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}
	private String syllabus;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTestid() {
		return testid;
	}
	public void setTestid(long testid) {
		this.testid = testid;
	}
	public long getSubjectid() {
		return subjectid;
	}
	public long getMarks() {
		return marks;
	}
	public void setMarks(long marks) {
		this.marks = marks;
	}
	public void setSubjectid(long subjectid) {
		this.subjectid = subjectid;
	}
	public String getSubjectname() {
		return subjectname;
	}
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}
	private long subjectid;
	private String subjectname;
	

}
