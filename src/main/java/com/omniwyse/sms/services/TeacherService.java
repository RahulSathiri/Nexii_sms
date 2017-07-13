package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.Teachers;

@Service
public class TeacherService {

	@Autowired
    private com.omniwyse.sms.db.DatabaseRetrieval retrive;
	private Database db;

	public int addTeacher(Teachers teacher) {

		db = retrive.getDatabase(1);

		String email = teacher.getEmailid();
		String teachername = teacher.getTeachername();
		if (isValidTeacherName(teachername)) {

			if (isValidTeacher(email)) {
				return db.insert(teacher).getRowsAffected();
			} else
				return 0;
		}
		return -1;
	}

	private boolean isValidTeacherName(String teachername) {

		List<Teachers> teachers = db.where("teachername = ?", teachername).results(Teachers.class);
		if (teachers.isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean isValidTeacher(String email) {

		List<Teachers> teacher = db.where("emailid=?", email).results(Teachers.class);
		if (teacher.isEmpty()) {
			return true;
		} else

			return false;
	}

	public int updateTeacher(Teachers updateTeacher) {
		db = retrive.getDatabase(1);
		// String email = updateTeacher.getEmailid();

		return db.update(updateTeacher).where("id = ?", updateTeacher.getId()).getRowsAffected();
	}

}
