package com.omniwyse.sms.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "classroom_testresult")
public class ClassroomTestResult {
    private long id;
    private long classid;
    private long testid;
    private String resultorgrade;
    private long studentid;

    public long getStudentid() {
        return studentid;
    }

    public void setStudentid(long studentid) {
        this.studentid = studentid;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClassid() {
        return classid;
    }

    public void setClassid(long classid) {
        this.classid = classid;
    }

    public long getTestid() {
        return testid;
    }

    public void setTestid(long testid) {
        this.testid = testid;
    }

    public String getResultorgrade() {
        return resultorgrade;
    }

    public void setResultorgrade(String resultorgrade) {
        this.resultorgrade = resultorgrade;
    }
}
