package com.omniwyse.sms.utils;
import java.sql.Date;
import java.util.List;

public class ClassAttendenceTransferObject {

	private long id;
	private String name;
	private String syllabustype;
	private long academicyear;
	private Date dateofattendance;
	private long attendancestatus;
	private String today;
	private String showdate;
	private long days;
	private List<AttendanceDTO> studentattendance;

	public List<AttendanceDTO> getStudentattendance() {
		return studentattendance;
	}

	public void setStudentattendance(List<AttendanceDTO> studentattendance) {
		this.studentattendance = studentattendance;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShowdate() {
		return showdate;
	}

	public void setShowdate(String showdate) {
		this.showdate = showdate;
	}

	public long getDays() {
		return days;
	}

	public void setDays(long days) {
		this.days = days;
	}

	public long getAttendancestatus() {
		return attendancestatus;
	}

	public void setAttendancestatus(long attendancestatus) {
		this.attendancestatus = attendancestatus;
	}

	public String getSyllabustype() {
		return syllabustype;
	}

	public void setSyllabustype(String syllabustype) {
		this.syllabustype = syllabustype;
	}

	public long getAcademicyear() {
		return academicyear;
	}

	public void setAcademicyear(long academicyear) {
		this.academicyear = academicyear;
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