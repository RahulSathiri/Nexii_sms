package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class StudentsController {

	@Autowired
	private StudentsService service;
	@Autowired
	private Response response;

	@RequestMapping(value = "/addstudent", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> addStudent(@RequestBody StudentTransferObject addStudent) {

		int rowEffected = service.addStudent(addStudent);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("Student added");
			response.setDescription("Student added successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else if (rowEffected == 0) {
			response.setStatus(400);
			response.setMessage("Student already exist");
			response.setDescription("Student already Exists");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.setStatus(400);
			response.setMessage("Invalid ClassRoom");
			response.setDescription("ClassRoom does't exist");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/updatestudent", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> updateStudent(@RequestBody Students updateStudent) {

		service.updateStudent(updateStudent);
		response.setStatus(200);
		response.setMessage("Student details updated");
		response.setDescription("Student details updated successfuly");
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/addstudenttoclassroom", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> addstudenttoclassroom(@RequestBody Students addStudent) {
		String admissionnumber = addStudent.getAdmissionnumber();
		long classid = addStudent.getId();
		service.addStudentToClassroom(admissionnumber, classid);
		response.setStatus(200);
		response.setMessage("Student added successfully");
		response.setDescription("Student added successfuly");
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}


	@RequestMapping(value = "/liststudentsofclassroom", method = RequestMethod.POST, produces = "application/json")
	public List<ClassRoomStudents> getStudentsOfClassRoom(@RequestBody StudentClassroom studentclassroom) {
		long classid = studentclassroom.getId();
		return service.getStudentsOfClassRoom(classid);

	}

}
