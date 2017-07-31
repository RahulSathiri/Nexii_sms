package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.Teachers;
import com.omniwyse.sms.services.TeacherModuleService;
import com.omniwyse.sms.utils.ClassRoomDetails;
import com.omniwyse.sms.utils.ClassSectionTransferObject;
import com.omniwyse.sms.utils.TeacherModuleDTO;
import com.omniwyse.sms.utils.TestTransferObject;


@RestController
public class TeacherModuleController {

	@Autowired
	private TeacherModuleService service;

	@RequestMapping("/{tenantId}/mysubjects")
	public List<TeacherModuleDTO> listOfTeacherAssignedSubjects(@PathVariable("tenantId") long tenantId, @RequestBody ClassSectionTransferObject moduleDTO) {

		return service.listAllSubjectsAlongWithClassRooms(tenantId,moduleDTO);
	}
	@RequestMapping("/{tenantId}/mysubjects/{id}/{subjectname}")
	public ClassRoomDetails listStudentsAndTests(@PathVariable("tenantId") long tenantId, @PathVariable ("id") long id, @PathVariable ("subjectname") String subjectname){
		
		return service.teacherModuleList(tenantId,id,subjectname);
		
	}
	
	@RequestMapping("/{tenantId}/mysubjectsstudents/{id}/{subjectname}")
	public ClassRoomDetails listStudents(@PathVariable("tenantId") long tenantId, @PathVariable ("id") long id, @PathVariable ("subjectname") String subjectname){
		
		return service.teacherModulestudentsList(tenantId,id,subjectname);
		
	}
	
	@RequestMapping("/{tenantId}/subjectstests/{id}/{subjectname}")
	public List<TestTransferObject> getListOfTests(@PathVariable("tenantId") long tenantId, @PathVariable ("id") long id, @PathVariable ("subjectname") String subjectname) {
		List<TestTransferObject> tests= service.getListOfsubjectTests(tenantId,id,subjectname);
		return tests; 

	}

	@RequestMapping("/{tenantId}/myclassroom")
	public List<ClassSectionTransferObject> listClassRoomAssignedAsClassRoomTeacher(@PathVariable("tenantId") long tenantId, 
			@RequestBody ClassSectionTransferObject moduleDTO) {

		return service.getClassRoomOfTeacherAssignedCRT(tenantId,moduleDTO);
	}

	@RequestMapping("/{tenantId}/teacherprofile")
	public List<Teachers> showTeacherProfile(@PathVariable("tenantId") long tenantId, @RequestBody ClassSectionTransferObject teacher) {

		return service.showTeacherProfile(tenantId,teacher);
	}
	
}
