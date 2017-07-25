package com.omniwyse.sms.services;

<<<<<<< HEAD
=======
import java.util.Calendar;
>>>>>>> feature/studentbirthdays
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DatabaseRetrieval;
<<<<<<< HEAD

=======
import com.omniwyse.sms.models.ClassRoom;
>>>>>>> feature/studentbirthdays
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
<<<<<<< HEAD
		date_to_string = date_to_string.substring(0, 5) + "%";

		db = retrive.getDatabase(1);
		return db
				.sql("select distinct students.name,classrooms.sectionname,grades.gradename from students "
						+ "join classrooms on classrooms.classteacherid=? "
						+ "join classroom_students on classroom_students.classid =classrooms.id "
						+ "and classroom_students.studentid=students.id and students.dateofbirth like ? "
						+ "join grades  on grades.id=classrooms.gradeid", teacherid, date_to_string)
				.results(DateOfBirthDTO.class);
=======
		date_to_string = "%" + date_to_string.substring(3, 5) + "-" + date_to_string.substring(0, 2);

		return this.getBirthdays(date_to_string, teacherid);
>>>>>>> feature/studentbirthdays

	}

	public List<DateOfBirthDTO> getClassSubjectsStudentsBirthDay(DateOfBirthDTO dateOfBirthDTO) {
		long teacherid = dateOfBirthDTO.getTeacherid();
		Date dateNow = new Date();
		SimpleDateFormat dateformatJava = new SimpleDateFormat("dd-MM-yyyy");
		String date_to_string = dateformatJava.format(dateNow);
<<<<<<< HEAD
		date_to_string = date_to_string.substring(0, 5) + "%";

		db = retrive.getDatabase(1);
		return db
				.sql("select distinct students.name,classrooms.sectionname,grades.gradename from students "
						+ "join classroom_students on classroom_students.studentid=students.id and students.dateofbirth like ? "
						+ "join class_subject_teacher on classroom_students.classid=class_subject_teacher.classid and class_subject_teacher.teacherid=? "
						+ "join classrooms on classrooms.id=classroom_students.classid "
						+ "join grades on grades.id=classrooms.gradeid", date_to_string, teacherid)
				.results(DateOfBirthDTO.class);
=======
		date_to_string = "%" + date_to_string.substring(3, 5) + "-" + date_to_string.substring(0, 2);

		db = retrive.getDatabase(1);
		return db.sql(
				"select distinct students.name,classrooms.sectionname,grades.gradename from students "
						+ "join classroom_students on classroom_students.studentid=students.id "
						+ "join class_subject_teacher on classroom_students.classid=class_subject_teacher.classid "
						+ "join classrooms on classrooms.id=classroom_students.classid "
						+ "join grades on grades.id=classrooms.gradeid "
						+ "where students.dateofbirth like ? and class_subject_teacher.teacherid=?",
				date_to_string, teacherid).results(DateOfBirthDTO.class);
>>>>>>> feature/studentbirthdays

	}

	public List<DateOfBirthDTO> getStudentsBirthDays() {

		Date dateNow = new Date();
		SimpleDateFormat dateformatJava = new SimpleDateFormat("dd-MM-yyyy");
		String date_to_string = dateformatJava.format(dateNow);
<<<<<<< HEAD
		date_to_string = date_to_string.substring(0, 5) + "%";
		

		db = retrive.getDatabase(1);
		return db.sql("select distinct students.name,classrooms.sectionname,grades.gradename from students "
				+ "join classroom_students on classroom_students.studentid=students.id and students.dateofbirth like ? "
				+ "join classrooms on classrooms.id=classroom_students.classid "
				+ "join grades on grades.id=classrooms.gradeid", date_to_string).results(DateOfBirthDTO.class);

	}

}
=======
		date_to_string = "%" + date_to_string.substring(3, 5) + "-" + date_to_string.substring(0, 2);

		db = retrive.getDatabase(1);
		return db.sql(
				"select distinct students.name,classrooms.sectionname,grades.gradename from students "
						+ "join classroom_students on classroom_students.studentid=students.id  "
						+ "join classrooms on classrooms.id=classroom_students.classid "
						+ "join grades on grades.id=classrooms.gradeid " + "where students.dateofbirth like ? ",
				date_to_string).results(DateOfBirthDTO.class);

	}

	public List<DateOfBirthDTO> getBirthDaysOfMyClassStudents(ClassRoom classRoom) {
		long gradeid = classRoom.getGradeid();
		String sectionname = classRoom.getSectionname();
		db = retrive.getDatabase(1);
		long teacherid = db.where("gradeid=? and sectionname=?", gradeid, sectionname).results(ClassRoom.class).get(0)
				.getClassteacherid();
		Date dateNow = new Date();
		SimpleDateFormat dateformatJava = new SimpleDateFormat("dd-MM-yyyy");
		String date_to_string = dateformatJava.format(dateNow);
		date_to_string = "%" + date_to_string.substring(3, 5) + "-" + date_to_string.substring(0, 2);
		return this.getBirthdays(date_to_string, teacherid);
	}

	public List<DateOfBirthDTO> getTomorrowBirthDaysOfMyClassStudents(ClassRoom classRoom) {

		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		SimpleDateFormat dateformatJava = new SimpleDateFormat("dd-MM-yyyy");
		String date_to_string = dateformatJava.format(dt);
		db = retrive.getDatabase(1);
		long teacherid = db.where("gradeid=? and sectionname=?", classRoom.getGradeid(), classRoom.getSectionname())
				.results(ClassRoom.class).get(0).getClassteacherid();
		date_to_string = "%" + date_to_string.substring(3, 5) + "-" + date_to_string.substring(0, 2);
		return this.getBirthdays(date_to_string, teacherid);

	}

	public List<DateOfBirthDTO> getBirthdays(String date_to_string, long teacherid) {
		db = retrive.getDatabase(1);
		return db.sql(
				"select distinct students.name,students.id,students.admissionnumber,classrooms.sectionname,grades.gradename from students "
						+ "join classroom_students on classroom_students.studentid=students.id "
						+ "join classrooms on classrooms.id=classroom_students.classid "
						+ "join grades  on grades.id=classrooms.gradeid "
						+ "where classrooms.classteacherid=? and students.dateofbirth like ?",
				teacherid, date_to_string).results(DateOfBirthDTO.class);

	}

}
>>>>>>> feature/studentbirthdays
