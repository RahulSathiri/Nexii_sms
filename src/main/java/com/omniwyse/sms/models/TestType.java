package com.omniwyse.sms.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="test_type")
public class TestType {
private long id;
private String testtype;

@Id
@GeneratedValue
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getTesttype() {
	return testtype;
}
public void setTesttype(String testtype) {
	this.testtype = testtype;
}

}
