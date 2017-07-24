package com.omniwyse.sms.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@RequestMapping(value="/attendance",method=RequestMethod.POST,produces="application/json")	
	public List<TeacherModuleDTO> listOfTeacherSubjects(@RequestBody ClassSectionTransferObject moduleDTO) {

		return teacherService.listAllSubjectsAlongWithClassRooms(moduleDTO);
	}	
	
	
//list of students for the classroom attendance			
	
@RequestMapping(value="/listofstudentsofclassroom",method=RequestMethod.POST,produces="application/json")
	public ClassAttendenceTransferObject listStudentsofClassroom(@RequestBody ClassAttendenceTransferObject classattendancetransferobject){
						
						return service.studentsList(classattendancetransferobject);
}	
// attendance report
	
@RequestMapping(value = "/recordattendance", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> getStudentsOfClassRoom(@RequestBody List<ClassAttendenceTransferObject> classattendancetransferobject) {
		
		int rowEffected= service.addingAttendanceStatus(classattendancetransferobject);
		  
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
			@RequestMapping("/viewattendancedetails")
			public ClassAttendenceTransferObject  getattendance(@RequestBody ClassAttendenceTransferObject classattendancetransferobject) {
			return	service.getAttendance(classattendancetransferobject);
			
			
	}

			@RequestMapping("/listdates")
			public List<ClassroomAttendance> getdates()
			{
				return service.getdates();	
			}
}
