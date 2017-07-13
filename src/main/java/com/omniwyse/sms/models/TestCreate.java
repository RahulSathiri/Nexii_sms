package com.omniwyse.sms.models;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "test_create")
public class TestCreate {
	private long id;
	private long gradeid;
	private long testtypeid;
	private Date startdate;
	private Date enddate;
	private long modeid;
	private long maxmarks;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGradeid() {
		return gradeid;
	}

	public void setGradeid(long gradeid) {
		this.gradeid = gradeid;
	}

	public long getTesttypeid() {
		return testtypeid;
	}

	public void setTesttypeid(long testtypeid) {
		this.testtypeid = testtypeid;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public long getModeid() {
		return modeid;
	}

	public void setModeid(long modeid) {
		this.modeid = modeid;
	}

	public long getMaxmarks() {
		return maxmarks;
	}

	public void setMaxmarks(long maxmarks) {
		this.maxmarks = maxmarks;
	}
}
