package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.ClassRoom;
import com.omniwyse.sms.models.ClassroomTestResult;
import com.omniwyse.sms.models.GradeSubjects;
import com.omniwyse.sms.models.StudentTestResult;
import com.omniwyse.sms.models.Students;
import com.omniwyse.sms.models.Subjects;
import com.omniwyse.sms.models.TestCreate;
import com.omniwyse.sms.models.TestType;
import com.omniwyse.sms.utils.MainResultsTransferObject;
import com.omniwyse.sms.utils.ResultsTransferObject;
import com.omniwyse.sms.utils.StudentSubjectMarks;

@Service
public class ResultsService {
    @Autowired
    private DatabaseRetrieval retrieve;
    private Database db;

    public MainResultsTransferObject viewResults(ResultsTransferObject resultstransferobject,long tenantId) {
        db = retrieve.getDatabase(tenantId);

        long classid = resultstransferobject.getId();
        long gradeid = db.where("id=?", classid).results(ClassRoom.class).get(0).getGradeid();

        String testtype = resultstransferobject.getTesttype();
        long testid = db.where("testtype=?", testtype).results(TestType.class).get(0).getId();

        List<Students> studentsidlist = db.sql("select students.id from students inner join classroom_students on classroom_students.classid=?"
                        + " and classroom_students.studentid=students.id", classid).results(Students.class);

        List<GradeSubjects> listsubjects = db.sql("select subjectid from grade_subjects where gradeid=?", gradeid)
                .results(GradeSubjects.class);
        List<TestCreate> testcreaterecords = db.where("gradeid=? and testtypeid=?", gradeid, testid).results(
                TestCreate.class);
        long testcreateid = testcreaterecords.get(0).getId();
        List<ClassroomTestResult> existingclassroomtestresult = db.where("testid=?", testcreateid).results(
                ClassroomTestResult.class);
        if (existingclassroomtestresult.isEmpty()) {
            for (Students std : studentsidlist) {
                ClassroomTestResult classroomtestresult = new ClassroomTestResult();
                classroomtestresult.setClassid(classid);
                classroomtestresult.setTestid(testcreateid);
                classroomtestresult.setStudentid(std.getId());

                for (GradeSubjects grdsub : listsubjects) {
                    StudentTestResult studenttestresult = new StudentTestResult();
                    studenttestresult.setTestid(testcreateid);
                    studenttestresult.setClassid(classid);
                    studenttestresult.setStudentid(std.getId());
                    studenttestresult.setSubjectid(grdsub.getSubjectid());
                    db.insert(studenttestresult);
                }
                db.insert(classroomtestresult);

            }
        }

        List<ResultsTransferObject> resulttransferlist = db.sql("select distinct cltrs.studentid as studentid,st.name,tt.testtype,cltrs.resultorgrade "
                        + "from classroom_testresult cltrs "
                        + "left join test_create tc on tc.testtypeid = cltrs.testid"
                        + "  left join grade_subjects gs on gs.gradeid = tc.gradeid "
                        + "left join students st on cltrs.studentid=st.id"
                        + " left join test_syllabus ts on ts.subjectid = gs.subjectid"
                        + " left join test_type tt on tt.id = cltrs.testid where cltrs.testid=? and gs.gradeid=?",testcreateid, gradeid).results(ResultsTransferObject.class);

        for (ResultsTransferObject rto : resulttransferlist) {
            rto.setStudentsubjectmarks(db.sql("select sb.subjectname,str.marks from student_testresult str "
                            + "left join subjects sb on str.subjectid = sb.id"
                            + " where str.classid=? and str.testid=? and str.studentid=?", classid, testid,rto.getStudentid()).results(StudentSubjectMarks.class));
        }

        List<Subjects> subjectlist = db.sql("select sb.subjectname from grade_subjects gs " + "left join subjects sb on sb.id = gs.subjectid "
                        + "where gs.gradeid=?", gradeid).results(Subjects.class);

        MainResultsTransferObject mainresults = new MainResultsTransferObject();
        mainresults.setResulttransfer(resulttransferlist);
        mainresults.setSubjects(subjectlist);
        return mainresults;

    }

    public List<ResultsTransferObject> enterMarks(ResultsTransferObject resultstransferobject,long tenantId) {

        db = retrieve.getDatabase(tenantId);

        long classid = resultstransferobject.getId();
        long gradeid = db.where("id=?", classid).results(ClassRoom.class).get(0).getGradeid();

        String testtype = resultstransferobject.getTesttype();
        long testid = db.where("testtype=?", testtype).results(TestType.class).get(0).getId();

        String subjectname = resultstransferobject.getSubjectname();
        long subjectid = db.where("subjectname=? ", subjectname).results(Subjects.class).get(0).getId();

        List<TestCreate> testcreaterecords = db.where("gradeid=? and testtypeid=?", gradeid, testid).results(
                TestCreate.class);

        long testcreateid = testcreaterecords.get(0).getId();

        return db
                .sql("select distinct str.studentid as studentid,str.testid,str.subjectid,str.id,str.marks,st.name as studentname, ts.maxmarks,str.classid from student_testresult str "
                        + "left join classroom_testresult cltrs on str.testid = cltrs.testid left join test_create tc on tc.testtypeid = str.testid "
                        + "left join grade_subjects gs on gs.gradeid = tc.gradeid left join students st on str.studentid=st.id "
                        + "left join test_syllabus ts on ts.subjectid = gs.subjectid left join test_type tt on tt.id = str.testid "
                        + "where ts.testid=? and str.subjectid=? and cltrs.classid=?", testcreateid, subjectid, classid).results(ResultsTransferObject.class);

    }

    public int addMarks(List<ResultsTransferObject> resultstransferobject,long tenantId) {

        db = retrieve.getDatabase(tenantId);

        int existingsubjectcount = 0;
        int marksenteredsubjectcount = 0;

        if (!resultstransferobject.isEmpty()) {
            for (ResultsTransferObject resultobj : resultstransferobject) {

                long classid = resultobj.getClassid();

                if (existingsubjectcount == 0) {
                    long gradeid = db.where("id=?", classid).results(ClassRoom.class).get(0).getGradeid();
                    List<GradeSubjects> gradeexistingsubjects = db.sql(
                            "select subjectid from grade_subjects where gradeid=?", gradeid).results(
                            GradeSubjects.class);
                    existingsubjectcount = gradeexistingsubjects.size();
                }

                if (marksenteredsubjectcount == 0) {
                    List<StudentTestResult> markseneteredsubjectslist = db.sql(
                            "select marks from student_testresult where marks !=0  and studentid=?",
                            resultobj.getStudentid()).results(StudentTestResult.class);
                    marksenteredsubjectcount = markseneteredsubjectslist.size();
                }

                long testid = resultobj.getTestid();
                long subjectid = resultobj.getSubjectid();

                StudentTestResult studenttestresult = new StudentTestResult();
                ClassroomTestResult classroomtestresult = new ClassroomTestResult();

                studenttestresult.setId(resultobj.getId());
                studenttestresult.setTestid(testid);
                studenttestresult.setStudentid(resultobj.getStudentid());
                studenttestresult.setSubjectid(subjectid);
                studenttestresult.setMarks(resultobj.getMarks());
                studenttestresult.setClassid(classid);

                db.update(studenttestresult).getRowsAffected();

                if (existingsubjectcount == marksenteredsubjectcount) {
                    classroomtestresult.setClassid(classid);
                    classroomtestresult.setTestid(testid);
                    classroomtestresult.setStudentid(resultobj.getStudentid());
                    classroomtestresult.setResultorgrade("A");
                    db.update(classroomtestresult);
                }

            }
            return 1;
        } else {
            return 0;
        }

    }
}
