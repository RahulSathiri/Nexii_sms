package com.omniwyse.sms.models;

import java.sql.Date;
import java.time.LocalTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "classroom_periods")
public class ClassRoomPeriods {
	
    private Long id;
    private LocalTime periodfrom;
    private LocalTime periodto;
    private Long classroomweekdayid;
    private Long classroomid;
    private long subjectid;
    private Date dateofassigning;

    public Date getDateofassigning() {
        return dateofassigning;
    }

    public void setDateofassigning(Date dateofassigning) {
        this.dateofassigning = dateofassigning;
    }

    public long getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(long subjectid) {
        this.subjectid = subjectid;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getPeriodfrom() {
        return periodfrom;
    }

    public void setPeriodfrom(LocalTime periodfrom) {
        this.periodfrom = periodfrom;
    }

    public LocalTime getPeriodto() {
        return periodto;
    }

    public void setPeriodto(LocalTime periodto) {
        this.periodto = periodto;
    }

    public Long getClassroomweekdayid() {
        return classroomweekdayid;
    }

    public void setClassroomweekdayid(Long classroomweekdayid) {
        this.classroomweekdayid = classroomweekdayid;
    }

    public Long getClassroomid() {
        return classroomid;
    }

    public void setClassroomid(Long classroomid) {
        this.classroomid = classroomid;
    }

}
