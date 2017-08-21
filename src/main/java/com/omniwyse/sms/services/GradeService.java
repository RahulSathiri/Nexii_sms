package com.omniwyse.sms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.GradeSubjects;
import com.omniwyse.sms.models.Grades;
import com.omniwyse.sms.models.Subjects;
import com.omniwyse.sms.models.Syllabus;
import com.omniwyse.sms.utils.GradeDTO;

@Service
public class GradeService {

	@Autowired
    private com.omniwyse.sms.db.DatabaseRetrieval database;
	@Autowired
	private GradeSubjects gradesubjects;

	public Database db;

	public int addGrade( long tenantId, GradeDTO addgrade) {
		Grades grade = new Grades();
		db = database.getDatabase(tenantId);
		long gradeNumber = addgrade.getGradenumber();
		grade.setGradenumber(gradeNumber);
		grade.setGradename(addgrade.getGradename());
		ArrayList<String> subjects = addgrade.getSubjects();
		String syllabus = addgrade.getSyllabustype();

		grade.setSyllabustype(syllabus);

		grade.setSubjects(subjects.toString());
		List<Syllabus> record = db.where("syllabustype=?", syllabus).results(Syllabus.class);
		if (record.isEmpty()) {
			return -1;
		}
		if (isValidGrade(gradeNumber, syllabus)) {
			db.insert(grade);
			gradesubjects.setGradeid(grade.getId());

			for (String subject : subjects) {
				long subjectid = db.where("subjectname=?", subject).results(Subjects.class).get(0).getId();
				gradesubjects.setSubjectid(subjectid);
				db.insert(gradesubjects);
			}
			return 1;
		}
		return 0;
	}

	private boolean isValidGrade(long gradeNumber, String syllabus) {

		List<Grades> list = db.where("gradenumber = ? and syllabustype = ?", gradeNumber, syllabus)
				.results(Grades.class);
		if (list.isEmpty()) {
			return true;
		}
		return false;
	}

	public List<Grades> listAllGrades( long tenantId) {

		db = database.getDatabase(tenantId);
		return db.sql("select * from grades").results(Grades.class);
	}

	public List<Grades> getListOfGrades( long tenantId, String syllabustype) {
		db = database.getDatabase(tenantId);
		return db.where("syllabustype=?", syllabustype).results(Grades.class);

	}

	public List<Grades> listDistinctGrades( long tenantId) {
		db = database.getDatabase(tenantId);
		return db.sql("select distinct gradename from grades").results(Grades.class);

	}

}
