package com.omniwyse.sms.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.AcademicYears;
import com.omniwyse.sms.models.ClassRoom;
import com.omniwyse.sms.models.GradeSubjects;
import com.omniwyse.sms.models.Grades;
import com.omniwyse.sms.models.SubjectTeacherClass;
import com.omniwyse.sms.models.Teachers;
import com.omniwyse.sms.utils.ClassSectionTransferObject;

@SuppressWarnings("unused")
@Service
public class ClassService {

	@Autowired
	private DatabaseRetrieval retrieve;

	private SubjectTeacherClass stc;
	private Database db;
	private long academicyear;
	private String sectionname;
	private String teachername;
	private long teacherid;
	private String gradename;
	private String syllabustype;
	private String yearfromto;
	private long gradeid;

	public int createClass(ClassSectionTransferObject createclass) {
		academicyear = createclass.getAcademicyear();
		sectionname = createclass.getSectionname();
		teachername = createclass.getTeachername();
		gradename = createclass.getGradename();
		syllabustype = createclass.getSyllabustype();
		db = retrieve.getDatabase(1);
		List<Grades> record = db.where("gradename=? and syllabustype=?", gradename, syllabustype).results(Grades.class);
		if (record.isEmpty()) {
			return -5;
		}
		gradeid = record.get(0).getId();
		ClassRoom classes = new ClassRoom();
		classes.setAcademicyear(academicyear);

		classes.setSectionname(sectionname);
		classes.setGradeid(gradeid);

		List<ClassRoom> records = db
				.where("academicyear = ? and sectionname=? and gradeid=?", academicyear, sectionname, gradeid)
				.results(ClassRoom.class);
		if (records.isEmpty()) {
			if (isValidTeachername(teachername)) {

				classes.setClassteacherid(teacherid);

				db.insert(classes).getRowsAffected();

				long classid = classes.getId();
				stc = new SubjectTeacherClass();
				List<GradeSubjects> subjectids = db.where("gradeid=?", gradeid).results(GradeSubjects.class);
				for (GradeSubjects subjectid : subjectids) {
					stc.setClassid(classid);
					stc.setSubjectid(subjectid.getSubjectid());
					db.insert(stc);

				}
				return 1;
			} else {
				return 0;
			}
		} else
			return -1;

	}

	private boolean isValidTeachername(String teachername) {

		List<Teachers> teacher = db.where("teachername = ?", teachername).results(Teachers.class);
		if (teacher.isEmpty()) {
			return false;
		} else {
			teacherid = teacher.get(0).getId();
			return isExist(academicyear, teacherid);
		}
	}

	private boolean isExist(long academicyear, long teacherid) {
		List<ClassRoom> teachersids = db.where("academicyear = ?", academicyear).results(ClassRoom.class);
		for (ClassRoom classes : teachersids) {
			if (classes.getClassteacherid() == teacherid) {
				return false;
			}
		}
		return true;
	}

	public int updateClassTeacher(ClassSectionTransferObject updateclass) {

		String teachername = updateclass.getTeachername();
		db = retrieve.getDatabase(1);
		ClassRoom classes = new ClassRoom();
		long teacherid = db.where("teachername=?", teachername).results(Teachers.class).get(0).getId();
		long academicyear = updateclass.getAcademicyear();
		if (isExist(academicyear, teacherid)) {
			classes.setAcademicyear(updateclass.getAcademicyear());
			classes.setSectionname(updateclass.getSectionname());
			classes.setId(updateclass.getId());
			classes.setClassteacherid(teacherid);
			db.update(classes).execute();

		} else {
			return -1;
		}
		return 1;

	}

	public List<ClassSectionTransferObject> getClassRoomsByYearAndSyllabustype(long academicyear, String syllabustype) {
		db = retrieve.getDatabase(1);

		return db
				.sql("select classrooms.id,classrooms.academicyear,classrooms.gradeid,grades.gradenumber,grades.gradename,classrooms.sectionname,grades.syllabustype,teachers.teachername from classrooms inner join grades on classrooms.academicyear=? and grades.syllabustype=? INNER JOIN teachers ON classrooms.classteacherid = teachers.id where classrooms.gradeid=grades.id",
						academicyear, syllabustype)
				.results(ClassSectionTransferObject.class);

	}

	public List<ClassSectionTransferObject> getClassRooms() {
		db = retrieve.getDatabase(1);

		return db
				.sql("select classrooms.id,classrooms.academicyear,classrooms.gradeid,grades.gradenumber,grades.gradename,classrooms.sectionname,grades.syllabustype,teachers.teachername from classrooms inner join grades INNER JOIN teachers ON classrooms.classteacherid = teachers.id where classrooms.gradeid=grades.id")
				.results(ClassSectionTransferObject.class);

	}

	public List<ClassSectionTransferObject> getClassRoomsByYear(long academicyear) {

		db = retrieve.getDatabase(1);
		List<ClassSectionTransferObject> classes = db
				.sql("select classrooms.id,classrooms.academicyear,classrooms.gradeid,grades.gradenumber,grades.gradename,classrooms.sectionname,grades.syllabustype,teachers.teachername from classrooms inner join grades on classrooms.academicyear=? INNER JOIN teachers ON classrooms.classteacherid = teachers.id where classrooms.gradeid=grades.id",
						academicyear)
				.results(ClassSectionTransferObject.class);

		return classes;
	}

	public List<AcademicYears> getAcademicYears() {
		db = retrieve.getDatabase(1);
		return db.sql("select * from academicyears").results(AcademicYears.class);
	}

	public int addAcademicYears(AcademicYears academicyears) {
		db = retrieve.getDatabase(1);
		long academicyear = academicyears.getAcademicyear();
		List<AcademicYears> list = db.where("academicyear=?", academicyear).results(AcademicYears.class);
		if (list.isEmpty()) {
			return db.insert(academicyears).getRowsAffected();
		} else {
			return 0;
		}
	}

	public void updateAcademicYear(AcademicYears academicyears) {
		db = retrieve.getDatabase(1);
		db.update(academicyears).getRowsAffected();

	}

}