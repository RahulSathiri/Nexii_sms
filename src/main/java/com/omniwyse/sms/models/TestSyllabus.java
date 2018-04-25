package com.omniwyse.sms.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="test_syllabus")
public class TestSyllabus {
	private long id;
	private long testid;
	private long subjectid;
	public long getMaxmarks() {
		return maxmarks;
	}
	public void setMaxmarks(long maxmarks) {
		this.maxmarks = maxmarks;
	}
	private String syllabus;
	private long maxmarks;

	@Id
	@GeneratedValue
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
	public void setSubjectid(long subjectid) {
		this.subjectid = subjectid;
	}
	public String getSyllabus() {
		return syllabus;
	}
	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}
}
