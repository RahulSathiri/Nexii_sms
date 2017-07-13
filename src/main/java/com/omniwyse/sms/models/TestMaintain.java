package com.omniwyse.sms.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "testsmaintain")
public class TestMaintain {
	private String testname;

	private long id;
	private long roomid;
	private long teacherid;
	private long testid;

	public String getTestname() {
		return testname;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public long getRoomid() {
		return roomid;
	}

	public long getTeacherid() {
		return teacherid;
	}

	public long getTestid() {
		return testid;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setRoomid(long roomid) {
		this.roomid = roomid;
	}

	public void setTeacherid(long teacherid) {
		this.teacherid = teacherid;
	}

	public void setTestid(long testid) {
		this.testid = testid;
	}

	public void setTestname(String testname) {
		this.testname = testname;
	}

}
