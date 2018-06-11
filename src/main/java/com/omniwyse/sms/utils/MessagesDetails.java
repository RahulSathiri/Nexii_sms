package com.omniwyse.sms.utils;

import java.sql.Timestamp;
import java.util.List;

public class MessagesDetails {
	private String message;
	private long senderid;
	private long recieverid;
	private String name;
	private long id;
	private String sentflag;
	private String sendername;
	private String recievername;
	private String fathername;
	private String mothername;
	private String studentname;
	private String teachername;
	private long classroomid;
	private Timestamp messagedate;
	private String dateofmessage=new String();
	private List<MessagesDetails> replymessages;

	public String getDateofmessage() {
		return dateofmessage;
	}

	
	public String getRecievername() {
		return recievername;
	}

	public void setRecievername(String recievername) {
		this.recievername = recievername;
	}

	public void setDateofmessage(String dateofmessage) {
		this.dateofmessage = dateofmessage;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getSentflag() {
		return sentflag;
	}

	public void setSentflag(String sentflag) {
		this.sentflag = sentflag;
	}

	public String getSendername() {
		return sendername;
	}

	public void setSendername(String sendername) {
		this.sendername = sendername;
	}

	public long getClassroomid() {
		return classroomid;
	}

	public void setClassroomid(long classroomid) {
		this.classroomid = classroomid;
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

	@SuppressWarnings("deprecation")
	public void setMessagedate(Timestamp messagedate) {
		this.messagedate = messagedate;
		dateofmessage=messagedate.toLocaleString();
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