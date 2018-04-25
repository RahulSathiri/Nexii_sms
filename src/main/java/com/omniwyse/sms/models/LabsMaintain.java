package com.omniwyse.sms.models;

import javax.persistence.Table;

@Table(name = "labsmaintain")
public class LabsMaintain {
	private String roomid;
	private String labid;
	private String teacherid;

	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public String getLabid() {
		return labid;
	}

	public void setLabid(String labid) {
		this.labid = labid;
	}

	public String getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

}
