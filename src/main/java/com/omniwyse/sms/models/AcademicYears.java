package com.omniwyse.sms.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="academicyears")
public class AcademicYears {
	private long id;
	private long academicyear;
	public long getAcademicyear() {
		return academicyear;
	}
	public void setAcademicyear(long academicyear) {
		this.academicyear = academicyear;
	}
	private String yearfromto;
	private long active;
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getYearfromto() {
		return yearfromto;
	}
	public void setYearfromto(String yearfromto) {
		this.yearfromto = yearfromto;
	}
	public long getActive() {
		return active;
	}
	public void setActive(long active) {
		this.active = active;
	}

}
