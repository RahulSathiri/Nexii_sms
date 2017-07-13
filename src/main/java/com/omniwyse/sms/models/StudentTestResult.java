package com.omniwyse.sms.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="student_testresult")
public class StudentTestResult {
	private long id;
	  private long testid;
	  private long studentid;
	  private long subjectid;
	  private long classid;
	  private long marks;
	  
	  public long getClassid() {
		return classid;
	}
	public void setClassid(long classid) {
		this.classid = classid;
	}
	
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
	public long getStudentid() {
		return studentid;
	}
	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}
	public long getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(long subjectid) {
		this.subjectid = subjectid;
	}
	public long getMarks() {
		return marks;
	}
	public void setMarks(long marks) {
		this.marks = marks;
	}
}
