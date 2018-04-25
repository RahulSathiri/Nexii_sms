package com.omniwyse.sms.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "worksheets")
public class Worksheets {

	private long id;
	private String worksheetname;
	private long degreeofdifficultyid;
	private long gradeid;
	private long subjectid;
	private String tags;
	private String description;
	private String worksheetpath;
	private byte[] worksheet;
	private String createdby;
	

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public long getDegreeofdifficultyid() {
		return degreeofdifficultyid;
	}

	public void setDegreeofdifficultyid(long degreeofdifficultyid) {
		this.degreeofdifficultyid = degreeofdifficultyid;
	}

	public long getGradeid() {
		return gradeid;
	}

	public void setGradeid(long gradeid) {
		this.gradeid = gradeid;
	}

	public long getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(long subjectid) {
		this.subjectid = subjectid;
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

	public String getWorksheetname() {
		return worksheetname;
	}

	public void setWorksheetname(String worksheetname) {
		this.worksheetname = worksheetname;
	}

	public byte[] getWorksheet() {
		return worksheet;
	}

	public void setWorksheet(byte[] worksheet) {
		this.worksheet = worksheet;
	}


}
