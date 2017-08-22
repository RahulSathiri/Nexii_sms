package com.omniwyse.sms.models;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "classroom_worksheets")
public class ClassroomWorksheets {

	private long id;
	private long worksheetsid;
	private Date dateofassigned;
	private Date worksheetduedate;
	private Long subjectid;
	private long classroomid;
	private Long lessonsid;
	private String publishworksheet;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getPublishworksheet() {
		return publishworksheet;
	}
	public void setPublishworksheet(String publishworksheet) {
		this.publishworksheet = publishworksheet;
	}
	
	public Date getDateofassigned() {
		return dateofassigned;
	}
	public void setDateofassigned(Date dateofassigned) {
		this.dateofassigned = dateofassigned;
	}
	
	public Long getLessonsid() {
		return lessonsid;
	}
	public void setLessonsid(Long lessonsid) {
		this.lessonsid = lessonsid;
	}
	public long getWorksheetsid() {
		return worksheetsid;
	}
	public void setWorksheetsid(long worksheetsid) {
		this.worksheetsid = worksheetsid;
	}
	
	public Date getWorksheetduedate() {
		return worksheetduedate;
	}
	public void setWorksheetduedate(Date worksheetduedate) {
		this.worksheetduedate = worksheetduedate;
	}
	public Long getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(Long subjectid) {
		this.subjectid = subjectid;
	}
	public long getClassroomid() {
		return classroomid;
	}
	public void setClassroomid(long classroomid) {
		this.classroomid = classroomid;
	}
	
}
