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

        List<TestCreate> testcreaterecords = db.where("gradeid=? and testtypeid=?", gradeid, testid).results(TestCreate.class);
        long testcreateid = testcreaterecords.get(0).getId();

        List<ResultsTransferObject> resulttransferlist = db.sql("select distinct cltrs.studentid as studentid,st.name,tt.testtype,cltrs.resultorgrade from classroom_testresult cltrs "
                        + "left join test_create tc on tc.testtypeid = cltrs.testid left join grade_subjects gs on gs.gradeid = tc.gradeid left join students st on cltrs.studentid=st.id"
                        + " left join test_syllabus ts on ts.subjectid = gs.subjectid left join test_type tt on tt.id = cltrs.testid where cltrs.testid=? and gs.gradeid=?",
                        testcreateid, gradeid).results(ResultsTransferObject.class);

        for (ResultsTransferObject rto : resulttransferlist) {
            rto.setStudentsubjectmarks(db.sql("select sb.subjectname,str.marks from student_testresult str left join subjects sb on str.subjectid = sb.id"
                            + " where str.classid=? and str.testid=? and str.studentid=?", classid, testid,rto.getStudentid()).results(StudentSubjectMarks.class));
        }

        List<Subjects> subjectlist = db.sql("select sb.subjectname from grade_subjects gs " + "left join subjects sb on sb.id = gs.subjectid where gs.gradeid=?", gradeid).results(Subjects.class);

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

        List<TestCreate> testcreaterecords = db.where("gradeid=? and testtypeid=?", gradeid, testid).results(TestCreate.class);

        long testcreateid = testcreaterecords.get(0).getId();

        if (!testResultList(classid, testcreateid, subjectid, tenantId).isEmpty()) {

            return db.sql("select distinct str.classid,str.studentid as studentid,st.name as studentname,str.testid,tt.testtype,str.subjectid,ts.maxmarks,str.marks from student_testresult str "
                            + "left join classroom_testresult cltrs on str.testid = cltrs.testid left join test_create tc on tc.testtypeid = str.testid"
                            + " left join grade_subjects gs on gs.gradeid = tc.gradeid left join students st on str.studentid=st.id "
                            + "left join test_syllabus ts on ts.subjectid = gs.subjectid left join test_type tt on tt.id = tc.testtypeid "
                            + "where str.testid=? and str.subjectid=? and cltrs.classid=? group by str.studentid", testcreateid, subjectid, classid).results(ResultsTransferObject.class);
        } else {
    
            List<ResultsTransferObject> resulttransferobj = db.sql("select distinct cstud.classid,cstud.studentid,stud.name as studentname,tsyl.testid as testid,tt.testtype,gsub.subjectid,tsyl.maxmarks"
                            + " from classrooms croom left join classroom_students cstud on croom.id = cstud.classid left join students stud on cstud.studentid = stud.id"
                            + " inner join grade_subjects gsub on croom.gradeid = gsub.gradeid inner join subjects sub on gsub.subjectid = sub.id inner join grades g on croom.gradeid = g.id "
                            + "inner join test_create test on g.id = test.gradeid inner join test_syllabus tsyl on gsub.subjectid = tsyl.subjectid and test.id = tsyl.testid inner join test_type tt on tt.id = test.testtypeid "
                            + "where sub.subjectname=? and test.id=? and croom.id=?  group by cstud.studentid",subjectname, testcreateid, classid).results(ResultsTransferObject.class);

            return resulttransferobj;
        }
    }

    public int addMarks(List<ResultsTransferObject> resultstransferobject,long tenantId) {

        db = retrieve.getDatabase(tenantId);

        int existingsubjectcount = 0;
        int marksenteredsubjectcount = 0;

        if (!resultstransferobject.isEmpty()) {
            for (ResultsTransferObject resultobj : resultstransferobject) {

                long classid = resultobj.getClassid();
                long testid = resultobj.getTestid();
                long subjectid = resultobj.getSubjectid();

                if (existingsubjectcount == 0 && marksenteredsubjectcount == 0) {
                    long gradeid = db.where("id=?", classid).results(ClassRoom.class).get(0).getGradeid();
                    List<GradeSubjects> gradeexistingsubjects = db.sql("select subjectid from grade_subjects where gradeid=?", gradeid).results(GradeSubjects.class);
                    existingsubjectcount = gradeexistingsubjects.size();
                    List<StudentTestResult> markseneteredsubjectslist = db.sql("select marks from student_testresult where marks !=0  and studentid=?",resultobj.getStudentid()).results(StudentTestResult.class);
                    marksenteredsubjectcount = markseneteredsubjectslist.size();
                }

                List<StudentTestResult> studenttestresultlist = db.where("classid=? and subjectid=? and testid=? and studentid=?", classid, subjectid, testid,resultobj.getStudentid()).results(StudentTestResult.class);
                List<ClassroomTestResult> classresultlist = db.where("classid=? and testid=?", classid,testid).results(ClassroomTestResult.class);

                StudentTestResult studenttestresult = new StudentTestResult();
                ClassroomTestResult classroomtestresult = new ClassroomTestResult();

                if (studenttestresultlist.isEmpty()) {
                    studenttestresult.setTestid(testid);
                    studenttestresult.setStudentid(resultobj.getStudentid());
                    studenttestresult.setSubjectid(subjectid);
                    studenttestresult.setMarks(resultobj.getMarks());
                    studenttestresult.setClassid(classid);
                    db.insert(studenttestresult).getRowsAffected();  
                } else {
                    StudentTestResult studentTestResult2 = db.where("classid=? and subjectid=? and studentid=? and testid=?", classid, subjectid,resultobj.getStudentid(), testid).results(StudentTestResult.class).get(0);
                    studentTestResult2.setMarks(resultobj.getMarks());
                    db.update(studentTestResult2).getRowsAffected();
                }
                if (classresultlist.isEmpty()) {
                    for (ResultsTransferObject resultobjforclassresult : resultstransferobject) {
                        classroomtestresult.setClassid(classid);
                        classroomtestresult.setTestid(testid);
                        classroomtestresult.setStudentid(resultobjforclassresult.getStudentid());
                        db.insert(classroomtestresult); 
                    }
                } else {
                    if (existingsubjectcount == marksenteredsubjectcount) {
                        ClassroomTestResult classresult = db.where("classid=? and studentid=? and testid=?", classid, resultobj.getStudentid(),testid).results(ClassroomTestResult.class).get(0);

                        classresult.setResultorgrade("A");

                        db.update(classresult);
                    }
                }

            }
            return 1;
        } else {
            return 0;
        }

    }

    private List<StudentTestResult> testResultList(long classid, long testcreateid, long subjectid, long tenantId) {
        db = retrieve.getDatabase(tenantId);

        return db.where("classid=? and testid=? and subjectid=?", classid, testcreateid, subjectid).results(StudentTestResult.class);
    }
}
