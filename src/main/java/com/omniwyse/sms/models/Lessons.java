package com.omniwyse.sms.models;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "lessons")
public class Lessons {
	
	private long id;
	private String lessondescription;
	private String lessonname;
	private long subjectid;
	private long classroomid;
	private Date lessonstartdate;
	private String status;
	private String publishtimeline;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getPublishtimeline() {
		return publishtimeline;
	}
	public void setPublishtimeline(String publishtimeline) {
		this.publishtimeline = publishtimeline;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getLessonstartdate() {
		return lessonstartdate;
	}
	public void setLessonstartdate(Date lessonstartdate) {
		this.lessonstartdate = lessonstartdate;
	}
	public String getLessonname() {
		return lessonname;
	}
	public void setLessonname(String lessonname) {
		this.lessonname = lessonname;
	}
	public String getLessondescription() {
		return lessondescription;
	}
	public void setLessondescription(String lessondescription) {
		this.lessondescription = lessondescription;
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
	

}
