package com.omniwyse.sms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.dieselpoint.norm.Transaction;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.AttendanceMode;
import com.omniwyse.sms.models.AttendanceSubjectwise;
import com.omniwyse.sms.models.ClassRoom;
import com.omniwyse.sms.models.ClassroomAttendance;
import com.omniwyse.sms.models.Students;
import com.omniwyse.sms.models.Subjects;
import com.omniwyse.sms.utils.ClassAttendenceTransferObject;
import com.omniwyse.sms.utils.ClassSectionTransferObject;
import com.omniwyse.sms.utils.TeacherModuleDTO;

@Service
public class ClassroomAttendenceService {

	@Autowired
	private DatabaseRetrieval retrive;

	@Autowired
	private StudentsService studentService;

	private Database db;

	// list of students for the classroom attendance

	public ClassAttendenceTransferObject studentsList(long tenantId,
			ClassAttendenceTransferObject classattendancetransferobject) {

		db = retrive.getDatabase(tenantId);
		long gradeid = classattendancetransferobject.getGradeid();
		String sectionname = classattendancetransferobject.getSectionname();
		long classid = db.where("gradeid=? and sectionname=?", gradeid, sectionname).results(ClassRoom.class).get(0)
				.getId();

		classattendancetransferobject.setStudentsOfClassRoom(studentService.getStudentsOfClassRoom(classid, tenantId));

		return classattendancetransferobject;
	}

	// Record students attendance

	public int addingAttendanceStatus(long tenantId,
			List<ClassAttendenceTransferObject> classattendancetransferobject) {

		db = retrive.getDatabase(tenantId);
		// long attendanceid=db.sql("select * from
		// school_attendance").results(AttendanceMode.class).get(0).getId();
		// if(attendanceid!=1){
		Transaction transact = db.startTransaction();

		ClassAttendenceTransferObject classattendance = classattendancetransferobject.get(0);

		try {
			long classroomid = db.where("gradeid=? and sectionname=?", classattendance.getGradeid(),
					classattendance.getSectionname()).results(ClassRoom.class).get(0).getId();
			long attendanceid = db.sql("select * from school_attendance where status=1")
					.results(AttendanceMode.class).get(0).getId();
			if (attendanceid != 1) {

				for (ClassAttendenceTransferObject attendencerecords : classattendancetransferobject) {

					ClassroomAttendance classroomAttendance = new ClassroomAttendance();
					classroomAttendance.setClassroomid(classroomid);
					classroomAttendance.setStudentid(attendencerecords.getId());
					classroomAttendance.setDateofattendance(attendencerecords.getDateofattendance());
					classroomAttendance.setAttendancestatus(attendencerecords.getAttendancestatus());
					List<ClassroomAttendance> attendance = db
							.sql("select * from classroom_attendance where classroomid=? and dateofattendance=? and studentid=?",
									classroomid, attendencerecords.getDateofattendance(), attendencerecords.getId())
							.results(ClassroomAttendance.class);
					if (attendance.isEmpty()) {
						db.transaction(transact).insert(classroomAttendance);
						
					} else {
						return -1;
					}
				}
	
			} else {

				for (ClassAttendenceTransferObject attendencerecords : classattendancetransferobject) {

					AttendanceSubjectwise classroomAttendance = new AttendanceSubjectwise();
					classroomAttendance.setClassroomid(classroomid);
					classroomAttendance.setStudentid(attendencerecords.getId());
					classroomAttendance.setDateofattendance(attendencerecords.getDateofattendance());
					classroomAttendance.setAttendancestatus(attendencerecords.getAttendancestatus());
					long subjectid = db.sql("select * from subjects where subjectname=?", attendencerecords.getSubjectname()).results(Subjects.class)
							.get(0).getId();
					classroomAttendance.setSubjectid(subjectid);
					List<AttendanceSubjectwise> attendance = db
							.sql("select * from attendance_subjectwise where classroomid=? and dateofattendance=? and studentid=?",
									classroomid, attendencerecords.getDateofattendance(), attendencerecords.getId())
							.results(AttendanceSubjectwise.class);
					if (attendance.isEmpty()) {
						db.transaction(transact).insert(classroomAttendance);
						
					} else {
						return -1;
					}
					
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
		long attendanceid = db.sql("select * from school_attendance  where status=1").results(AttendanceMode.class).get(0).getId();
		if (attendanceid != 1) {
			return db.sql("select distinct dateofattendance from classroom_attendance")
					.results(ClassroomAttendance.class);
		} else {
			List<AttendanceSubjectwise> listdates = db
					.sql("select distinct dateofattendance from attendance_subjectwise")
					.results(AttendanceSubjectwise.class);
			List<ClassroomAttendance> dates = null;
			ClassroomAttendance classroomattendance = new ClassroomAttendance();
			for (AttendanceSubjectwise date : listdates) {
				classroomattendance.setDateofattendance(date.getDateofattendance());
				dates.add(classroomattendance);
			}
			return dates;
		}
	} 

	// view attendance

	@SuppressWarnings("null")
	public List<ClassAttendenceTransferObject> getAttendance(long tenantId,long gradeid,String sectionname, String subjectname) {
		List<ClassAttendenceTransferObject> attendancedetails=new ArrayList<ClassAttendenceTransferObject>();
		
		db = retrive.getDatabase(tenantId);
		long attendanceid = db.sql("select * from school_attendance where status=1").results(AttendanceMode.class).get(0).getId();
		if (attendanceid != 1) {
			
 			long classroomid = db.where("gradeid=? and sectionname=?",gradeid,
					sectionname).results(ClassRoom.class).get(0).getId();
			List<ClassroomAttendance> onetimedates=db.sql("select distinct dateofattendance from classroom_attendance").results(ClassroomAttendance.class);
		for(ClassroomAttendance date:onetimedates){
			ClassAttendenceTransferObject attendancereport = new ClassAttendenceTransferObject();
			attendancereport.setDateofattendance(date.getDateofattendance());

			Long studentscount = db
					.sql("select count(*) as count from classroom_attendance where classroomid=? and dateofattendance=?",
							classroomid, date.getDateofattendance())
					.first(Long.class);

			attendancereport.setNoofstudents(studentscount);

			long status = 0;
			Long absentiescount = db
					.sql("select count(*) as count from classroom_attendance where classroomid=? and dateofattendance=? and attendancestatus=?",
							classroomid, date.getDateofattendance(), status)
					.first(Long.class);
			attendancereport.setNoofabsents(absentiescount);

			List<Students> absentiesnames = db.sql(
					"select students.id,students.name,students.contactnumber from classroom_attendance "
							+ "join students on classroom_attendance.studentid=students.id "
							+ "and classroom_attendance.classroomid=? and  classroom_attendance.dateofattendance=? "
							+ "and classroom_attendance.attendancestatus=?",
					classroomid, date.getDateofattendance(), status).results(Students.class);

			attendancereport.setStudents(absentiesnames);
			status = 1;
			Long presentiescount = db
					.sql("select count(*) as count from classroom_attendance where classroomid=? and dateofattendance=? and attendancestatus=?",
							classroomid, date.getDateofattendance(), status)
					.first(Long.class);

			attendancereport.setNoofpresents(presentiescount);
			attendancedetails.add(attendancereport);
		
		}
		return attendancedetails;
		}
		else {
			long classroomid = db.where("gradeid=? and sectionname=?",gradeid,
					sectionname).results(ClassRoom.class).get(0).getId();
			List<AttendanceSubjectwise> subjectwisedates=db.sql("select distinct dateofattendance from attendance_subjectwise").results(AttendanceSubjectwise.class);
			long subjectid = db.sql("select * from subjects where subjectname=?", subjectname).results(Subjects.class)
					.get(0).getId();
			for(AttendanceSubjectwise date:subjectwisedates){
				ClassAttendenceTransferObject attendancereport = new ClassAttendenceTransferObject();

			attendancereport.setDateofattendance(date.getDateofattendance());

			Long studentscount = db
					.sql("select count(*) as count from attendance_subjectwise where classroomid=? and dateofattendance=? and subjectid=?",
							classroomid,date.getDateofattendance(),subjectid)
					.first(Long.class);

			attendancereport.setNoofstudents(studentscount);

			long status = 0;
			Long absentiescount = db
					.sql("select count(*) as count from attendance_subjectwise where classroomid=? and dateofattendance=? and attendancestatus=? and subjectid=?",
							classroomid,date.getDateofattendance(), status,subjectid)
					.first(Long.class);
			attendancereport.setNoofabsents(absentiescount);

			List<Students> absentiesnames = db.sql(
					"select students.id,students.name,students.contactnumber from attendance_subjectwise "
							+ "join students on attendance_subjectwise.studentid=students.id "
							+ "and attendance_subjectwise.classroomid=? and  attendance_subjectwise.dateofattendance=? "
							+ "and attendance_subjectwise.attendancestatus=? and subjectid=?",
					classroomid, date.getDateofattendance(), status,subjectid).results(Students.class);

			attendancereport.setStudents(absentiesnames);
			status = 1;
			Long presentiescount = db
					.sql("select count(*) as count from attendance_subjectwise where classroomid=? and dateofattendance=? and attendancestatus=? and subjectid=?",
							classroomid, date.getDateofattendance(), status,subjectid)
					.first(Long.class);

			attendancereport.setNoofpresents(presentiescount);
			attendancedetails.add(attendancereport);
		}
			return attendancedetails;
		}

	}

	// optional attendance modes
	public List<AttendanceMode> listattendancemodes(long tenantId) {
		db = retrive.getDatabase(tenantId);
		return db.sql("select id,attendance_type from school_attendance ").results(AttendanceMode.class);
	}
	//optinal attendance  status
	public String addingAttendanceStatus(long tenantId, ClassAttendenceTransferObject classattendancetransferobject) {
		db = retrive.getDatabase(tenantId);
		String attendancetype=classattendancetransferobject.getAttendance_type();
		List<AttendanceMode> status = db.where("status=1").results(AttendanceMode.class);
		if (status.isEmpty()){
		db.sql("update school_attendance set status=1 where attendance_type=?",attendancetype).execute();
		return "updated";
		}else{
			return status.get(0).getAttendance_type(); 
		}
	}
//attendance
	public List<TeacherModuleDTO> listTeacherAttendanceOption(long tenantId, ClassSectionTransferObject moduleDTO) {
		db=retrive.getDatabase(tenantId);
		long attendanceid = db.sql("select * from school_attendance where status=1").results(AttendanceMode.class).get(0).getId();
		if (attendanceid != 1) {
			
			List<TeacherModuleDTO> onetime= db.sql("select classrooms.gradeid,classrooms.sectionname from classrooms where classrooms.classteacherid=?",moduleDTO.getId()).results(TeacherModuleDTO.class);
			return onetime;
	}
		else{
			List<TeacherModuleDTO> subjectwise=db.sql("select classrooms.gradeid,classrooms.sectionname,subjects.subjectname from classrooms "
					 +"join class_subject_teacher on class_subject_teacher.classid=classrooms.id "
					+"join subjects on subjects.id=class_subject_teacher.subjectid where class_subject_teacher.teacherid=?",moduleDTO.getId()).results(TeacherModuleDTO.class);
			return subjectwise;
		}
			
		}
}