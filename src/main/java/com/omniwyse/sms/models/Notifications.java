package com.omniwyse.sms.models;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "notifications")
public class Notifications {

	private long id;
	private String notificationname;
	private String description;
	private Date notificationdate;
	private String publishedby;
	private String actioncode;
	private String parentactionrequired;
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

	public String getNotificationname() {
		return notificationname;
	}

	public void setNotificationname(String notificationname) {
		this.notificationname = notificationname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getNotificationdate() {
		return notificationdate;
	}

	public void setNotificationdate(Date notificationdate) {
		this.notificationdate = notificationdate;
	}

	public String getPublishedby() {
		return publishedby;
	}

	public void setPublishedby(String publishedby) {
		this.publishedby = publishedby;
	}

	public String getActioncode() {
		return actioncode;
	}

	public void setActioncode(String actioncode) {
		this.actioncode = actioncode;
	}

	public String getParentactionrequired() {
		return parentactionrequired;
	}

	public void setParentactionrequired(String parentactionrequired) {
		this.parentactionrequired = parentactionrequired;
	}

}
