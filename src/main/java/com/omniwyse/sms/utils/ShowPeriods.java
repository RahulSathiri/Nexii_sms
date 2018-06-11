package com.omniwyse.sms.utils;

import java.sql.Date;
import java.sql.Time;

import org.springframework.web.bind.annotation.GetMapping;

public class ShowPeriods {

	private long id;
	private Time periodfrom;
	private Time periodto;
	private long subjectid;
	private Long classroomid;
	private Long classroomweekdayid;
	
	
	public Long getClassroomid() {
		return classroomid;
	}
	public void setClassroomid(Long classroomid) {
		this.classroomid = classroomid;
	}
	public Long getClassroomweekdayid() {
		return classroomweekdayid;
	}
	public void setClassroomweekdayid(Long classroomweekdayid) {
		this.classroomweekdayid = classroomweekdayid;
	}
	public long getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(long subjectid) {
		this.subjectid = subjectid;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Time getPeriodfrom() {
		return periodfrom;
	}
	public void setPeriodfrom(Time periodfrom) {
		this.periodfrom = periodfrom;
	}
	public Time getPeriodto() {
		return periodto;
	}
	public void setPeriodto(Time periodto) {
		this.periodto = periodto;
	}
	
	
	
	
	
}
