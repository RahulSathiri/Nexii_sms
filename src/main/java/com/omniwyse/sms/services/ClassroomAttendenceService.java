package com.omniwyse.sms.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.dieselpoint.norm.Transaction;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.ClassroomAttendance;
import com.omniwyse.sms.models.Students;
import com.omniwyse.sms.utils.AttendanceDTO;
import com.omniwyse.sms.utils.ClassAttendenceTransferObject;

@Service
public class ClassroomAttendenceService {

	@Autowired
	private DatabaseRetrieval retrive;

	private Database db;

	public int addingAttendanceStatus(List<ClassAttendenceTransferObject> classattendancetransferobject) {
		int rowEffected = 0;
		db = retrive.getDatabase(1);
		ClassroomAttendance classroomattendance = new ClassroomAttendance();

		Transaction transact = db.startTransaction();
		try {

			for (ClassAttendenceTransferObject studentobj : classattendancetransferobject) {
				String studentname = studentobj.getName();
				long classid = studentobj.getId();
				long studentid = db.where("name=?", studentname).results(Students.class).get(0).getId();
				classroomattendance.setDateofattendance(studentobj.getDateofattendance());
				classroomattendance.setAttendancestatus(studentobj.getAttendancestatus());
				classroomattendance.setClassroomid(classid);
				classroomattendance.setStudentid(studentid);
				rowEffected = db.transaction(transact).insert(classroomattendance).getRowsAffected();
			}
			transact.commit();

		} catch (Exception e) {
			transact.rollback();

		}

		return rowEffected;
	}

	public List<ClassAttendenceTransferObject> recordsOfAttendance(
			ClassAttendenceTransferObject classattendancetransferobject) {

		List<ClassAttendenceTransferObject> classATO;

		db = retrive.getDatabase(1);

		long classid = classattendancetransferobject.getId();

		classATO = db.sql("select students.id as studentid,students.name,(SELECT DATE_FORMAT(classroom_attendance.dateofattendance,'%M %Y')) AS showdate from students "
						+ "JOIN classroom_attendance ON  students.id = classroom_attendance.studentid "
						+ " JOIN classroom_students ON students.id = classroom_students.studentid "
						+ "where classid =?  group by students.id", classid)
				.results(ClassAttendenceTransferObject.class);
		
		for (ClassAttendenceTransferObject presentclassATO : classATO) {
			presentclassATO.setStudentattendance(db.sql("select students.id, classroom_attendance.dateofattendance, classroom_attendance.attendancestatus,"
							+ "day(classroom_attendance.dateofattendance) as day"
							+   " from students JOIN classroom_attendance ON  students.id = classroom_attendance.studentid"
							+ " JOIN classroom_students ON students.id = classroom_students.studentid where classid =?  and students.id=?",
					classid, presentclassATO.getId()).results(AttendanceDTO.class));
		}

		return classATO;
	}

}