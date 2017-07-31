package com.omniwyse.sms.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.ClassroomAttendance;
import com.omniwyse.sms.services.ClassroomAttendenceService;
import com.omniwyse.sms.services.TeacherModuleService;
import com.omniwyse.sms.utils.ClassAttendenceTransferObject;
import com.omniwyse.sms.utils.ClassSectionTransferObject;
import com.omniwyse.sms.utils.Response;
import com.omniwyse.sms.utils.TeacherModuleDTO;

@RestController
public class ClassroomAttendenceController {

	@Autowired
	private ClassroomAttendenceService service;
	@Autowired
	private TeacherModuleService teacherService;
	
	@Autowired
	private Response response;
	
	//attendance
	@RequestMapping(value="/{tenantId}/attendance",method=RequestMethod.POST,produces="application/json")	
	public List<TeacherModuleDTO> listOfTeacherSubjects(@PathVariable("tenantId") long tenantId, @RequestBody ClassSectionTransferObject moduleDTO) {

		return teacherService.listAllSubjectsAlongWithClassRooms(tenantId,moduleDTO);
	}	
	
	
//list of students for the classroom attendance			
	
@RequestMapping(value="/{tenantId}/listofstudentsofclassroom",method=RequestMethod.POST,produces="application/json")
	public ClassAttendenceTransferObject listStudentsofClassroom(@PathVariable("tenantId") long tenantId, @RequestBody ClassAttendenceTransferObject classattendancetransferobject){
						
						return service.studentsList(tenantId,classattendancetransferobject);
}	
// attendance report
	
@RequestMapping(value = "/{tenantId}/recordattendance", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> getStudentsOfClassRoom(@PathVariable("tenantId") long tenantId, @RequestBody List<ClassAttendenceTransferObject> classattendancetransferobject) {
		
		int rowEffected= service.addingAttendanceStatus(tenantId,classattendancetransferobject);
		  
			if (rowEffected > 0) {
				response.setStatus(200);
				response.setMessage("attendance recorded");
				response.setDescription("attendance record added successfuly");
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			} 
			else {
				response.setStatus(400);
				response.setMessage(" attandance already taken");
				response.setDescription("attandance Record already exist");
				return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		
	}
}
			@RequestMapping("/{tenantId}/viewattendancedetails")
			public ClassAttendenceTransferObject  getattendance(@PathVariable("tenantId") long tenantId, @RequestBody ClassAttendenceTransferObject classattendancetransferobject) {
			return	service.getAttendance(tenantId,classattendancetransferobject);
			
			
	}

			@RequestMapping("/{tenantId}/listdates")
			public List<ClassroomAttendance> getdates(@PathVariable("tenantId") long tenantId)
			{
				return service.getdates(tenantId);	
			}
}
