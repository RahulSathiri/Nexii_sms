package com.omniwyse.sms.utils;

import java.sql.Time;

public class TeacherScheduleDTO {

	private Time periodfrom;
	private Time periodto;
	private String subjectname;
	private long gradeid;
	private String sectionname;

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

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
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

}
