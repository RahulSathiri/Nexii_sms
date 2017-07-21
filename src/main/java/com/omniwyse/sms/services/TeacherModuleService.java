package com.omniwyse.sms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.ClassRoom;
import com.omniwyse.sms.models.SubjectTeacherClass;
import com.omniwyse.sms.models.Subjects;
import com.omniwyse.sms.models.Teachers;
import com.omniwyse.sms.utils.ClassRoomDetails;
import com.omniwyse.sms.utils.ClassSectionTransferObject;
import com.omniwyse.sms.utils.TeacherModuleDTO;
import com.omniwyse.sms.utils.TeacherScheduleDTO;
import com.omniwyse.sms.utils.TestTransferObject;


@Service
public class TeacherModuleService {

	@Autowired
	private DatabaseRetrieval retrive;
	@Autowired
	private StudentsService studentService;

	private Database db;

	public List<TeacherModuleDTO> listAllSubjectsAlongWithClassRooms(ClassSectionTransferObject moduleDTO) {

		db = retrive.getDatabase(1);

		List<TeacherModuleDTO> list = db.sql(
				"select classrooms.id, subjects.subjectname, classrooms.gradeid, classrooms.sectionname from subjects "
						+ "JOIN class_subject_teacher ON class_subject_teacher.subjectid = subjects.id JOIN classrooms"
						+ " ON classrooms.id = class_subject_teacher.classid where class_subject_teacher.teacherid = ?",
				moduleDTO.getId()).results(TeacherModuleDTO.class);

		return list;
	}

	public List<ClassSectionTransferObject> getClassRoomOfTeacherAssignedCRT(ClassSectionTransferObject moduleDTO) {
		db = retrive.getDatabase(1);

		List<ClassSectionTransferObject> list = db
				.sql("select gradeid, sectionname from classrooms where classteacherid = ? ", moduleDTO.getId())
				.results(ClassSectionTransferObject.class);

		return list;
	}

	public List<Teachers> showTeacherProfile(ClassSectionTransferObject moduleDTO) {

		db = retrive.getDatabase(1);
		List<Teachers> teacher = db.where("id = ?", moduleDTO.getId()).results(Teachers.class);
		return teacher;

	}

	@SuppressWarnings("deprecation")
	public List<TeacherScheduleDTO> getSchedule(ClassSectionTransferObject dataObject, String date) {

		db = retrive.getDatabase(1);

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
	public  ClassRoomDetails teacherModuleList(long id, String subjectname) {
		
		db = retrive.getDatabase(1);
		ClassRoomDetails classroom=new ClassRoomDetails();
		classroom.setStudentsOfClassRoom(studentService.getStudentsOfClassRoom(id));
		
		
		long subjectid = db.where("subjectname=?",subjectname).results(Subjects.class).get(0).getId();
		long gradeid=db.where("id=?", id).results(ClassRoom.class).get(0).getGradeid();
		List<TestTransferObject> listTetss = db
				.sql("select test_type.testtype,test_create.startdate, test_create.enddate from test_create join test_syllabus on test_create.gradeid=?"
						+ " and test_syllabus.subjectid=? and test_create.id=test_syllabus.testid join test_type on"
						+ " test_create.testtypeid=test_type.id ", gradeid, subjectid)
				.results(TestTransferObject.class);
		
		classroom.setTests(listTetss);
		
		return classroom;
	}

}
