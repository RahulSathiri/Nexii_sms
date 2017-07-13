package com.omniwyse.sms.utils;

import java.util.List;

import com.omniwyse.sms.models.Students;
import com.omniwyse.sms.models.Subjects;

public class ClassRoomDetails {
	private List<ClassSectionTransferObject> subjectteachers;
	private List<Subjects> subjects;
	private List<ClassRoomStudents> studentsOfClassRoom;
	private List<Students> studentsoOfGrade;

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
