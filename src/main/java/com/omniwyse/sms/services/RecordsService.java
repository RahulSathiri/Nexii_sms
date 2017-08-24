package com.omniwyse.sms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.ClassRoom;
import com.omniwyse.sms.models.Grades;
import com.omniwyse.sms.models.Subjects;
import com.omniwyse.sms.models.Syllabus;
import com.omniwyse.sms.models.Teachers;
import com.omniwyse.sms.models.UserRoles;
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

	public List<Teachers> getAllTeachers(long tenantId) {
		db = retrive.getDatabase(tenantId);
		List<Teachers> teachers = db.sql("select * from teachers").results(Teachers.class);
		return teachers;

	}

	public List<StudentTransferObject> getAllStudents(long tenantId) {
		db = retrive.getDatabase(tenantId);
		List<StudentTransferObject> students = db
				.sql("select grades.gradename,houses.housename,students.houseid,students.gradeid,students.parentid,"
						+ "grades.syllabustype,students.name,parents.fathername,students.dateofbirth,students.dateofjoining,students.id,"
						+ "students.gender,parents.contactnumber,parents.mothername,students.admissionnumber,students.emailid,"
						+ "students.address,students.id from students left join grades on students.gradeid=grades.id "
						+ "join parents on parents.id=students.parentid left join houses on houses.id=students.houseid")
				.results(StudentTransferObject.class);
		return students;
	}

	public List<Subjects> getAllSubjects(long tenantId) {

		db = retrive.getDatabase(tenantId);
		List<Subjects> subjects = db.sql("select * from subjects").results(Subjects.class);
		return subjects;
	}

	public List<Subjects> getAllSections(long tenantId) {

		db = retrive.getDatabase(tenantId);
		List<Subjects> subjects = db.sql("select * from sections").results(Subjects.class);
		return subjects;
	}

	public ClassRoomDetails getClassRoomDetails(long classid, long gradeid,long tenantId) {
		ClassRoomDetails classroomdetails = new ClassRoomDetails();
		classroomdetails.setSubjects(service.getListOfSubjects(gradeid,tenantId));
		classroomdetails.setSubjectteachers(service.listOfSubjectsTeachers(classid,tenantId));
		classroomdetails.setStudentsOfClassRoom(studentservice.getStudentsOfClassRoom(classid,tenantId));
		return classroomdetails;
	}

	public List<Syllabus> getAllSyllabus(long tenantId) {
		db = retrive.getDatabase(tenantId);
		List<Syllabus> syllabus = db.sql("select * from syllabus").results(Syllabus.class);
		return syllabus;
	}

    public List<Grades> getAllClassRoomSyllabusTypes(long tenantId) {
        db = retrive.getDatabase(tenantId);
        List<Grades> syllabus = db.sql("select distinct syllabustype from grades").results(Grades.class);
        return syllabus;
    }

    public List<ClassRoom> getAcademicYears(long tenantId) {
        db = retrive.getDatabase(tenantId);
        return db.sql("select distinct academicyear from classrooms").results(ClassRoom.class);

    }

    public List<UserRoles> getroles(long tenantId, int[] roleid) {		
		List<UserRoles> roles=new ArrayList<UserRoles>();
		db = retrive.getDatabase(tenantId);
		for(int id:roleid)
		{
			roles.add(db.where("id=?",id).results(UserRoles.class).get(0));
		}
		return roles;
	}

	
}
