package com.omniwyse.sms.services;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DatabaseRetrieval;

import com.omniwyse.sms.utils.DateOfBirthDTO;

@Service
public class StudentsDateOfBirthService {

	@Autowired
	private DatabaseRetrieval retrive;
	private Database db;

	public List<DateOfBirthDTO> getClassStudentsBirthDay(DateOfBirthDTO dateOfBirthDTO) {
		long teacherid = dateOfBirthDTO.getTeacherid();
		Date dateNow = new Date();
		SimpleDateFormat dateformatJava = new SimpleDateFormat("dd-MM-yyyy");
		String date_to_string = dateformatJava.format(dateNow);
		date_to_string = date_to_string.substring(0, 5) + "%";

		db = retrive.getDatabase(1);
		return db
				.sql("select distinct students.name,classrooms.sectionname,grades.gradename from students "
						+ "join classrooms on classrooms.classteacherid=? "
						+ "join classroom_students on classroom_students.classid =classrooms.id "
						+ "and classroom_students.studentid=students.id and students.dateofbirth like ? "
						+ "join grades  on grades.id=classrooms.gradeid", teacherid, date_to_string)
				.results(DateOfBirthDTO.class);

	}

	public List<DateOfBirthDTO> getClassSubjectsStudentsBirthDay(DateOfBirthDTO dateOfBirthDTO) {
		long teacherid = dateOfBirthDTO.getTeacherid();
		Date dateNow = new Date();
		SimpleDateFormat dateformatJava = new SimpleDateFormat("dd-MM-yyyy");
		String date_to_string = dateformatJava.format(dateNow);
		date_to_string = date_to_string.substring(0, 5) + "%";

		db = retrive.getDatabase(1);
		return db
				.sql("select distinct students.name,classrooms.sectionname,grades.gradename from students "
						+ "join classroom_students on classroom_students.studentid=students.id and students.dateofbirth like ? "
						+ "join class_subject_teacher on classroom_students.classid=class_subject_teacher.classid and class_subject_teacher.teacherid=? "
						+ "join classrooms on classrooms.id=classroom_students.classid "
						+ "join grades on grades.id=classrooms.gradeid", date_to_string, teacherid)
				.results(DateOfBirthDTO.class);

	}

	public List<DateOfBirthDTO> getStudentsBirthDays() {

		Date dateNow = new Date();
		SimpleDateFormat dateformatJava = new SimpleDateFormat("dd-MM-yyyy");
		String date_to_string = dateformatJava.format(dateNow);
		date_to_string = date_to_string.substring(0, 5) + "%";
		

		db = retrive.getDatabase(1);
		return db.sql("select distinct students.name,classrooms.sectionname,grades.gradename from students "
				+ "join classroom_students on classroom_students.studentid=students.id and students.dateofbirth like ? "
				+ "join classrooms on classrooms.id=classroom_students.classid "
				+ "join grades on grades.id=classrooms.gradeid", date_to_string).results(DateOfBirthDTO.class);

	}

}
