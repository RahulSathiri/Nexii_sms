package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.Subjects;
import com.omniwyse.sms.models.Syllabus;
import com.omniwyse.sms.models.Teachers;
import com.omniwyse.sms.utils.ClassRoomDetails;
import com.omniwyse.sms.utils.StudentTransferObject;

@Service
public class RecordsService {
	@Autowired
    private com.omniwyse.sms.db.DatabaseRetrieval retrive;
	@Autowired
	SubjectTeacherClassService service;
	@Autowired
	StudentsService studentservice;
	private Database db;

	public List<Teachers> getAllTeachers() {
		db = retrive.getDatabase(1);
		List<Teachers> teachers = db.sql("select * from teachers").results(Teachers.class);
		return teachers;

	}

	public List<StudentTransferObject> getAllStudents() {
		db = retrive.getDatabase(1);
		List<StudentTransferObject> students = db.sql("select grades.gradename,grades.syllabustype,students.name,students.fathername,students.dateofbirth,"
		        + "students.dateofjoining,students.id,students.gender,students.contactnumber,students.mothername,students.admissionnumber,students.emailid,"
		        + "students.address,students.id from students inner join grades on students.gradeid=grades.id").results(StudentTransferObject.class);
		return students;
	}

	public List<Subjects> getAllSubjects() {

		db = retrive.getDatabase(1);
		List<Subjects> subjects = db.sql("select * from subjects").results(Subjects.class);
		return subjects;
	}

	public List<Subjects> getAllSections() {

		db = retrive.getDatabase(1);
		List<Subjects> subjects = db.sql("select * from sections").results(Subjects.class);
		return subjects;
	}

	public ClassRoomDetails getClassRoomDetails(long classid, long gradeid) {
		ClassRoomDetails classroomdetails = new ClassRoomDetails();
		classroomdetails.setSubjects(service.getListOfSubjects(gradeid));
		classroomdetails.setSubjectteachers(service.listOfSubjectsTeachers(classid));
		classroomdetails.setStudentsOfClassRoom(studentservice.getStudentsOfClassRoom(classid));
		classroomdetails.setStudentsoOfGrade(studentservice.getStudentsOfGrade(gradeid));
		return classroomdetails;
	}

	public List<Syllabus> getAllSyllabus() {
		db = retrive.getDatabase(1);
		List<Syllabus> syllabus = db.sql("select * from syllabus").results(Syllabus.class);
		return syllabus;
	}

}
