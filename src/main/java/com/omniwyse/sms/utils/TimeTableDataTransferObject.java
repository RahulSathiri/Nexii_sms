package com.omniwyse.sms.utils;

import java.time.LocalTime;		

public class TimeTableDataTransferObject {

	private Long id;
	private Long dayid;
	private String day;
	private LocalTime periodfrom;
	private LocalTime periodto;
	private String subjectname;

	
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDayid() {
		return dayid;
	}

	public void setDayid(Long dayid) {
		this.dayid = dayid;
	}
	
	public LocalTime getPeriodfrom() {
		return periodfrom;
	}

	public void setPeriodfrom(LocalTime periodfrom) {
		this.periodfrom = periodfrom;
	}

	public LocalTime getPeriodto() {
		return periodto;
	}

	public void setPeriodto(LocalTime periodto) {
		this.periodto = periodto;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}
		
}
