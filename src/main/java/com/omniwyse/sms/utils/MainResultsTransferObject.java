package com.omniwyse.sms.utils;

import java.util.List;

import com.omniwyse.sms.models.Subjects;

public class MainResultsTransferObject {
	private List<ResultsTransferObject> resulttransfer;
	private List<Subjects> subjects;

	public List<ResultsTransferObject> getResulttransfer() {
		return resulttransfer;
	}

	public void setResulttransfer(List<ResultsTransferObject> resulttransfer) {
		this.resulttransfer = resulttransfer;
	}

	public List<Subjects> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subjects> subjects) {
		this.subjects = subjects;
	}

}
