package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.AttendanceMode;
import com.omniwyse.sms.models.Notifications;
import com.omniwyse.sms.models.Subjects;
import com.omniwyse.sms.utils.AttendanceDTO;
import com.omniwyse.sms.utils.MainResultsTransferObject;
import com.omniwyse.sms.utils.ParentDTO;
import com.omniwyse.sms.utils.ResultsTransferObject;
import com.omniwyse.sms.utils.StudentSubjectMarks;

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
	
	public MainResultsTransferObject studentsMarks(long studentid, long gradeid, long classid, long testcreateid,
			long tenantId) {
		 db = retrieve.getDatabase(tenantId);

	       List<ResultsTransferObject> resulttransferlist = db.sql("select distinct cltrs.studentid as studentid,st.name,tt.testtype,cltrs.resultorgrade from classroom_testresult cltrs "
	                        + "left join test_create tc on tc.testtypeid = cltrs.testid left join grade_subjects gs on gs.gradeid = tc.gradeid left join students st on cltrs.studentid=st.id"
	                        + " left join test_syllabus ts on ts.subjectid = gs.subjectid left join test_type tt on tt.id = cltrs.testid where cltrs.testid=? and cltrs.studentid=? and gs.gradeid=?",
	                        testcreateid,studentid,gradeid).results(ResultsTransferObject.class);

	        for (ResultsTransferObject rto : resulttransferlist) {
	            rto.setStudentsubjectmarks(db.sql("select sb.subjectname,str.marks from student_testresult str left join subjects sb on str.subjectid = sb.id"
	                            + " where str.classid=? and str.testid=? and str.studentid=?", classid, testcreateid,studentid).results(StudentSubjectMarks.class));
	        }

	        List<Subjects> subjectlist = db.sql("select sb.subjectname from grade_subjects gs " + "left join subjects sb on sb.id = gs.subjectid where gs.gradeid=?", gradeid).results(Subjects.class);

	        MainResultsTransferObject mainresults = new MainResultsTransferObject();
	        mainresults.setResulttransfer(resulttransferlist);
	        mainresults.setSubjects(subjectlist);
	        return mainresults;
	}


}
