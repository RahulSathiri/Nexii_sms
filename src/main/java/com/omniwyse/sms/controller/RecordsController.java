package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.ClassRoom;
import com.omniwyse.sms.models.Grades;
import com.omniwyse.sms.models.Subjects;
import com.omniwyse.sms.models.Syllabus;
import com.omniwyse.sms.models.Teachers;
import com.omniwyse.sms.services.RecordsService;
import com.omniwyse.sms.utils.ClassRoomDetails;
import com.omniwyse.sms.utils.StudentTransferObject;

@RestController
public class RecordsController {

	@Autowired
	RecordsService recordservice;

	@RequestMapping("/{tenantId}/teachers")
	public List<Teachers> getAllTeachers(@PathVariable("tenantId") long tenantId) {
		return recordservice.getAllTeachers(tenantId);

	}

	@RequestMapping("/{tenantId}/students")
	public List<StudentTransferObject> getAllStudents(@PathVariable("tenantId") long tenantId) {
		return recordservice.getAllStudents(tenantId);

	}

	@RequestMapping("/{tenantId}/subjects")
	public List<Subjects> getAllSubjects(@PathVariable("tenantId") long tenantId) {
		return recordservice.getAllSubjects(tenantId);

	}

	@RequestMapping("/{tenantId}/sections")
	public List<Subjects> getAllSections(@PathVariable("tenantId") long tenantId) {
		return recordservice.getAllSubjects(tenantId);

	}

	@RequestMapping("/{tenantId}/classroomdetails")
	public ClassRoomDetails getClassRoomDetails(@PathVariable("tenantId") long tenantId,@RequestBody ClassRoom classroom) {
		long id = classroom.getId();
		long gradeid = classroom.getGradeid();
		return recordservice.getClassRoomDetails(id, gradeid,tenantId);

	}

	@RequestMapping("/{tenantId}/syllabus")
	public List<Syllabus> getAllSyllabus(@PathVariable("tenantId") long tenantId) {
		return recordservice.getAllSyllabus(tenantId);

	}

    @RequestMapping("/{tenantId}/classroomsyllabustypes")
    public List<Grades> distinctSyllabusType(@PathVariable("tenantId") long tenantId) {

        return recordservice.getAllClassRoomSyllabusTypes(tenantId);
    }

    @RequestMapping("/{tenantId}/classroomacademicyears")
    public List<ClassRoom> getAcademicYears(@PathVariable("tenantId") long tenantId) {
        return recordservice.getAcademicYears(tenantId);

    }
}