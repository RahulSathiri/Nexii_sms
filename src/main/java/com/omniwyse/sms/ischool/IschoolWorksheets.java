package com.omniwyse.sms.ischool;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ischool_worksheets")
public class IschoolWorksheets {

	private Long id;
	private String worksheetname;
	private long degreeofdifficultyid;
	private long gradeid;
	private long subjectid;
	private String tags;
	private String worksheetpath;
	private String createdby;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorksheetname() {
		return worksheetname;
	}

	public void setWorksheetname(String worksheetname) {
		this.worksheetname = worksheetname;
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

	public String getWorksheetpath() {
		return worksheetpath;
	}

	public void setWorksheetpath(String worksheetpath) {
		this.worksheetpath = worksheetpath;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

}
