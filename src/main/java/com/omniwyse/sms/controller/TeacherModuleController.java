package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.Teachers;
import com.omniwyse.sms.services.TeacherModuleService;
import com.omniwyse.sms.utils.ClassRoomDetails;
import com.omniwyse.sms.utils.ClassSectionTransferObject;
import com.omniwyse.sms.utils.TeacherModuleDTO;


@RestController
public class TeacherModuleController {

	@Autowired
	private TeacherModuleService service;

	@RequestMapping("/mysubjects")
	public List<TeacherModuleDTO> listOfTeacherAssignedSubjects(@RequestBody ClassSectionTransferObject moduleDTO) {

		return service.listAllSubjectsAlongWithClassRooms(moduleDTO);
	}
	@RequestMapping("/mysubjects/{id}/{subjectname}")
	public ClassRoomDetails listStudentsAndTests(@RequestParam ("id") long id, @RequestParam ("subjectname") String subjectname){
		
		return service.teacherModuleList(id,subjectname);
		
	}

	@RequestMapping("/myclassroom")
	public List<ClassSectionTransferObject> listClassRoomAssignedAsClassRoomTeacher(
			@RequestBody ClassSectionTransferObject moduleDTO) {

		return service.getClassRoomOfTeacherAssignedCRT(moduleDTO);
	}

	@RequestMapping("/teacherprofile")
	public List<Teachers> showTeacherProfile(@RequestBody ClassSectionTransferObject teacher) {

		return service.showTeacherProfile(teacher);
	}
	
}
