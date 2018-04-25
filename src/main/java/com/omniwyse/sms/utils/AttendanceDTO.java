package com.omniwyse.sms.utils;
import java.sql.Date;
import java.util.List;

public class AttendanceDTO {

	private long id;
	private String showdate;
	private long day;
	private Date dateofattendance;
	private long attendancestatus;
	private String subjectname;
	private List<AttendanceDTO> subjectattendance;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShowdate() {
		return showdate;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public List<AttendanceDTO> getSubjectattendance() {
		return subjectattendance;
	}

	public void setSubjectattendance(List<AttendanceDTO> subjectattendance) {
		this.subjectattendance = subjectattendance;
	}

	public void setShowdate(String showdate) {
		this.showdate = showdate;
	}

	public long getDay() {
		return day;
	}

	public void setDay(long day) {
		this.day = day;
	}

	public Date getDateofattendance() {
		return dateofattendance;
	}

	public void setDateofattendance(Date dateofattendance) {
		this.dateofattendance = dateofattendance;
	}

	public long getAttendancestatus() {
		return attendancestatus;
	}

	public void setAttendancestatus(long attendancestatus) {
		this.attendancestatus = attendancestatus;
	}

}