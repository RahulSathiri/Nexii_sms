package com.omniwyse.sms.utils;

public class ClassRoomStudents {
	private long id;
	private String name;
	private String fathername;
	private String admissionnumber;
	private long parentid;
	public long getParentid() {
		return parentid;
	}
	public void setParentid(long parentid) {
		this.parentid = parentid;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	private boolean status=true; 
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	public String getAdmissionnumber() {
		return admissionnumber;
	}
	public void setAdmissionnumber(String admissionnumber) {
		this.admissionnumber = admissionnumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
