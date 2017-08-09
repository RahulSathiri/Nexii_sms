package com.omniwyse.sms.models;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="messages")
public class Messages {
	
	private long id; 
	private String message; 
	private Timestamp messagedate; 
	private long teacherid; 
	private long studentid; 
	 private String sentflag;  
	private long rootmessageid;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getMessagedate() {
		return messagedate;
	}
	public void setMessagedate(Timestamp messagedate) {
		this.messagedate = messagedate;
	}
	public long getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(long teacherid) {
		this.teacherid = teacherid;
	}
	
	public long getStudentid() {
		return studentid;
	}
	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}
	public String getSentflag() {
		return sentflag;
	}
	public void setSentflag(String sentflag) {
		this.sentflag = sentflag;
	}
	public long getRootmessageid() {
		return rootmessageid;
	}
	public void setRootmessageid(long rootmessageid) {
		this.rootmessageid = rootmessageid;
	}

}
