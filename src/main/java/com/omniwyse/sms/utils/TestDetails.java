package com.omniwyse.sms.utils;

import java.util.List;

public class TestDetails {
	private List<TestSubjectsDisplay> testsubjectsdetails;
	private List<TestTransferObject> testdetails;
	
	public List<TestSubjectsDisplay> getTestsubjectsdetails() {
		return testsubjectsdetails;
	}
	public void setTestsubjectsdetails(List<TestSubjectsDisplay> testsubjectsdetails) {
		this.testsubjectsdetails = testsubjectsdetails;
	}
	public List<TestTransferObject> getTestdetails() {
		return testdetails;
	}
	public void setTestdetails(List<TestTransferObject> testdetails) {
		this.testdetails = testdetails;
	}

}
