package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.Subjects;
import com.omniwyse.sms.services.SubjectTeacherClassService;
import com.omniwyse.sms.utils.ClassSectionTransferObject;
import com.omniwyse.sms.utils.Response;

@RestController
public class SubjectTeacherClassController {
	@Autowired
	private SubjectTeacherClassService service;

	@Autowired
	private Response response;

	@RequestMapping("/{tenantId}/listsubjectsofgrade")
	public List<Subjects> listSubjectsOfGrade(@PathVariable("tenantId") long tenantId,@RequestBody ClassSectionTransferObject classtransferobject) {
		long gradeid = classtransferobject.getGradeid();
		return service.getListOfSubjects(tenantId,gradeid);

	}

	@PostMapping
	@RequestMapping("/assignteachertosubject")
	@ResponseBody
	public ResponseEntity<Response> assignOrEditTeacherToSubject(
			@RequestBody ClassSectionTransferObject classtransferobject) {
		String teachername = classtransferobject.getTeachername();
		long classid = classtransferobject.getId();
		String subjectname = classtransferobject.getSubjectname();

		int rowEffected = service.assignTeacherToSubject(classid, teachername, subjectname);
		if (rowEffected > 0) {
			response.setStatus(202);
			response.setMessage("teacher assigned successfully");
			response.setDescription("teacher assigned successfully");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {
			response.setStatus(400);
			response.setMessage("same teacher already assigned");
			response.setDescription("same teacher already assigned");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

		}
	}

	@RequestMapping("/{tenantId}/listingassignedteachers")
	public List<ClassSectionTransferObject> listOfSubjectsToTeachers(
			@PathVariable("tenantId") long tenantId,@RequestBody ClassSectionTransferObject classtransferobject) {
		long id = classtransferobject.getId();
		List<ClassSectionTransferObject> list = service.listOfSubjectsTeachers(id,tenantId);
		return list;
	}

	@RequestMapping("/editsubjectteacher")
	public ResponseEntity<Response> updateSubjectTeachers(@RequestBody ClassSectionTransferObject classtransferobject) {
		long classid = classtransferobject.getId();
		String subjectname = classtransferobject.getSubjectname();
		String teachername = classtransferobject.getTeachername();

		service.updateSubjectTeacher(classid, subjectname, teachername);

		response.setStatus(202);
		response.setMessage("subject techer edited successfully");
		response.setDescription("subject teacher edited successfully");
		return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
	}

}
