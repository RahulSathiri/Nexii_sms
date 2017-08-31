package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.ClassRoom;
import com.omniwyse.sms.models.Grades;
import com.omniwyse.sms.models.Subjects;
import com.omniwyse.sms.models.Syllabus;
import com.omniwyse.sms.models.Teachers;
import com.omniwyse.sms.models.UserRoles;
import com.omniwyse.sms.services.RecordsService;
import com.omniwyse.sms.utils.ClassRoomDetails;
import com.omniwyse.sms.utils.StudentTransferObject;

@RestController
@RequestMapping("/{tenantId}")
public class RecordsController {

	@Autowired
	RecordsService recordservice;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping("/teachers")
	public List<Teachers> getAllTeachers(@PathVariable("tenantId") long tenantId) {
		return recordservice.getAllTeachers(tenantId);

	}

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping("/students")
	public List<StudentTransferObject> getAllStudents(@PathVariable("tenantId") long tenantId) {
		return recordservice.getAllStudents(tenantId);

	}

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	@RequestMapping("/subjects")
	public List<Subjects> getAllSubjects(@PathVariable("tenantId") long tenantId) {
		return recordservice.getAllSubjects(tenantId);

	}

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/sections")
	public List<Subjects> getAllSections(@PathVariable("tenantId") long tenantId) {
		return recordservice.getAllSubjects(tenantId);

	}

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	@RequestMapping("/classroomdetails")
	public ClassRoomDetails getClassRoomDetails(@PathVariable("tenantId") long tenantId,@RequestBody ClassRoom classroom) {
		long id = classroom.getId();
		long gradeid = classroom.getGradeid();
		return recordservice.getClassRoomDetails(id, gradeid,tenantId);

	}

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	@RequestMapping("/syllabus")
	public List<Syllabus> getAllSyllabus(@PathVariable("tenantId") long tenantId) {
		return recordservice.getAllSyllabus(tenantId);

	}

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    @RequestMapping("/classroomsyllabustypes")
    public List<Grades> distinctSyllabusType(@PathVariable("tenantId") long tenantId) {

        return recordservice.getAllClassRoomSyllabusTypes(tenantId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/classroomacademicyears")
    public List<ClassRoom> getAcademicYears(@PathVariable("tenantId") long tenantId) {
        return recordservice.getAcademicYears(tenantId);

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/roles")
    public List<UserRoles> getroles(@PathVariable("tenantId") long tenantId) {
    	int[] roleid={4,5};
        return recordservice.getroles(tenantId,roleid);

    }
    @RequestMapping("/teacherroles")
    public List<UserRoles> getteacherroles(@PathVariable("tenantId") long tenantId) {
    	int[] roleid={2,3};
    	return recordservice.getroles(tenantId,roleid);

    }

}