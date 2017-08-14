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

import com.omniwyse.sms.models.StudentClassroom;
import com.omniwyse.sms.models.Students;
import com.omniwyse.sms.services.StudentsService;
import com.omniwyse.sms.utils.ClassRoomStudents;
import com.omniwyse.sms.utils.Response;
import com.omniwyse.sms.utils.StudentTransferObject;

@RestController
@RequestMapping("/{tenantId}")
public class StudentsController {

	@Autowired
	private StudentsService service;
	@Autowired
	private Response response;

	@RequestMapping(value = "/addstudent", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> addStudent(@PathVariable("tenantId") long tenantId,@RequestBody StudentTransferObject addStudent) {

		int rowEffected = service.addStudent(addStudent,tenantId);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("Student added");
			response.setDescription("Student added successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else if (rowEffected == 0) {
			response.setStatus(400);
			response.setMessage("invalid admissionnumber");
			response.setDescription("Student already registered");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else if(rowEffected==-5){
			response.setStatus(400);
			response.setMessage("enter valid emailid of parent");
			response.setDescription("enter valid emailid of parent");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
		else
		{
			response.setStatus(400);
			response.setMessage("try again");
			response.setDescription("try again");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);	
		}
	}

	@RequestMapping(value = "/updatestudent", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> updateStudent(@PathVariable("tenantId") long tenantId,@RequestBody Students updateStudent) {

		service.updateStudent(updateStudent,tenantId);
		response.setStatus(200);
		response.setMessage("Student details updated");
		response.setDescription("Student details updated successfuly");
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/addstudenttoclassroom", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> addstudenttoclassroom(@PathVariable("tenantId") long tenantId,@RequestBody Students addStudent) {
		String admissionnumber = addStudent.getAdmissionnumber();
		long classid = addStudent.getId();
		service.addStudentToClassroom(admissionnumber, classid,tenantId);
		response.setStatus(200);
		response.setMessage("Student added successfully");
		response.setDescription("Student added successfuly");
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}


	@RequestMapping(value = "/liststudentsofclassroom", method = RequestMethod.POST, produces = "application/json")
	public List<ClassRoomStudents> getStudentsOfClassRoom(@PathVariable("tenantId") long tenantId,@RequestBody StudentClassroom studentclassroom) {
		long classid = studentclassroom.getId();
		return service.getStudentsOfClassRoom(classid,tenantId);

	}

}
