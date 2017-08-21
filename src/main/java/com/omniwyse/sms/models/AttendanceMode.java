package com.omniwyse.sms.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="school_attendance")
public class AttendanceMode {
	private long id;
	private String attendance_type;
	private long status;
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAttendance_type() {
		return attendance_type;
	}
	public void setAttendance_type(String attendance_type) {
		this.attendance_type = attendance_type;
	}
}
