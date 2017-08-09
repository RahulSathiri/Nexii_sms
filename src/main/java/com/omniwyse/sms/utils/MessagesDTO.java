package com.omniwyse.sms.utils;

import java.util.ArrayList;

public class MessagesDTO {
	private long id;
	private long senderid;
	private long recieverid;
	private ArrayList<Long> recievers;
	private String message;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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