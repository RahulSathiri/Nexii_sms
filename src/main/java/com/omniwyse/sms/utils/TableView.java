package com.omniwyse.sms.utils;

import java.sql.Date;
import java.sql.Time;

public class TableView {

	private String subjectname;
	private String day;
	private Time periodfrom;
	private Time periodto;
	private Date dateofassigning;
	
	public Date getDateofassigning() {
		return dateofassigning;
	}

	public void setDateofassigning(Date dateofassigning) {
		this.dateofassigning = dateofassigning;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
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