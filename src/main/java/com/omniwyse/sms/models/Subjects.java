package com.omniwyse.sms.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "subjects")
public class Subjects  {

	private long id;
	private String subjectname;
	private String istestable;

	public String getIstestable() {
		return istestable;
	}

	public void setIstestable(String istestable) {
		this.istestable = istestable;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

}
