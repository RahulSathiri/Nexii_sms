package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.SubjectTeacherClass;
import com.omniwyse.sms.models.Subjects;
import com.omniwyse.sms.models.Teachers;
import com.omniwyse.sms.utils.ClassSectionTransferObject;

@Service
public class SubjectTeacherClassService {

	@Autowired
    private com.omniwyse.sms.db.DatabaseRetrieval database;
	public Database db;

	public void addingSubjectTeacher( long tenantId) {

		db = database.getDatabase(tenantId);

		List<SubjectTeacherClass> list = db
				.sql("select subjects.id, teachers.id, classes.id from subjects INNER JOIN teachers ON subjects.id=teachers.id INNER JOIN classes ON subjects.id=classes.id")
				.results(SubjectTeacherClass.class);
		SubjectTeacherClass stc = new SubjectTeacherClass();

		for (int var = 0; var < list.size(); var++) {

			stc.setClassid(list.get(var).getClassid());
			stc.setSubjectid(list.get(var).getSubjectid());
			stc.setTeacherid(list.get(var).getTeacherid());
			db.insert(stc);
		}
	}

	public List<ClassSectionTransferObject> listOfSubjectsTeachers( long classid, long tenantId) {

		db = database.getDatabase(tenantId);

		List<ClassSectionTransferObject> list = db
				.sql("select subjects.subjectname, teachers.teachername from subjects left JOIN class_subject_teacher "
						+ " ON class_subject_teacher.subjectid = subjects.id left JOIN teachers ON "
						+ "class_subject_teacher.teacherid=teachers.id where class_subject_teacher.classid=?",
						classid)
				.results(ClassSectionTransferObject.class);
		return list;
	}

	public List<Subjects> getListOfSubjects(long gradeid,long tenantId) {
		db = database.getDatabase(tenantId);
		return db
				.sql("select subjects.subjectname from subjects inner join grade_subjects"
						+ " on grade_subjects.subjectid=subjects.id and grade_subjects.gradeid=?",
						gradeid)
				.results(Subjects.class);

	}

	public int assignTeacherToSubject( long tenantId, long classid, String teachername, String subjectname) {
		db = database.getDatabase(tenantId);

		long subjectid = db.where("subjectname = ?", subjectname).results(Subjects.class).get(0).getId();
		long id=db.where("subjectid=? and classid=?" , subjectid,classid).results(SubjectTeacherClass.class).get(0).getId();
		long teacherid = db.where("teachername = ?", teachername).results(Teachers.class).get(0).getId();
List<SubjectTeacherClass> records=db.where("classid=? and subjectid=? and teacherid=?",classid,subjectid,teacherid).results(SubjectTeacherClass.class);
if(records.isEmpty()){
		SubjectTeacherClass stc = new SubjectTeacherClass();
		stc.setId(id);
		stc.setClassid(classid);
		stc.setSubjectid(subjectid);
		stc.setTeacherid(teacherid);
	return	db.update(stc).getRowsAffected();
}
return 0;

	}

	public int updateSubjectTeacher( long tenantId, long classid, String subjectname, String teachername) {
		db = database.getDatabase(tenantId);
		long subjectid = db.where("subjectname=?", subjectname).results(Subjects.class).get(0).getId();
		long teacherid = db.where("teachername=?", teachername).results(Teachers.class).get(0).getId();
		SubjectTeacherClass stc = new SubjectTeacherClass();
		long stid = db.where("classid=? and subjectid=?", classid, subjectid).results(SubjectTeacherClass.class).get(0)
				.getId();
		stc.setClassid(classid);
		stc.setId(stid);
		stc.setTeacherid(teacherid);
		stc.setSubjectid(subjectid);
		return db.update(stc).getRowsAffected();

	}

}
