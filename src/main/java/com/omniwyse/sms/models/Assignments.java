package com.omniwyse.sms.models;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "assignments")
public class Assignments {

	private long id;
	private String assignmentname;
	private Date dateofassigned;
	private Date assignmentduedate;
	private long classroomid;
	private long subjectid;
	private long lessonsid;
	private String tags;
	private String publishassignment;

	
	public String getPublishassignment() {
		return publishassignment;
	}
	public void setPublishassignment(String publishassignment) {
		this.publishassignment = publishassignment;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
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
	public String getAssignmentname() {
		return assignmentname;
	}
	public void setAssignmentname(String assignmentname) {
		this.assignmentname = assignmentname;
	}
	public Date getDateofassigned() {
		return dateofassigned;
	}
	public void setDateofassigned(Date dateofassigned) {
		this.dateofassigned = dateofassigned;
	}
	public Date getAssignmentduedate() {
		return assignmentduedate;
	}
	public void setAssignmentduedate(Date assignmentduedate) {
		this.assignmentduedate = assignmentduedate;
	}
	public long getClassroomid() {
		return classroomid;
	}
	public void setClassroomid(long classroomid) {
		this.classroomid = classroomid;
	}
	public long getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(long subjectid) {
		this.subjectid = subjectid;
	}
	
	
}
