package com.omniwyse.sms.utils;

import java.sql.Timestamp;
import java.util.List;

import com.omniwyse.sms.models.Students;
import com.omniwyse.sms.models.Teachers;

public class MessagesDetails {
	private String message;
	private long senderid;
	private long recieverid;
	private String name;
	private long id;
	private long rootmessageid;
	private String fathername;
	private String mothername;
	private String teachername;
	private Timestamp messagedate;
	private List<Students> students;
	private List<Teachers> teachers;
	private List<MessagesDetails> replymessages;
	public long getRootmessageid() {
		return rootmessageid;
	}
	public void setRootmessageid(long rootmessageid) {
		this.rootmessageid = rootmessageid;
	}
	

	public List<MessagesDetails> getReplymessages() {
		return replymessages;
	}
	public void setReplymessages(List<MessagesDetails> replymessages) {
		this.replymessages = replymessages;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
		public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
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
	public String getMothername() {
		return mothername;
	}
	public void setMothername(String mothername) {
		this.mothername = mothername;
	}
	
	public List<Teachers> getTeachers() {
		return teachers;
	}
	
	public Timestamp getMessagedate() {
		return messagedate;
	}
	public void setMessagedate(Timestamp messagedate) {
		this.messagedate = messagedate;
	}
	public void setTeachers(List<Teachers> teachers) {
		this.teachers = teachers;
	}
		
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	public List<Students> getStudents() {
		return students;
	}
	public void setStudents(List<Students> students) {
		this.students = students;
	}
	

}