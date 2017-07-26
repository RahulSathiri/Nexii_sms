package com.omniwyse.sms.utils;

import java.sql.Date;
import java.util.List;

public class TestTransferObject {

	private String testtype;
	private long id;
	private long academicyear;

	public long getAcademicyear() {
		return academicyear;
	}

	public void setAcademicyear(long academicyear) {
		this.academicyear = academicyear;
	}

	List<TestSubjectsDisplay> subjects;

	private Date startdate;
	private Date enddate;
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	private String testmode;
	private long maxmarks;

	

	public long getId() {
		return id;
	}

	public String getTestmode() {
		return testmode;
	}

	public String getTesttype() {
		return testtype;
	}

	public void setTesttype(String testtype) {
		this.testtype = testtype;
	}

	public void setTestmode(String testmode) {
		this.testmode = testmode;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public long getMaxmarks() {
		return maxmarks;
	}

	public void setMaxmarks(long maxmarks) {
		this.maxmarks = maxmarks;
	}

	public List<TestSubjectsDisplay> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<TestSubjectsDisplay> subjects) {
		this.subjects = subjects;
	}
	
	

//	@Override
//	public String toString() {
//		return "TestTransferObject [testtype=" + testtype + ", id=" + id + ", startdate=" + startdate + ", enddate="
//				+ enddate + ", testmode=" + testmode + ", maxmarks=" + maxmarks + "]";
//	}
	
	

}
