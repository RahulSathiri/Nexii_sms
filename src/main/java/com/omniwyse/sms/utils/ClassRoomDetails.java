package com.omniwyse.sms.utils;

import java.util.List;

import com.omniwyse.sms.models.Students;
import com.omniwyse.sms.models.Subjects;
import com.omniwyse.sms.models.TestType;



public class ClassRoomDetails {
	private List<ClassSectionTransferObject> subjectteachers;
	private List<Subjects> subjects;
	private List<ClassRoomStudents> studentsOfClassRoom;
	private List<Students> studentsoOfGrade;
	private List<TableView> timetable;
	private List<TestType> tests; 
	
	
	public List<TestType> getTests() {
		return tests;
	}

	public void setTests(List<TestType> tests) {
		this.tests = tests;
	}

	public List<TableView> getTimetable() {
		return timetable;
	}

	public void setTimetable(List<TableView> timetable) {
		this.timetable = timetable;
	}

	public List<ClassSectionTransferObject> getSubjectteachers() {
		return subjectteachers;
	}

	public void setSubjectteachers(List<ClassSectionTransferObject> subjectteachers) {
		this.subjectteachers = subjectteachers;
	}

	public List<Subjects> getSubjects() {
		return subjects;
	}

	public List<ClassRoomStudents> getStudentsOfClassRoom() {
		return studentsOfClassRoom;
	}

	public void setStudentsOfClassRoom(List<ClassRoomStudents> studentsOfClassRoom) {
		this.studentsOfClassRoom = studentsOfClassRoom;
	}

	public List<Students> getStudentsoOfGrade() {
		return studentsoOfGrade;
	}

	public void setStudentsoOfGrade(List<Students> studentsoOfGrade) {
		this.studentsoOfGrade = studentsoOfGrade;
	}

	public void setSubjects(List<Subjects> subjects) {
		this.subjects = subjects;
	}

}
