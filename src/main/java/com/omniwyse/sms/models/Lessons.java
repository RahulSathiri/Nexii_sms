package com.omniwyse.sms.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "lessons")
public class Lessons {
	
	private long id;
	private String lessonname;
	private long subjectid;
	private long classroomid;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
