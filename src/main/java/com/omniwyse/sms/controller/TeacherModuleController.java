package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.Assignments;
import com.omniwyse.sms.models.ClassroomWorksheets;
import com.omniwyse.sms.models.Lessons;
import com.omniwyse.sms.models.Teachers;
import com.omniwyse.sms.services.TeacherModuleService;
import com.omniwyse.sms.utils.AssignmentDTO;
import com.omniwyse.sms.utils.ClassRoomDetails;
import com.omniwyse.sms.utils.ClassSectionTransferObject;
import com.omniwyse.sms.utils.Response;
import com.omniwyse.sms.utils.TeacherModuleDTO;
import com.omniwyse.sms.utils.TestTransferObject;
import com.omniwyse.sms.utils.TimelineDTO;
import com.omniwyse.sms.utils.WorkSheetsDTO;


@RestController
@RequestMapping("/{tenantId}")
public class TeacherModuleController {

	@Autowired
	private TeacherModuleService service;

	@Autowired
	private Response response;
	
	@RequestMapping("/mysubjects")
	public List<TeacherModuleDTO> listOfTeacherAssignedSubjects(@PathVariable("tenantId") long tenantId, @RequestBody ClassSectionTransferObject moduleDTO) {
		return service.listAllSubjectsAlongWithClassRooms(tenantId,moduleDTO);
	}
	@RequestMapping("/mysubjects/{id}/{subjectname}")
	public ClassRoomDetails listStudentsAndTests(@PathVariable("tenantId") long tenantId, @PathVariable ("id") long id, @PathVariable ("subjectname") String subjectname){
		
		return service.teacherModuleList(tenantId,id,subjectname);
		
	}
	
	@RequestMapping("/mysubjectsstudents/{id}/{subjectname}")
	public ClassRoomDetails listStudents(@PathVariable("tenantId") long tenantId, @PathVariable ("id") long id, @PathVariable ("subjectname") String subjectname){
		
		return service.teacherModulestudentsList(tenantId,id,subjectname);
		
	}
	
	@RequestMapping("/subjectstests/{id}/{subjectname}")
	public List<TestTransferObject> getListOfTests(@PathVariable("tenantId") long tenantId, @PathVariable ("id") long id, @PathVariable ("subjectname") String subjectname) {
		List<TestTransferObject> tests= service.getListOfsubjectTests(tenantId,id,subjectname);
		return tests; 

	}

	@RequestMapping("/myclassroom")
	public List<ClassSectionTransferObject> listClassRoomAssignedAsClassRoomTeacher(@PathVariable("tenantId") long tenantId, 
			@RequestBody ClassSectionTransferObject moduleDTO) {

		return service.getClassRoomOfTeacherAssignedCRT(tenantId,moduleDTO);
	}

	@RequestMapping("/myclassroomtests/{id}")
	public List<TestTransferObject> getListOfClassroomTests(@PathVariable("tenantId") long tenantId, @PathVariable ("id") long id){
		
		return service.getListOfClassroomTests(tenantId,id);
	}
	@RequestMapping("/teacherprofile")
	public Teachers showTeacherProfile(@PathVariable("tenantId") long tenantId, @RequestBody ClassSectionTransferObject teacher) {

		return service.showTeacherProfile(tenantId,teacher);
	}
	@RequestMapping("/timeline")
	public List<TimelineDTO> timelineView(@PathVariable("tenantId") long tenantId, @RequestBody TimelineDTO data){
		
		return service.viewTimeline(tenantId, data);
	}
	
	@RequestMapping("/addlesson")
	public ResponseEntity<Response> addLessonToSubject(@PathVariable("tenantId") long tenantId,
			@RequestBody TimelineDTO data) {

		int rowEffected = service.addingLesson(tenantId, data);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("data recorded successfuly");
			response.setDescription("data recorded");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else if (rowEffected == -3) {
			response.setStatus(403);
			response.setMessage("Exception occured");
			response.setDescription("please contact Backend team for resolving");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.setStatus(400);
			response.setMessage("data not recorded");
			response.setDescription("data recording failed");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/teacherschedule/listofworksheets")
	public List<WorkSheetsDTO> listOFWorksheets(@PathVariable("tenantId") long tenantId, @RequestBody WorkSheetsDTO data){
		
		return service.listWorkSheetsbasedOn(tenantId, data);
	}

	@RequestMapping("/assignassignment")
	public ResponseEntity<Response> assignmentAssigning(@PathVariable("tenantId") long tenantId, @RequestBody AssignmentDTO assigning) {

		int rowEffected = service.assignAssignment(tenantId, assigning);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("Assigned successfuly");
			response.setDescription("Assigned successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMessage("failed to assign");
			response.setDescription("failed to assign to class");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping("/deleteassignment")
	public ResponseEntity<Response> deleteAssignedAssignment(@PathVariable("tenantId") long tenantId, @RequestBody Assignments data) {
		int rowEffected=service.deleteAssignedAssignment(data,tenantId);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("deleted successfuly");
			response.setDescription("deleted successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMessage("try again");
			response.setDescription("try again");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping("/assignworksheet")
	public ResponseEntity<Response> worksheetAssigning(@PathVariable("tenantId") long tenantId, @RequestBody WorkSheetsDTO data) {

		int rowEffected = service.worksheets(tenantId, data);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("Assigned successfuly");
			response.setDescription("Assigned successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMessage("already assigned");
			response.setDescription("already assigned");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping("/deleteworksheet")
	public ResponseEntity<Response> deleteAssignedWorksheet(@PathVariable("tenantId") long tenantId, @RequestBody ClassroomWorksheets data) {
		int rowEffected=service.deleteAssignedWorksheet(data,tenantId);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("deleted successfuly");
			response.setDescription("deleted successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMessage("try again");
			response.setDescription("try again");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}

	}

	
	
	@RequestMapping("/lessonslist")
	public List<Lessons> listOfLessons(@PathVariable("tenantId") long tenantId, @RequestBody TimelineDTO data){
		
		return service.lessonsList(tenantId, data);
	}
	
	@RequestMapping("/assignedassignmentslist")
	public List<AssignmentDTO> listOfAssignedAssignments(@PathVariable("tenantId") long tenantId,
			@RequestBody TimelineDTO data) {

		return service.AssignmentsList(tenantId, data);
	}
	
	@RequestMapping("/assignedworksheetslist")
	public List<WorkSheetsDTO> listOfAssignedWorksheets(@PathVariable("tenantId") long tenantId,
			@RequestBody TimelineDTO data) {

		return service.AssignedWorksheetsList(tenantId, data);
	}
}