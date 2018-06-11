package com.omniwyse.sms.models;







import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="attendance_subjectwise")
public class AttendanceSubjectwise {
	private long id;
	private long studentid;
	private long classroomid;
	private Date dateofattendance;
	private long attendancestatus;
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
	public long getClassroomid() {
		return classroomid;
	}
	public void setClassroomid(long classroomid) {
		this.classroomid = classroomid;
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
	public long getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(long subjectid) {
		this.subjectid = subjectid;
	}
	private long subjectid;
}
