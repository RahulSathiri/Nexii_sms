package com.omniwyse.sms.utils;

import java.util.List;

import com.omniwyse.sms.models.WeekDays;

public class TimeTableView {

	private List<TimeTableDataTransferObject> periodsandtime;
	private List<WeekDays> weekday;
	private List<TimeTableDataTransferObject> subjects;

	public List<TimeTableDataTransferObject> getPeriodsandtime() {
		return periodsandtime;
	}

	public void setPeriodsandtime(List<TimeTableDataTransferObject> periodsandtime) {
		this.periodsandtime = periodsandtime;
	}

	public List<WeekDays> getWeekday() {
		return weekday;
	}

	public void setWeekday(List<WeekDays> weekday) {
		this.weekday = weekday;
	}

	public List<TimeTableDataTransferObject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<TimeTableDataTransferObject> subjects) {
		this.subjects = subjects;
	}

}
