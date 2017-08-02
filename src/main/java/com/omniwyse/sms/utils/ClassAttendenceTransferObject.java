package com.omniwyse.sms.utils;
import java.util.Date;
import java.util.List;

import com.omniwyse.sms.models.ClassroomAttendance;
import com.omniwyse.sms.models.Students;

public class ClassAttendenceTransferObject {

	private long id;
	private String name;
	private Date dateofattendance;
	private long attendancestatus;
	private long gradeid;
	private String sectionname;
	private long noofstudents;
	private long noofpresents;
	private long noofabsents;
	private List<Students> students;
	private List<ClassroomAttendance> studentattendance;
	private List<ClassRoomStudents> studentsOfClassRoom;
	
	public long getNoofstudents() {
		return noofstudents;
	}

	public void setNoofstudents(long noofstudents) {
		this.noofstudents = noofstudents;
	}

	public long getNoofpresents() {
		return noofpresents;
	}

	public void setNoofpresents(long noofpresents) {
		this.noofpresents = noofpresents;
	}

	public long getNoofabsents() {
		return noofabsents;
	}

	public void setNoofabsents(long noofabsents) {
		this.noofabsents = noofabsents;
	}

	public List<Students> getStudents() {
		return students;
	}

	public void setStudents(List<Students> students) {
		this.students = students;
	}

	public long getGradeid() {
		return gradeid;
	}

	public void setGradeid(long gradeid) {
		this.gradeid = gradeid;
	}

	public String getSectionname() {
		return sectionname;
	}

	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}


	public List<ClassRoomStudents> getStudentsOfClassRoom() {
		return studentsOfClassRoom;
	}

	public void setStudentsOfClassRoom(List<ClassRoomStudents> studentsOfClassRoom) {
		this.studentsOfClassRoom = studentsOfClassRoom;
	}

	
	public List<ClassroomAttendance> getStudentattendance() {
		return studentattendance;
	}

	public void setStudentattendance(List<ClassroomAttendance> studentattendance) {
		this.studentattendance = studentattendance;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public long getAttendancestatus() {
		return attendancestatus;
	}

	public void setAttendancestatus(long attendancestatus) {
		this.attendancestatus = attendancestatus;
	}

	
	public long getId() {
		return id;
	}

	public Date getDateofattendance() {
		return dateofattendance;
	}

	public void setDateofattendance(Date dateofattendance) {
		this.dateofattendance = dateofattendance;
	}

	public void setId(long id) {
		this.id = id;
	}

}