package com.omniwyse.sms.models;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "messages")
public class Messages {

    private long id;
    private String message;
    private Timestamp messagedate;
    private long classroomid;
    private long senderid;
    private long recieverid;
    private String sentflag;
    private long rootmessageid;
    private long parentmessageid;
    private boolean isreply;

    public boolean isIsreply() {
        return isreply;
    }

    public void setIsreply(boolean isreply) {
        this.isreply = isreply;
    }

    public long getParentmessageid() {
        return parentmessageid;
    }

    public void setParentmessageid(long parentmessageid) {
        this.parentmessageid = parentmessageid;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClassroomid() {
        return classroomid;
    }

    public void setClassroomid(long classroomid) {
        this.classroomid = classroomid;
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
