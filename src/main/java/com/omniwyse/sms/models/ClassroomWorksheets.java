package com.omniwyse.sms.models;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "classroom_worksheets")
public class ClassroomWorksheets {

	private long id;
	private long worksheetid;
	private Date dateofassigned;
	private Date duedate;
	private long subjectid;
	private long classroomid;
	private long teacherid;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getWorksheetid() {
		return worksheetid;
	}
	public void setWorksheetid(long worksheetid) {
		this.worksheetid = worksheetid;
	}
	public Date getDateofassigned() {
		return dateofassigned;
	}
	public void setDateofassigned(Date dateofassigned) {
		this.dateofassigned = dateofassigned;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public long getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(long subjectid) {
		this.subjectid = subjectid;
	}
	public long getClassroomid() {
		return classroomid;
	}
	public void setClassroomid(long classroomid) {
		this.classroomid = classroomid;
	}
	public long getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(long teacherid) {
		this.teacherid = teacherid;
	}
	
}
