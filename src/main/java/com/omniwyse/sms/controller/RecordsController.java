package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping("/teachers")
	public List<Teachers> getAllTeachers() {
		return recordservice.getAllTeachers();

	}

	@RequestMapping("/students")
	public List<StudentTransferObject> getAllStudents() {
		return recordservice.getAllStudents();

	}

	@RequestMapping("/subjects")
	public List<Subjects> getAllSubjects() {
		return recordservice.getAllSubjects();

	}

	@RequestMapping("/sections")
	public List<Subjects> getAllSections() {
		return recordservice.getAllSubjects();

	}

	@RequestMapping("/classroomdetails")
	public ClassRoomDetails getClassRoomDetails(@RequestBody ClassRoom classroom) {
		long id = classroom.getId();
		long gradeid = classroom.getGradeid();
		return recordservice.getClassRoomDetails(id, gradeid);

	}

	@RequestMapping("/syllabus")
	public List<Syllabus> getAllSyllabus() {
		return recordservice.getAllSyllabus();

	}

    @RequestMapping("/classroomsyllabustypes")
    public List<Grades> distinctSyllabusType() {

        return recordservice.getAllClassRoomSyllabusTypes();
    }

    @RequestMapping("/classroomacademicyears")
    public List<ClassRoom> getAcademicYears() {
        return recordservice.getAcademicYears();

    }
}