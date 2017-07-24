package com.omniwyse.sms.utils;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="academicyears")
public class AcademicYearsDTO {
	private long id;
	private Date passingyear;
	private Date academicyearstarting;
	private Date academicyearending;
	private long active;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getPassingyear() {
		return passingyear;
	}
	public void setPassingyear(Date passingyear) {
		this.passingyear = passingyear;
	}
	public Date getAcademicyearstarting() {
		return academicyearstarting;
	}
	public void setAcademicyearstarting(Date academicyearstarting) {
		this.academicyearstarting = academicyearstarting;
	}
	public Date getAcademicyearending() {
		return academicyearending;
	}
	public void setAcademicyearending(Date academicyearending) {
		this.academicyearending = academicyearending;
	}
	public long getActive() {
		return active;
	}
	public void setActive(long active) {
		this.active = active;
	}

}
