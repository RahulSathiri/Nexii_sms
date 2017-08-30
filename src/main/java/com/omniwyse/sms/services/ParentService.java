package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.AttendanceMode;
import com.omniwyse.sms.models.Notifications;
import com.omniwyse.sms.utils.AttendanceDTO;
import com.omniwyse.sms.utils.ParentDTO;
import com.omniwyse.sms.utils.TestSubjectsDisplay;
import com.omniwyse.sms.utils.TestTransferObject;

@Service
public class ParentService {
	@Autowired
	private DatabaseRetrieval retrieve;

	private Database db;

	public List<ParentDTO> listParentChildrens(long parentId, long tenantId) {
		db = retrieve.getDatabase(tenantId);
		return db.sql(
				"select students.id,students.name,classrooms.sectionname,classrooms.gradeid,grades.gradename,grades.syllabustype,classroom_students.classid,classroom_students.studentid "
						+ "from parents join students on parents.id=students.parentid join classroom_students on classroom_students.studentid=students.id "
						+ "join classrooms on classrooms.id=classroom_students.classid join grades on grades.id=classrooms.gradeid where parents.id=?",
				parentId).results(ParentDTO.class);
	}

	public List<TestTransferObject> studentMarks(long studentid, long gradeid, long classid, long tenantId) {
		db = retrieve.getDatabase(tenantId);
		List<TestTransferObject> tests = db.sql(
				"select test_type.testtype,test_mode.testmode,test_create.id,test_create.startdate,test_create.enddate,"
						+ "test_create.maxmarks from test_create inner join test_mode on test_create.gradeid=? and "
						+ "test_create.modeid=test_mode.id inner join test_type on test_create.testtypeid=test_type.id",
				gradeid).results(TestTransferObject.class);
		for (TestTransferObject testTransferObject : tests) {
			List<TestSubjectsDisplay> subjectmarks = db
					.sql("select subjects.subjectname,student_testresult.marks,test_syllabus.maxmarks from "
							+ "student_testresult join subjects on subjects.id=student_testresult.subjectid "
							+ "join test_syllabus on test_syllabus.testid=student_testresult.testid "
							+ "where student_testresult.studentid=? and student_testresult.testid=?student_testresult.classid=?",studentid,testTransferObject.getId(),classid)
					.results(TestSubjectsDisplay.class);
			testTransferObject.setSubjects(subjectmarks);
		}
		return tests;
	}

	
	public List<Notifications> notifications(long tenantId) {
		db = retrieve.getDatabase(tenantId);
		return db.sql("select * from notifications").results(Notifications.class);
	}

	public List<AttendanceDTO> attendanceReport(long classid, long studentid, long tenantId) {
		db = retrieve.getDatabase(tenantId);
		long attendancetypeid = db.sql("select * from school_attendance where status=1")
				.results(AttendanceMode.class).get(0).getId();
		if (attendancetypeid != 1) {
		return db.sql("select dateofattendance,attendancestatus from classroom_attendance where classroomid=? and studentid=? order by dateofattendance desc",classid,studentid).results(AttendanceDTO.class);
		}
		else
		{
			List<AttendanceDTO> attendancedates=db.sql("select distinct dateofattendance from attendance_subjectwise where classroomid=? and studentid=? order by dateofattendance desc",classid,studentid).results(AttendanceDTO.class);
			for(AttendanceDTO date: attendancedates)
			{
				date.setSubjectattendance(db.sql("select subjects.subjectname,attendance_subjectwise.attendancestatus from subjects join attendance_subjectwise "
					+ "on attendance_subjectwise.subjectid=subjects.id where dateofattendance=? and classroomid=? and studentid=?",date.getDateofattendance(),classid,studentid).results(AttendanceDTO.class));
			}
			return attendancedates;
		}
	}

}
