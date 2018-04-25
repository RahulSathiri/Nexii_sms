package com.omniwyse.sms.utils;

import java.sql.Date;
import java.util.List;

public class ResultsTransferObject {
	private long id;
	private long academicyear;
	private String syllabustype;
	private String gradename;
	private String sectionname;
	private String testtype;
	private long marks;
	private long classid;
	private String testmode;
	private String subjectname;
	private String name;
	private long admissionnumber;
	private long gradeid;
	private long subjectid;
	private Date startdate;
	private String resultorgrade;
	private long testid;
	private long studentid;
    private long maxmarks;
	private List<StudentSubjectMarks> studentsubjectmarks;
	
 
	public List<StudentSubjectMarks> getStudentsubjectmarks() {
		return studentsubjectmarks;
	}

	public void setStudentsubjectmarks(List<StudentSubjectMarks> studentsubjectmarks) {
		this.studentsubjectmarks = studentsubjectmarks;
	}

    public long getMaxmarks() {
        return maxmarks;
    }

    public void setMaxmarks(long maxmarks) {
        this.maxmarks = maxmarks;
    }

    public long getClassid() {
		return classid;
	}

	public void setClassid(long classid) {
		this.classid = classid;
	}

	public long getMarks() {
		return marks;
	}

	public void setMarks(long marks) {
		this.marks = marks;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public String getResultorgrade() {
		return resultorgrade;
	}

	public void setResultorgrade(String resultorgrade) {
		this.resultorgrade = resultorgrade;
	}

	public long getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(long subjectid) {
		this.subjectid = subjectid;
	}

	public long getStudentid() {
		return studentid;
	}

	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}

	public long getTestid() {
		return testid;
	}

	public void setTestid(long testid) {
		this.testid = testid;
	}

	public long getGradeid() {
		return gradeid;
	}

	public void setGradeid(long gradeid) {
		this.gradeid = gradeid;
	}

	public long getAdmissionnumber() {
		return admissionnumber;
	}

	public void setAdmissionnumber(long admissionnumber) {
		this.admissionnumber = admissionnumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAcademicyear() {
		return academicyear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setAcademicyear(long academicyear) {
		this.academicyear = academicyear;
	}

	public String getSyllabustype() {
		return syllabustype;
	}

	public void setSyllabustype(String syllabustype) {
		this.syllabustype = syllabustype;
	}

	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public String getSectionname() {
		return sectionname;
	}

	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}

	public String getTesttype() {
		return testtype;
	}

	public void setTesttype(String testtype) {
		this.testtype = testtype;
	}

	public String getTestmode() {
		return testmode;
	}

	public void setTestmode(String testmode) {
		this.testmode = testmode;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

}
