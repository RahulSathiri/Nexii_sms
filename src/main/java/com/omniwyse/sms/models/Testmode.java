package com.omniwyse.sms.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="test_mode")
public class Testmode {
private long id;
private String testmode;
@Id
@GeneratedValue
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getTestmode() {
	return testmode;
}
public void setTestmode(String testmode) {
	this.testmode = testmode;
}
}
