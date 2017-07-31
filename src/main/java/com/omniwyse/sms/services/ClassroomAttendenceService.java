package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.dieselpoint.norm.Transaction;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.ClassRoom;
import com.omniwyse.sms.models.ClassroomAttendance;
import com.omniwyse.sms.models.Students;
import com.omniwyse.sms.utils.ClassAttendenceTransferObject;

@Service
public class ClassroomAttendenceService {

	@Autowired
	private DatabaseRetrieval retrive;

	@Autowired
	private StudentsService studentService;

	private Database db;

	// list of students for the classroom attendance

	public ClassAttendenceTransferObject studentsList(long tenantId, ClassAttendenceTransferObject classattendancetransferobject) {

		db = retrive.getDatabase(tenantId);
		long gradeid = classattendancetransferobject.getGradeid();
		String sectionname = classattendancetransferobject.getSectionname();
		long classid = db.where("gradeid=? and sectionname=?", gradeid, sectionname).results(ClassRoom.class).get(0)
				.getId();

		classattendancetransferobject.setStudentsOfClassRoom(studentService.getStudentsOfClassRoom(classid,tenantId));

		return classattendancetransferobject;
	}

	// Record students attendance

	public int addingAttendanceStatus(long tenantId, List<ClassAttendenceTransferObject> classattendancetransferobject) {

		db = retrive.getDatabase(tenantId);
		Transaction transact = db.startTransaction();

		ClassAttendenceTransferObject classattendance = classattendancetransferobject.get(0);

		try {
			long classroomid = db.where("gradeid=? and sectionname=?", classattendance.getGradeid(),
					classattendance.getSectionname()).results(ClassRoom.class).get(0).getId();

			for (ClassAttendenceTransferObject attendencerecords : classattendancetransferobject) {

				ClassroomAttendance classroomAttendance = new ClassroomAttendance();
				classroomAttendance.setClassroomid(classroomid);
				classroomAttendance.setStudentid(attendencerecords.getId());
				classroomAttendance.setDateofattendance(attendencerecords.getDateofattendance());
				classroomAttendance.setAttendancestatus(attendencerecords.getAttendancestatus());
				List<ClassroomAttendance> attendance=db.sql("select * from classroom_attendance where classroomid=? and dateofattendance=? and studentid=?",
						classroomid,attendencerecords.getDateofattendance(),attendencerecords.getId()).results(ClassroomAttendance.class);
				if(attendance.isEmpty()){
				db.transaction(transact).insert(classroomAttendance);
			
			}else
			{
				return -1;
			}
			}
		
			transact.commit();
		} catch (Throwable tr) {
			transact.rollback();
			tr.printStackTrace();
		}

		return 1;
	}

	// dates list

	public List<ClassroomAttendance> getdates(long tenantId) {

		db = retrive.getDatabase(tenantId);
		return db.sql("select distinct dateofattendance from classroom_attendance").results(ClassroomAttendance.class);
	}

	// view attendance

	public ClassAttendenceTransferObject getAttendance(long tenantId, ClassAttendenceTransferObject classattendancetransferobject) {
		ClassAttendenceTransferObject attendancereport = new ClassAttendenceTransferObject();
		db = retrive.getDatabase(tenantId);

		long classroomid = db.where("gradeid=? and sectionname=?", classattendancetransferobject.getGradeid(),
				classattendancetransferobject.getSectionname()).results(ClassRoom.class).get(0).getId();

		attendancereport.setDateofattendance(classattendancetransferobject.getDateofattendance());

		Long studentscount = db
				.sql("select count(*) as count from classroom_attendance where classroomid=? and dateofattendance=?",
						classroomid, classattendancetransferobject.getDateofattendance())
				.first(Long.class);

		attendancereport.setNoofstudents(studentscount);

		long status = 0;
		Long absentiescount = db
				.sql("select count(*) as count from classroom_attendance where classroomid=? and dateofattendance=? and attendancestatus=?",
						classroomid, classattendancetransferobject.getDateofattendance(), status)
				.first(Long.class);
		attendancereport.setNoofabsents(absentiescount);

		List<Students> absentiesnames = db.sql(
					"select students.id,students.name,students.contactnumber from classroom_attendance " 
					+"join students on classroom_attendance.studentid=students.id "
					+ "and classroom_attendance.classroomid=? and  classroom_attendance.dateofattendance=? "
					+ "and classroom_attendance.attendancestatus=?",
				classroomid,classattendancetransferobject.getDateofattendance(),status).results(Students.class);

		attendancereport.setStudents(absentiesnames);
		status = 1;
		Long presentiescount = db
				.sql("select count(*) as count from classroom_attendance where classroomid=? and dateofattendance=? and attendancestatus=?",
						classroomid, classattendancetransferobject.getDateofattendance(), status)
				.first(Long.class);

		attendancereport.setNoofpresents(presentiescount);
		return attendancereport;
	}

}