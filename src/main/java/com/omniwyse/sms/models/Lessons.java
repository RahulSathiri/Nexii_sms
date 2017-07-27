package com.omniwyse.sms.models;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "lessons")
public class Lessons {
	
	private long id;
	private String lessonname;
	private String tags;
	private long subjectid;
	private long classroomid;
	private Date lessonstartdate;
	private String status;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getLessonname() {
		return lessonname;
	}
	public void setLessonname(String lessonname) {
		this.lessonname = lessonname;
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
