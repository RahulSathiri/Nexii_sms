package com.omniwyse.sms.models;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "noticeboard")
public class NoticeBoard {
	private long id;
	private long noticeid;
	private Date noticeddate;
	private String description;

	public long getNoticeid() {
		return noticeid;
	}

	public void setNoticeid(long noticeid) {
		this.noticeid = noticeid;
	}


	public Date getNoticeddate() {
		return noticeddate;
	}

	public void setNoticeddate(Date noticeddate) {
		this.noticeddate = noticeddate;
	}

	public String getDescription() {
		return description;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
