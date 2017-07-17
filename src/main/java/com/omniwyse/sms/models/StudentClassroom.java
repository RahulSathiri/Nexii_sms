package com.omniwyse.sms.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="classroom_students")
public class StudentClassroom {
	private long id;
	private long studentid;
	private long classid;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStudentid() {
		return studentid;
	}
	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}
	public long getClassid() {
		return classid;
	}
	public void setClassid(long classid) {
		this.classid = classid;
	}
	

}
