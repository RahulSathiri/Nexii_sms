package com.omniwyse.sms.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "classroom_weekdays")
public class ClassRoomWeekDays {
	
	private long id;
	private long classroomid;
	private long weekdayid;

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

	public long getWeekdayid() {
		return weekdayid;
	}

	public void setWeekdayid(long weekdayid) {
		this.weekdayid = weekdayid;
	}

	

}
