package com.omniwyse.sms.models;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "events")
public class Events {
	private long id;
	private Date eventdate;
	private String eventname;
	private String chiefguest;
	private String description;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getEventdate() {
		return eventdate;
	}

	public void setEventdate(Date eventdate) {
		this.eventdate = eventdate;
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public String getChiefguest() {
		return chiefguest;
	}

	public void setChiefguest(String chiefguest) {
		this.chiefguest = chiefguest;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
