package com.omniwyse.sms.utils;

import java.util.ArrayList;
import java.util.List;

import com.omniwyse.sms.models.Students;
import com.omniwyse.sms.models.Teachers;

public class MessagesDTO {
	private long id;
	private long teacherid;
	private long studentid;
	private ArrayList<Long> students;
	private ArrayList<Long> teachers;
	private String message;
	public long getStudentid() {
		return studentid;
	}
	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}
	public ArrayList<Long> getTeachers() {
		return teachers;
	}
	public void setTeachers(ArrayList<Long> teachers) {
		this.teachers = teachers;
	}
	
	public ArrayList<Long> getStudents() {
		return students;
	}
	public void setStudents(ArrayList<Long> students) {
		this.students = students;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(long teacherid) {
		this.teacherid = teacherid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}