package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.GradeSubjects;
import com.omniwyse.sms.models.TestCreate;
import com.omniwyse.sms.models.TestSyllabus;
import com.omniwyse.sms.models.TestType;
import com.omniwyse.sms.models.Testmode;
import com.omniwyse.sms.utils.TestSubjectsDisplay;
import com.omniwyse.sms.utils.TestTransferObject;

@Service
public class TestService {

	@Autowired
    private com.omniwyse.sms.db.DatabaseRetrieval retrieve;

	private Database db;

	public int addTestType(long tenantId, TestType testtype) {
		String testtypename = testtype.getTesttype();
		db = retrieve.getDatabase(tenantId);

		List<TestType> records = db.where("testtype=?", testtypename).results(TestType.class);
		if (records.isEmpty()) {
			return db.insert(testtype).getRowsAffected();
		}
		return 0;
	}

	public int createTest(long tenantId, TestTransferObject testTransferObject) {
		TestCreate testcreate;
		TestSyllabus testsyllubus;

		String testtypename = testTransferObject.getTesttype();
		String testmode = testTransferObject.getTestmode();
		db = retrieve.getDatabase(tenantId);
		long gradeid = testTransferObject.getId();
		long academicyear = testTransferObject.getAcademicyear();
		long testtypeid = db.where("testtype=?", testtypename).results(TestType.class).get(0).getId();
		List<TestCreate> records = db
				.where("academicyear=? and gradeid=? and testtypeid=?", academicyear, gradeid, testtypeid)
				.results(TestCreate.class);
		if (records.isEmpty()) {
			long testmodeid = db.where("testmode=?", testmode).results(Testmode.class).get(0).getId();
			testcreate = new TestCreate();
			testcreate.setGradeid(gradeid);
			testcreate.setModeid(testmodeid);
			testcreate.setTesttypeid(testtypeid);
			testcreate.setStartdate(testTransferObject.getStartdate());
			testcreate.setEnddate(testTransferObject.getEnddate());
			testcreate.setMaxmarks(testTransferObject.getMaxmarks());
			testcreate.setAcademicyear(academicyear);
			db.insert(testcreate).getRowsAffected();
			long testid = testcreate.getId();
			testsyllubus = new TestSyllabus();
			testsyllubus.setTestid(testid);
			List<GradeSubjects> subjectid = db
					.sql("select grade_subjects.subjectid from grade_subjects inner join subjects on grade_subjects.gradeid=? and grade_subjects.subjectid=subjects.id and subjects.istestable='true'",
							gradeid)
					.results(GradeSubjects.class);
			for (GradeSubjects gradesubject : subjectid) {
				testsyllubus.setSubjectid(gradesubject.getSubjectid());
				db.insert(testsyllubus);
			}
			return 1;

		} else
			return 0;

	}

	public List<TestType> listtesttypes(long tenantId) {
		db = retrieve.getDatabase(tenantId);
		return db.sql("select * from test_type ").results(TestType.class);
	}

	public List<Testmode> listtestmodes(long tenantId) {
		db = retrieve.getDatabase(tenantId);
		return db.sql("select * from testmode_type ").results(Testmode.class);
	}

	public List<TestTransferObject> listAllTests(long tenantId) {

		db = retrieve.getDatabase(tenantId);

		return db.sql("select test_type.testtype, test_create.startdate, test_create.enddate, test_mode.testmode, test_create.maxmarks from test_type JOIN test_create ON test_type.id = test_create.testtypeid"
		        + " JOIN test_mode ON test_mode.id = test_create.modeid").results(TestTransferObject.class);
	}

	public List<TestTransferObject> getListOfTests(long tenantId, TestTransferObject testtransferobject) {

		db = retrieve.getDatabase(tenantId);
		long gradeid = testtransferobject.getId();

		List<TestTransferObject> testsdetails = db.sql("select test_type.id,test_type.testtype,test_mode.testmode,test_create.startdate,test_create.enddate,test_create.maxmarks from test_create"
		        + " inner join test_mode on test_create.gradeid=? and  test_create.modeid=test_mode.id inner join test_type on test_create.testtypeid=test_type.id",gradeid).results(TestTransferObject.class);

		long testid;
		for (TestTransferObject test : testsdetails) {
			testid = test.getId();

			List<TestSubjectsDisplay> subjecttestsyllabus = db.sql("select test_syllabus.id,test_syllabus.maxmarks,test_syllabus.testid,test_syllabus.subjectid,test_syllabus.syllabus,subjects.subjectname"
			        + " from test_syllabus  inner join subjects on test_syllabus.testid=? and test_syllabus.subjectid=subjects.id",testid).results(TestSubjectsDisplay.class);
			
			test.setSubjects(subjecttestsyllabus);
		}
		return testsdetails;

	}
	

	public List<TestSubjectsDisplay> getListOfTestSubjects(long tenantId, TestSubjectsDisplay testsubjectsdisplay) {
		long testid = testsubjectsdisplay.getId();
		db = retrieve.getDatabase(tenantId);

		return db
				.sql("select test_syllabus.id,test_syllabus.testid,test_syllabus.subjectid,test_syllabus.syllabus,subjects.subjectname from test_syllabus  inner join subjects on test_syllabus.testid=? and test_syllabus.subjectid=subjects.id",
						testid)
				.results(TestSubjectsDisplay.class);
	}

    public int addorEditSyllabus(long tenantId, TestSyllabus testsyllabus) {
		db = retrieve.getDatabase(tenantId);

        long testid = testsyllabus.getTestid();
        long subjectid = testsyllabus.getSubjectid();

        TestSyllabus testSyllabus2 = db.where("testid=? and subjectid=?", testid, subjectid).results(TestSyllabus.class).get(0);
        String syllabus = testSyllabus2.getSyllabus();
        syllabus = syllabus + testsyllabus.getSyllabus();
        testSyllabus2.setSyllabus(syllabus);

		return db.update(testsyllabus).getRowsAffected();
	}

	
}
