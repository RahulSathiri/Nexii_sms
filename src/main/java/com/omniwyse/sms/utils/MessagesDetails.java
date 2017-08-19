package com.omniwyse.sms.utils;

import java.sql.Timestamp;
import java.util.List;

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
	private long classroomid;
	private Timestamp messagedate;
	private List<MessagesDetails> replymessages;

	public long getClassroomid() {
		return classroomid;
	}

	public void setClassroomid(long classroomid) {
		this.classroomid = classroomid;
	}

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

	public Timestamp getMessagedate() {
		return messagedate;
	}

	public void setMessagedate(Timestamp messagedate) {
		this.messagedate = messagedate;
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

	
}