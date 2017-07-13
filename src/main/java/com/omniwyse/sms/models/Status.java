package com.omniwyse.sms.models;

import javax.persistence.Table;

@Table(name = "clientstatus")
public class Status {

	private String statusid;
	private String description;
	private String createdOn;
	private String modifiedOn;

	public String getStatusid() {
		return statusid;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
