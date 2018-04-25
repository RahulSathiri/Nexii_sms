package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.Subjects;

@Service
public class SubjectService {
	@Autowired
    private com.omniwyse.sms.db.DatabaseRetrieval retrive;
	private Database db;
	private String subjectname;

	public int addSubject(long tenantId, Subjects subjects) {
		subjectname = subjects.getSubjectname();
		db = retrive.getDatabase(tenantId);
		if (isValidSubject(subjectname)) {
			return db.insert(subjects).getRowsAffected();
		} else
			return 0;

	}

	private boolean isValidSubject(String subjectname) {
		List<Subjects> subject = db.where("subjectname=?", subjectname).results(Subjects.class);
		if (subject.isEmpty())
			return true;
		else
			return false;
	}

}
