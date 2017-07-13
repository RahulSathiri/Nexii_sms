package com.omniwyse.sms.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.services.ClassroomAttendenceService;
import com.omniwyse.sms.utils.ClassAttendenceTransferObject;
import com.omniwyse.sms.utils.Response;

@RestController
public class ClassroomAttendenceController {

	@Autowired
	private ClassroomAttendenceService service;
	
	@Autowired
	private Response response;
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
				response.setMessage("duplicate attandance Record");
				response.setDescription("attandance Record already exist");
				return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		
	}
	}
			
			@RequestMapping(value = "/attendancereport", method = RequestMethod.POST, produces = "application/json")
			public List<ClassAttendenceTransferObject> recordsOfAttendance(@RequestBody ClassAttendenceTransferObject classattendancetransferobject){
	
			return service.recordsOfAttendance(classattendancetransferobject);
			
			
			}
}