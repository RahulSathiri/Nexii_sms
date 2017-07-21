package com.omniwyse.sms.utils;

public class WorkSheetsDTO {

	private long id;
	private String worksheetname;
	private String degreeofdifficulty;
	private long gradeid;
	private String subjectname;
	private String tags;
	private String description;
	private String worksheetpath;
	private byte[] worksheet;
	private String createdby;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWorksheetname() {
		return worksheetname;
	}

	public void setWorksheetname(String worksheetname) {
		this.worksheetname = worksheetname;
	}

	public String getDegreeofdifficulty() {
		return degreeofdifficulty;
	}

	public void setDegreeofdifficulty(String degreeofdifficulty) {
		this.degreeofdifficulty = degreeofdifficulty;
	}

	public long getGradeid() {
		return gradeid;
	}

	public void setGradeid(long gradeid) {
		this.gradeid = gradeid;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWorksheetpath() {
		return worksheetpath;
	}

	public void setWorksheetpath(String worksheetpath) {
		this.worksheetpath = worksheetpath;
	}

	
	public byte[] getWorksheet() {
		return worksheet;
	}

	public void setWorksheet(byte[] worksheet) {
		this.worksheet = worksheet;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

}
