package com.omniwyse.sms.models;

import javax.persistence.Table;

@Table(name = "degreeofdifficulty")
public class Degreeofdifficulty {

	private long id;
	private String degreeofdifficulty;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDegreeofdifficulty() {
		return degreeofdifficulty;
	}

	public void setDegreeofdifficulty(String degreeofdifficulty) {
		this.degreeofdifficulty = degreeofdifficulty;
	}


}
