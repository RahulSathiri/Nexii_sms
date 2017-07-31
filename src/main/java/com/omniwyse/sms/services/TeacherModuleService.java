package com.omniwyse.sms.services;

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
				.sql("select gradeid, sectionname from classrooms where classteacherid = ? ", moduleDTO.getId())
				.results(ClassSectionTransferObject.class);

		return list;
	}

	public Teachers showTeacherProfile(long tenantId, ClassSectionTransferObject moduleDTO) {

		db = retrive.getDatabase(tenantId);
		Teachers teacher = db.where("id = ?", moduleDTO.getId()).results(Teachers.class).get(0);
		return teacher;

	}
	@SuppressWarnings("deprecation")
	public List<TeacherScheduleDTO> getSchedule(long tenantId, ClassSectionTransferObject dataObject, String date) {

		db = retrive.getDatabase(tenantId);

		List<TeacherScheduleDTO> list = new ArrayList<>();
		List<SubjectTeacherClass> classub = db
				.sql("select classid, subjectid from class_subject_teacher where teacherid = ?", dataObject.getId())
				.results(SubjectTeacherClass.class);
		for (SubjectTeacherClass sub : classub) {
			List<TeacherScheduleDTO> sublist = db
					.sql(" select classroom_periods.periodfrom, classroom_periods.periodto,"
							+ "subjects.subjectname,classrooms.gradeid,classrooms.sectionname from classroom_periods "
							+ " join subjects on classroom_periods.subjectid=subjects.id join classrooms on classrooms.id = classroom_periods.classroomid"
							+ " where classroom_periods.classroomid =? and classroom_periods.subjectid = ? "
							+ "and classroom_periods.dateofassigning = ?", sub.getClassid(), sub.getSubjectid(), date)
					.results(TeacherScheduleDTO.class);
			int variable = 0;
			for (TeacherScheduleDTO teacher : sublist) {
				list.add(sublist.get(variable));
				variable++;
			}

		}

		return list;

	}
	public  ClassRoomDetails  teacherModuleList(long tenantId, long id, String subjectname) {
		
		db = retrive.getDatabase(tenantId);
		ClassRoomDetails classroom=new ClassRoomDetails();
		classroom.setStudentsOfClassRoom(studentService.getStudentsOfClassRoom(tenantId,id));
		
		
		long subjectid = db.where("subjectname=?",subjectname).results(Subjects.class).get(0).getId();
		long gradeid=db.where("id=?", id).results(ClassRoom.class).get(0).getGradeid();
		List<TestTransferObject> listTetss = db
				.sql("select test_type.testtype,test_create.startdate, test_create.enddate from "
						+ "test_create join test_syllabus on test_create.gradeid=?"
						+ " and test_syllabus.subjectid=? and test_create.id=test_syllabus.testid join test_type on"
						+ " test_create.testtypeid=test_type.id ", gradeid, subjectid)
				.results(TestTransferObject.class);
		
		classroom.setTests(listTetss);
		
		return classroom;
	}
		
//students list of subject 
	public  ClassRoomDetails teacherModulestudentsList(long tenantId,long id, String subjectname) {
	
		db = retrive.getDatabase(tenantId);
		ClassRoomDetails classroom=new ClassRoomDetails();
		classroom.setStudentsOfClassRoom(studentService.getStudentsOfClassRoom(tenantId,id));
		return classroom;
	}
//tests list
	public List<TestTransferObject> getListOfsubjectTests(long tenantId,long id, String subjectname) {

		db = retrive.getDatabase(tenantId);
		long gradeid = db.where("id=?", id).results(ClassRoom.class).get(0).getGradeid();
		long subjectid = db.where("subjectname=?", subjectname).results(Subjects.class).get(0).getId();
		List<TestTransferObject> testsdetails = db
				.sql("SELECT  test_create.id,test_type.testtype,test_mode.testmode,test_create.startdate,test_create.enddate,"
						+ "test_syllabus.subjectid,test_create.maxmarks,test_syllabus.syllabus " 
						+ "FROM test_create "
						+ "JOIN test_mode on test_create.modeid = test_mode.id "
						+ "JOIN test_type on test_create.testtypeid = test_type.id "
						+ "JOIN test_syllabus on test_syllabus.testid = test_create.id "
						+ "WHERE test_syllabus.subjectid = ? AND test_create.gradeid = ?", subjectid, gradeid)
				.results(TestTransferObject.class);

		return testsdetails;

	}

	public List<TimelineDTO> viewTimeline(TimelineDTO data) {

		db = retrive.getDatabase(1);

		long classroomid = data.getId();
		long subjectid = db.where("subjectname = ?", data.getSubjectname()).results(Subjects.class).get(0).getId();

		List<TimelineDTO> list = db
				.sql("select lessons.lessonstartdate, lessons.lessonname, lessons.tags, classroom_worksheets.worksheetduedate,"
						+ "assignments.assignmentduedate, worksheets.worksheetname, assignments.assignmentname from lessons LEFT OUTER JOIN assignments "
						+ "ON assignments.lessonsid = lessons.id LEFT OUTER JOIN classroom_worksheets  ON "
						+ "classroom_worksheets.lessonsid = lessons.id LEFT OUTER JOIN worksheets ON "
						+ "classroom_worksheets.worksheetsid = worksheets.id where"
						+ " lessons.subjectid = ? and lessons.classroomid = ?;", subjectid, classroomid)
				.results(TimelineDTO.class);
		return list;
	}

	public int addingLesson(TimelineDTO data) {

		int rowEffected = 0;
		db = retrive.getDatabase(1);
		Lessons lesson = new Lessons();

		Transaction transact = db.startTransaction();
		try {
			lesson.setClassroomid(data.getId());
			lesson.setLessonname(data.getLessonname());
			lesson.setLessonstartdate(data.getLessonstartdate());
			lesson.setSubjectid(
					db.where("subjectname = ?", data.getSubjectname()).results(Subjects.class).get(0).getId());
			lesson.setTags(data.getTags());
			lesson.setStatus(data.getStatus());

			rowEffected = db.transaction(transact).insert(lesson).getRowsAffected();
			transact.commit();
		} catch (Exception e) {

			transact.rollback();
			return rowEffected;
		}
		return rowEffected;
	}

	public List<WorkSheetsDTO> listWorkSheetsbasedOn(WorkSheetsDTO data) {

		long gradeid = db.where("id = ?", data.getId()).results(ClassRoom.class).get(0).getGradeid();
		data.setGradeid(gradeid);
		List<WorkSheetsDTO> list = workSheetService.listingWorksheetsOfTenant(data);
		return list;
	}

	public int assignAssignment(AssignmentDTO assigning) {

		db = retrive.getDatabase(1);
		
		return db.insert(assignments(db, assigning)).getRowsAffected();
	}
	
	private Assignments assignments(Database db, AssignmentDTO assigning){
		
		long lessonid = db.where("lessonname = ?", assigning.getLessonname()).results(Lessons.class).get(0).getId();
		Assignments assignment = new Assignments();

		assignment.setClassroomid(assigning.getId());
		assignment.setAssignmentname(assigning.getAssignmentname());
		assignment.setDateofassigned(assigning.getDateofassigned());
		assignment.setAssignmentduedate(assigning.getDuedate());
		assignment.setSubjectid(
				db.where("subjectname = ?", assigning.getSubjectname()).results(Subjects.class).get(0).getId());
		assignment.setLessonsid(lessonid);

		return assignment;
	}

	public int worksheetAssign(WorkSheetsDTO data) {

		db = retrive.getDatabase(1);
		return db.insert(worksheets(db, data)).getRowsAffected();

	}

	private ClassroomWorksheets worksheets(Database db, WorkSheetsDTO data) {

		long lessonid = db.where("lessonname = ?", data.getLessonname()).results(Lessons.class).get(0).getId();

		ClassroomWorksheets worksheet = new ClassroomWorksheets();

		worksheet.setClassroomid(data.getId());
		worksheet.setWorksheetsid(
				db.where("worksheetname = ?", data.getWorksheetname()).results(Worksheets.class).get(0).getId());
		worksheet.setDateofassigned(data.getDateofassigned());
		worksheet.setWorksheetduedate(data.getDuedate());
		worksheet.setSubjectid(
				db.where("subjectname = ?", data.getSubjectname()).results(Subjects.class).get(0).getId());
		worksheet.setLessonsid(lessonid);

		return worksheet;
	}
	
	public List<Lessons> lessonsList(TimelineDTO data) {

		db = retrive.getDatabase(1);

		long subjectid = db.where("subjectname = ?", data.getSubjectname()).results(Subjects.class).get(0).getId();

		return db.where("classroomid = ? and subjectid = ?", data.getId(), subjectid).results(Lessons.class);
	}

	public int updateAssignedAssignment(AssignmentDTO assigning) {

		db = retrive.getDatabase(1);
		return db.update(assignments(db, assigning)).getRowsAffected();
	}

	public int updaetWorksheetAssigned(WorkSheetsDTO data) {

		db = retrive.getDatabase(1);
		return db.update(worksheets(db, data)).getRowsAffected();
	}

}
