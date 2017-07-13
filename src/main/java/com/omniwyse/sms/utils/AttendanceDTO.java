package com.omniwyse.sms.utils;
import java.sql.Date;

public class AttendanceDTO {

	private long id;
	private String showdate;
	private long day;
	private Date dateofattendance;
	private long attendancestatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShowdate() {
		return showdate;
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