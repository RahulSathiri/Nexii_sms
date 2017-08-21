package com.omniwyse.sms.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.dieselpoint.norm.Transaction;
import com.omniwyse.sms.models.ClassRoom;
import com.omniwyse.sms.models.Grades;
import com.omniwyse.sms.models.House;
import com.omniwyse.sms.models.Parents;
import com.omniwyse.sms.models.StudentClassroom;
import com.omniwyse.sms.models.Students;
import com.omniwyse.sms.models.UserCredentials;
import com.omniwyse.sms.models.UserRoleMaintain;
import com.omniwyse.sms.models.UserRoles;
import com.omniwyse.sms.utils.ClassRoomStudents;
import com.omniwyse.sms.utils.StudentTransferObject;

@Service
public class StudentsService {

	@Autowired
	private com.omniwyse.sms.db.DatabaseRetrieval retrive;

	private Database db;

	private long gradeid;
	private String gradename;
	private String syllabustype;
	private String admissionnumber;
	private Students students;

	public int addStudent(StudentTransferObject addStudent, long tenantId) {

		db = retrive.getDatabase(tenantId);
		Transaction transaction = db.startTransaction();
		Parents parent = new Parents();
		parent.setMothername(addStudent.getMothername());
		parent.setFathername(addStudent.getFathername());
		parent.setEmailid(addStudent.getParentemailid());
		parent.setContactnumber(addStudent.getContactnumber());
		parent.setAddress(addStudent.getParentaddress());
		try {
			db.transaction(transaction).insert(parent);

			long parentid = parent.getId();
			students = new Students();
			gradename = addStudent.getGradename();
			syllabustype = addStudent.getSyllabustype();
			students.setAddress(addStudent.getAddress());
			students.setAdmissionnumber(addStudent.getAdmissionnumber());
			students.setDateofbirth(addStudent.getDateofbirth());
			students.setDateofjoining(addStudent.getDateofjoining());
			students.setEmailid(addStudent.getEmailid());
			students.setGender(addStudent.getGender());
			students.setName(addStudent.getName());
			students.setParentid(parentid);
			gradeid = db.where("gradename=? and syllabustype=?", gradename, syllabustype).results(Grades.class).get(0)
					.getId();
			List<House> house = db.where("housename=?", addStudent.getHousename()).results(House.class);
			if (!house.isEmpty()) {
				students.setHouseid(house.get(0).getId());
			}
			students.setGradeid(gradeid);
			admissionnumber = addStudent.getAdmissionnumber();

			if (isValidStudent(admissionnumber)) {
				db.transaction(transaction).insert(students);
			} else {
				return 0;
			}

			UserCredentials userCredentials = new UserCredentials();
			List<UserCredentials> mail=db.where("mail=?",addStudent.getParentemailid()).results(UserCredentials.class);
			if(mail.isEmpty())
			{
			userCredentials.setMail(addStudent.getParentemailid());
			userCredentials.setPassword(addStudent.getPassword());
			userCredentials.setStatusid(1);

			db.transaction(transaction).insert(userCredentials);

			UserRoleMaintain userRoleMaintain = new UserRoleMaintain();
			userRoleMaintain.setUserid(userCredentials.getId());
			long roleid = db.sql("select id from roles where role=?",addStudent.getRole()).results(UserRoles.class).get(0).getId();
			userRoleMaintain.setRoleid(roleid);
			db.transaction(transaction).insert(userRoleMaintain);
			transaction.commit();
			return 1;
			}
			else
			{
				return -5;
			}

		} catch (Throwable tw) {
			tw.printStackTrace();
			transaction.rollback();
			return -1;
		}

	}

	private boolean isValidStudent(String admissionnumber) {
		List<Students> students = db.where("admissionnumber=?", admissionnumber).results(Students.class);
		if (students.isEmpty())
			return true;
		else
			return false;
	}

	public int updateStudent(Students updateStudent, long tenantId) {
		db = retrive.getDatabase(tenantId);
		return db.update(updateStudent).getRowsAffected();
	}

	public int addStudentToClassroom(String admissionnumber, long classid, long tenantId) {
		StudentClassroom studentclassroom = new StudentClassroom();
		db = retrive.getDatabase(tenantId);
		long studentid = db.where("admissionnumber=?", admissionnumber).results(Students.class).get(0).getId();
		studentclassroom.setClassid(classid);
			studentclassroom.setStudentid(studentid);
			return db.insert(studentclassroom).getRowsAffected();
	}

	public List<ClassRoomStudents> getStudentsOfClassRoom(long classid, long tenantId) {
		db = retrive.getDatabase(tenantId);
		
		return db.sql("select students.name,students.id,parents.fathername,students.parentid,students.admissionnumber "
						+ "from students JOIN parents ON parents.id = students.parentid inner join "
						+ "classroom_students on classroom_students.classid=? and classroom_students.studentid=students.id",classid).results(ClassRoomStudents.class);

	}

	public List<Students> getStudentsOfGrade(long gradeid, long tenantId) {
		db = retrive.getDatabase(tenantId);
		return db.where("gradeid=?", gradeid).results(Students.class);
	}

	public List<Students> getStudentsList(long classroomid, long tenantId) {
		db = retrive.getDatabase(tenantId);
		long gradeId = db.where("id = ?", classroomid).results(ClassRoom.class).get(0).getGradeid();
		List<Students> gradestudentslist = getStudentsOfGrade(gradeId, tenantId);
		List<ClassRoomStudents> classstudentslist = getStudentsOfClassRoom(classroomid, tenantId);
		List<Students> studentslist = new ArrayList<Students>();
		int count=0;
		for (Students stu : gradestudentslist) {
			count = 0;
			for (ClassRoomStudents s : classstudentslist) {
				if (stu.getId() == s.getId()) {
					count++;
					break;
				}
			}
			if (count == 0) {
				studentslist.add(stu);
			}

		}
		return studentslist;
	}
}
