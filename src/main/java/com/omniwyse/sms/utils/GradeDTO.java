package com.omniwyse.sms.utils;

import java.util.ArrayList;

public class GradeDTO {

	private long gradenumber;
	private String gradename;
	private String syllabustype;
	private ArrayList<String> subjects;

	public String getSyllabustype() {
		return syllabustype;
	}

	public void setSyllabustype(String syllabustype) {
		this.syllabustype = syllabustype;
	}

	public long getGradenumber() {
		return gradenumber;
	}

	public void setGradenumber(long gradenumber) {
		this.gradenumber = gradenumber;
	}

	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public ArrayList<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(ArrayList<String> subjects) {
		this.subjects = subjects;
	}

	
}
