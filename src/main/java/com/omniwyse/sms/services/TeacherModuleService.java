package com.omniwyse.sms.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.dieselpoint.norm.Transaction;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.Assignments;
import com.omniwyse.sms.models.ClassRoom;
import com.omniwyse.sms.models.ClassroomWorksheets;
import com.omniwyse.sms.models.Lessons;
import com.omniwyse.sms.models.Notifications;
import com.omniwyse.sms.models.SubjectTeacherClass;
import com.omniwyse.sms.models.Subjects;
import com.omniwyse.sms.models.Teachers;
import com.omniwyse.sms.models.Worksheets;
import com.omniwyse.sms.utils.AssignmentDTO;
import com.omniwyse.sms.utils.ClassRoomDetails;
import com.omniwyse.sms.utils.ClassSectionTransferObject;
import com.omniwyse.sms.utils.TeacherModuleDTO;
import com.omniwyse.sms.utils.TeacherScheduleDTO;
import com.omniwyse.sms.utils.TestTransferObject;
import com.omniwyse.sms.utils.TimelineDTO;
import com.omniwyse.sms.utils.WorkSheetsDTO;

@Service
public class TeacherModuleService {

	@Autowired
	private DatabaseRetrieval retrive;
	@Autowired
	private StudentsService studentService;

	@Autowired
	private WorksheetService workSheetService;

	private Database db;

	public List<TeacherModuleDTO> listAllSubjectsAlongWithClassRooms(long tenantId,
			ClassSectionTransferObject moduleDTO) {

		db = retrive.getDatabase(tenantId);

		List<TeacherModuleDTO> list = db.sql(
				"select classrooms.id, subjects.subjectname, classrooms.gradeid, classrooms.sectionname from subjects "
						+ "JOIN class_subject_teacher ON class_subject_teacher.subjectid = subjects.id JOIN classrooms"
						+ " ON classrooms.id = class_subject_teacher.classid where class_subject_teacher.teacherid = ?",
				moduleDTO.getId()).results(TeacherModuleDTO.class);

		return list;
	}

	public List<ClassSectionTransferObject> getClassRoomOfTeacherAssignedCRT(long tenantId,
			ClassSectionTransferObject moduleDTO) {
		db = retrive.getDatabase(tenantId);

		List<ClassSectionTransferObject> list = db
				.sql("select id, gradeid, sectionname from classrooms where classteacherid = ? ", moduleDTO.getId())
				.results(ClassSectionTransferObject.class);

		return list;
	}

	public Teachers showTeacherProfile(long tenantId, ClassSectionTransferObject moduleDTO) {

		db = retrive.getDatabase(tenantId);
		Teachers teacher = db.where("id = ?", moduleDTO.getId()).results(Teachers.class).get(0);
		return teacher;

	}
	@SuppressWarnings("deprecation")
	public List<TeacherScheduleDTO> getSchedule(long tenantId, ClassSectionTransferObject dataObject, int dayId) {

		db = retrive.getDatabase(tenantId);

		List<TeacherScheduleDTO> list = new ArrayList<>();
		List<SubjectTeacherClass> classub = db
				.sql("select classid, subjectid from class_subject_teacher where teacherid = ?", dataObject.getId())
				.results(SubjectTeacherClass.class);
		for (SubjectTeacherClass sub : classub) {
			List<TeacherScheduleDTO> sublist = db
					.sql(" select classroom_periods.periodfrom, classroom_periods.periodto, subjects.subjectname,classrooms.gradeid,"
							+ "classrooms.sectionname from classroom_periods join subjects on classroom_periods.subjectid=subjects.id"
							+ " join classrooms on classrooms.id = classroom_periods.classroomid where classroom_periods.classroomid = ?"
							+ " and classroom_periods.subjectid = ? and classroom_periods.classroomweekdayid = ?",
							sub.getClassid(), sub.getSubjectid(), dayId)
					.results(TeacherScheduleDTO.class);
			int variable = 0;
			for (TeacherScheduleDTO teacher : sublist) {
				list.add(sublist.get(variable));
				variable++;
			}

		}

		return list;

	}

	public ClassRoomDetails teacherModuleList(long tenantId, long id, String subjectname) {

		db = retrive.getDatabase(tenantId);
		ClassRoomDetails classroom = new ClassRoomDetails();
		classroom.setStudentsOfClassRoom(studentService.getStudentsOfClassRoom(tenantId, id));

		long subjectid = db.where("subjectname=?", subjectname).results(Subjects.class).get(0).getId();
		long gradeid = db.where("id=?", id).results(ClassRoom.class).get(0).getGradeid();
		List<TestTransferObject> listTetss = db
				.sql("select test_type.testtype,test_create.startdate, test_create.enddate from "
						+ "test_create join test_syllabus on test_create.gradeid=?"
						+ " and test_syllabus.subjectid=? and test_create.id=test_syllabus.testid join test_type on"
						+ " test_create.testtypeid=test_type.id ", gradeid, subjectid)
				.results(TestTransferObject.class);

		classroom.setTests(listTetss);

		return classroom;
	}

	public ClassRoomDetails teacherModulestudentsList(long tenantId, long id, String subjectname) {

		db = retrive.getDatabase(tenantId);
		ClassRoomDetails classroom = new ClassRoomDetails();
		classroom.setStudentsOfClassRoom(studentService.getStudentsOfClassRoom(id, tenantId));
		return classroom;
	}

	public List<TestTransferObject> getListOfsubjectTests(long tenantId, long id, String subjectname) {

		db = retrive.getDatabase(tenantId);
		long gradeid = db.where("id=?", id).results(ClassRoom.class).get(0).getGradeid();
		long subjectid = db.where("subjectname=?", subjectname).results(Subjects.class).get(0).getId();
		List<TestTransferObject> testsdetails = db.sql("SELECT  test_syllabus.id,test_syllabus.testid,test_type.testtype,test_mode.testmode,test_create.startdate,test_create.enddate,"
                        + "test_syllabus.subjectid,test_syllabus.maxmarks,test_syllabus.syllabus FROM test_create "
						+ "JOIN test_mode on test_create.modeid = test_mode.id  JOIN test_type on test_create.testtypeid = test_type.id "
						+ "JOIN test_syllabus on test_syllabus.testid = test_create.id "
						+ "WHERE test_syllabus.subjectid = ? AND test_create.gradeid = ?", subjectid, gradeid).results(TestTransferObject.class);

		return testsdetails;

	}

	public List<TimelineDTO> viewTimeline(long tenantId, TimelineDTO data) {

		db = retrive.getDatabase(tenantId);
		List<TimelineDTO> lessons = null;
		List<WorkSheetsDTO> worksheets = null;
		List<AssignmentDTO> assignments = null;

		String queryForWorksheets = "select lessons.lessonname, worksheets.tags, worksheets.worksheetname,worksheets.createdby,"
				+ "worksheets.worksheetpath,classroom_worksheets.id, classroom_worksheets.dateofassigned,classroom_worksheets.worksheetduedate "
				+ "from worksheets join classroom_worksheets on classroom_worksheets.worksheetsid=worksheets.id "
				+ " JOIN lessons ON lessons.id = classroom_worksheets.lessonsid ";

		String queryForLessons = "select lessons.id,lessons.lessonname,lessons.status,lessons.lessondescription,lessons.lessonstartdate, lessons.publishtimeline";
		String subjectSelectQuery = ", subjects.subjectname";
		String queryForAssignments = "select lessons.lessonname, assignments.tags, assignments.id,assignments.assignmentname,"
				+ "assignments.dateofassigned,assignments.assignmentduedate "
				+ "from assignments  JOIN lessons ON lessons.id = assignments.lessonsid ";

		long classroomid = data.getId();
		if (data.getSubjectname() != null) {

			long subjectid = db.where("subjectname = ?", data.getSubjectname()).results(Subjects.class).get(0).getId();
			queryForLessons = queryForLessons + " from lessons where classroomid=? and subjectid=? ";
			queryForWorksheets = queryForWorksheets
					+ " where classroom_worksheets.classroomid=? and classroom_worksheets.subjectid=? "
					+ "and classroom_worksheets.lessonsid=?";
			queryForAssignments = queryForAssignments
					+ " where assignments.classroomid=? and assignments.subjectid=? and assignments.lessonsid=?";

			if (data.getDatefrom() != null && data.getDateto() != null) {
				queryForLessons = dateFilterbasedTime(data, queryForLessons);
				lessons = db.sql(queryForLessons, classroomid, subjectid, data.getDatefrom(), data.getDateto())
						.results(TimelineDTO.class);
			} else {
				lessons = db.sql(queryForLessons, classroomid, subjectid).results(TimelineDTO.class);
			}
			for (TimelineDTO lesson : lessons) {
				worksheets = db.sql(queryForWorksheets, classroomid, subjectid, lesson.getId())
						.results(WorkSheetsDTO.class);
				lesson.setWorksheets(worksheets);
				assignments = db.sql(queryForAssignments, classroomid, subjectid, lesson.getId())
						.results(AssignmentDTO.class);
				lesson.setAssignments(assignments);

			}
		} else {
			queryForLessons = queryForLessons + subjectSelectQuery + " from lessons JOIN subjects on "
					+ "subjects.id = lessons.subjectid where classroomid=? ";
			queryForWorksheets = queryForWorksheets
					+ "where classroom_worksheets.classroomid=? and classroom_worksheets.lessonsid=? ";
			queryForAssignments = queryForAssignments + "where assignments.classroomid=? and assignments.lessonsid=? ";
			if (data.getDatefrom() != null && data.getDateto() != null) {
				queryForLessons = dateFilterbasedTime(data, queryForLessons);
				lessons = db.sql(queryForLessons, classroomid, data.getDatefrom(), data.getDateto()).results(TimelineDTO.class);
			} else {
				lessons = db.sql(queryForLessons, classroomid).results(TimelineDTO.class);
			}
			for (TimelineDTO lesson : lessons) {
				worksheets = db.sql(queryForWorksheets, classroomid, lesson.getId()).results(WorkSheetsDTO.class);
				lesson.setWorksheets(worksheets);
				assignments = db.sql(queryForAssignments, classroomid, lesson.getId()).results(AssignmentDTO.class);
				lesson.setAssignments(assignments);
			}
		}

		return lessons;
	}

	private String dateFilterbasedTime(TimelineDTO data, String queryForLessons) {

			queryForLessons = queryForLessons
					+ " and lessonstartdate between ? and ? order by lessonstartdate desc";
			return queryForLessons;
	}

	public int addingLesson(long tenantId, TimelineDTO data) {

		int rowEffected = 0;
		db = retrive.getDatabase(tenantId);
		Lessons lesson = new Lessons();

		Transaction transaction = db.startTransaction();
		try {
			lesson.setClassroomid(data.getId());
			lesson.setLessondescription(data.getLessondescription());
			lesson.setLessonstartdate(data.getLessonstartdate());
			lesson.setLessonname(data.getLessonname());
			lesson.setStatus(data.getStatus());
			if (data.getSubjectname() != null) {
				lesson.setSubjectid(
						db.where("subjectname = ?", data.getSubjectname()).results(Subjects.class).get(0).getId());
			}
			long flag = data.getPublish();
			if (flag == 0) {
				lesson.setPublishtimeline(true);
				Notifications notifications = new Notifications();

				notifications.setNotificationname(data.getLessonname());
				notifications.setDescription(data.getLessondescription());
				notifications.setActioncode("TL");
				notifications.setParentactionrequired("No");
				notifications.setPublishedby("Tejaswi Chava");
				notifications.setNotificationdate(data.getLessonstartdate());

				rowEffected = db.transaction(transaction).insert(notifications).getRowsAffected();
			}

			rowEffected = db.transaction(transaction).insert(lesson).getRowsAffected();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			return -3;
		}
		return rowEffected;
	}

	public List<WorkSheetsDTO> listWorkSheetsbasedOn(long tenantId, WorkSheetsDTO data) {

		long gradeid = db.where("id = ?", data.getId()).results(ClassRoom.class).get(0).getGradeid();
		data.setGradeid(gradeid);
		List<WorkSheetsDTO> list = workSheetService.listingWorksheetsOfTenant(tenantId, data);
		return list;
	}

	public int assignAssignment(long tenantId, AssignmentDTO assigning) {

		db = retrive.getDatabase(tenantId);

		return db.insert(assignments(db, assigning)).getRowsAffected();
	}

	private Assignments assignments(Database db, AssignmentDTO assigning) {

		Assignments assignment = new Assignments();
		long classroomid = assigning.getId();
		String assignmentname = assigning.getAssignmentname();
		Date dateofassigned = assigning.getDateofassigned();
		Date assignmentduedate = assigning.getDuedate();
		assignment.setClassroomid(classroomid);
		assignment.setAssignmentname(assignmentname);
		assignment.setDateofassigned(dateofassigned);
		assignment.setAssignmentduedate(assignmentduedate);
		assignment.setTags(assigning.getTags());
		if (assigning.getSubjectname() != null) {
			Long subjectid = db.where("subjectname = ?", assigning.getSubjectname()).results(Subjects.class).get(0)
					.getId();
			assignment.setSubjectid(subjectid);
		}
		if(assigning.getLessonname()!=null){
		Long lessonid = db.where("lessonname = ?", assigning.getLessonname()).results(Lessons.class).get(0).getId();
		assignment.setLessonsid(lessonid);
		}

		return assignment;
	}

	public int worksheets(long tenantId, WorkSheetsDTO data) {
		db = retrive.getDatabase(tenantId);
		Long lessonid = null;
		ClassroomWorksheets worksheet = new ClassroomWorksheets();
		if (data.getLessonname() != null) {
			lessonid = db.where("lessonname = ?", data.getLessonname()).results(Lessons.class).get(0).getId();
			worksheet.setLessonsid(lessonid);
		}

		long classroomid = data.getId();
		worksheet.setClassroomid(classroomid);
		long worksheetsid = db.where("worksheetname = ?", data.getWorksheetname()).results(Worksheets.class).get(0)
				.getId();
		worksheet.setWorksheetsid(worksheetsid);
		worksheet.setDateofassigned(data.getDateofassigned());
		worksheet.setWorksheetduedate(data.getDuedate());
		if (data.getSubjectname() != null) {
			Long subjectid = db.where("subjectname = ?", data.getSubjectname()).results(Subjects.class).get(0).getId();
			worksheet.setSubjectid(subjectid);
		}

		List<ClassroomWorksheets> worksheets = db
				.where("classroomid=?  and  lessonsid=? and  worksheetsid=?", classroomid, lessonid, worksheetsid)
				.results(ClassroomWorksheets.class);
		if (worksheets.isEmpty()) {
			return db.insert(worksheet).getRowsAffected();
		} else
			return 0;
	}

	public List<Lessons> lessonsList(long tenantId, TimelineDTO data) {

		db = retrive.getDatabase(tenantId);

		long subjectid = db.where("subjectname = ?", data.getSubjectname()).results(Subjects.class).get(0).getId();

		return db.where("classroomid = ? and subjectid = ?", data.getId(), subjectid).results(Lessons.class);
	}

	public List<TestTransferObject> getListOfClassroomTests(long tenantId, long id) {

		db = retrive.getDatabase(tenantId);
		long gradeid = db.where("id=?", id).results(ClassRoom.class).get(0).getGradeid();

		List<TestTransferObject> testsdetails = db.sql("SELECT  test_syllabus.id,test_syllabus.testid,test_type.testtype,test_mode.testmode,"
						+ "test_create.startdate,test_create.enddate,test_syllabus.subjectid,test_create.maxmarks,test_syllabus.syllabus"
						+ " FROM test_create "
						+ "JOIN test_mode on test_create.modeid = test_mode.id "
						+ "JOIN test_type on test_create.testtypeid = test_type.id "
						+ "JOIN test_syllabus on test_syllabus.testid = test_create.id "
						+ "WHERE test_create.gradeid = ?", gradeid).results(TestTransferObject.class);

		return testsdetails;

	}

	public int worksheetAssign(WorkSheetsDTO data) {

		db = retrive.getDatabase(1);

		long lessonid = db.where("lessonname = ?", data.getLessonname()).results(Lessons.class).get(0).getId();

		ClassroomWorksheets worksheet = new ClassroomWorksheets();

		worksheet.setClassroomid(data.getId());
		worksheet.setWorksheetsid(
				db.where("worksheetname = ?", data.getWorksheetname()).results(Worksheets.class).get(0).getId());
		worksheet.setDateofassigned(data.getDateofassigned());
		worksheet.setWorksheetduedate(data.getDateofassigned());
		worksheet.setSubjectid(
				db.where("subjectname = ?", data.getSubjectname()).results(Subjects.class).get(0).getId());
		worksheet.setLessonsid(lessonid);

		return db.insert(worksheet).getRowsAffected();

	}

	public int deleteAssignedWorksheet(ClassroomWorksheets data, long tenantId) {
		db = retrive.getDatabase(tenantId);
		return db.delete(data).getRowsAffected();
	}

	public int deleteAssignedAssignment(Assignments data, long tenantId) {
		db = retrive.getDatabase(tenantId);
		return db.delete(data).getRowsAffected();

	}

	public List<AssignmentDTO> assignmentsList(long tenantId, TimelineDTO data) {

		db = retrive.getDatabase(tenantId);
		List<AssignmentDTO> list = null;
		String query = "select subjects.subjectname, assignments.assignmentduedate, assignments.assignmentname, assignments.dateofassigned,"
				+ "assignments.publishassignment, assignments.tags, lessons.lessonname from assignments LEFT JOIN lessons ON "
				+ "lessons.id = assignments.lessonsid LEFT JOIN subjects  ON subjects.id = assignments.subjectid ";
		if (data.getSubjectname() != null) {
			long subjectId = db.where("subjectname = ?", data.getSubjectname()).results(Subjects.class).get(0).getId();
			list = db.sql(query + " where assignments.classroomid = ? and assignments.subjectid = ?", data.getId(), subjectId)
					.results(AssignmentDTO.class);
			return list;
		} else {
			list = db.sql(query + " where assignments.classroomid = ? ", data.getId()).results(AssignmentDTO.class);
			return list;
		}
	}

	public List<WorkSheetsDTO> assignedWorksheetsList(long tenantId, TimelineDTO data) {

		db = retrive.getDatabase(tenantId);
		List<WorkSheetsDTO> list = null;
		String query = "select worksheets.worksheetname,subjects.subjectname, classroom_worksheets.dateofassigned, classroom_worksheets.worksheetduedate,"
				+ " lessons.lessonname from classroom_worksheets "
				+ "LEFT JOIN subjects ON subjects.id = classroom_worksheets.subjectid LEFT JOIN lessons ON lessons.id = classroom_worksheets.lessonsid "
				+ " JOIN worksheets ON worksheets.id = classroom_worksheets.worksheetsid ";
		if (data.getSubjectname() != null) {
			long subjectId = db.where(" subjectname = ?", data.getSubjectname()).results(Subjects.class).get(0).getId();
			list = db.sql(query + " where classroom_worksheets.classroomid = ? and classroom_worksheets.subjectid = ?",
					data.getId(), subjectId).results(WorkSheetsDTO.class);
			return list;
		} else {
			list = db.sql(query + " where classroom_worksheets.classroomid = ? ", data.getId()).results(WorkSheetsDTO.class);
			return list;
		}
	}

}