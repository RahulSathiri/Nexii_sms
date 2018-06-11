package com.omniwyse.sms.utils;

import java.util.ArrayList;

public class MessagesDTO {
	private long id;
	private long senderid;
	private long recieverid;
	private long parentmessageid;
	private long rootmessageid;
	private long classroomid;
	private ArrayList<Long> recievers;
	private String typeofmessage;
	public String getTypeofmessage() {
		return typeofmessage;
	}
	public void setTypeofmessage(String typeofmessage) {
		this.typeofmessage = typeofmessage;
	}
	private String message;
	
	public long getRootmessageid() {
		return rootmessageid;
	}
	public void setRootmessageid(long rootmessageid) {
		this.rootmessageid = rootmessageid;
	}
	public long getId() {
		return id;
	}
	public long getClassroomid() {
		return classroomid;
	}
	public void setClassroomid(long classroomid) {
		this.classroomid = classroomid;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getParentmessageid() {
		return parentmessageid;
	}
	public void setParentmessageid(long parentmessageid) {
		this.parentmessageid = parentmessageid;
	}
	public long getSenderid() {
		return senderid;
	}
	public void setSenderid(long senderid) {
		this.senderid = senderid;
	}
	public long getRecieverid() {
		return recieverid;
	}
	public void setRecieverid(long recieverid) {
		this.recieverid = recieverid;
	}
	public ArrayList<Long> getRecievers() {
		return recievers;
	}
	public void setRecievers(ArrayList<Long> recievers) {
		this.recievers = recievers;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}