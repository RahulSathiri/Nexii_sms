package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.AcademicYears;
import com.omniwyse.sms.models.ClassRoom;
import com.omniwyse.sms.services.ClassService;
import com.omniwyse.sms.utils.ClassSectionTransferObject;
import com.omniwyse.sms.utils.Response;

@RestController
public class ClassController {
	@Autowired
	private ClassService service;

	@Autowired
	private Response response;

	@PostMapping
	@RequestMapping("/createclass")
	public ResponseEntity<Response> creatingClass(@RequestBody ClassSectionTransferObject createclass) {

		int rowEffected = service.createClass(createclass);

		if (rowEffected > 0) {
			response.setStatus(201);
			response.setMessage("ClassRoom created");
			response.setDescription("ClassRoom created");
			return new ResponseEntity<Response>(response, HttpStatus.CREATED);
		} else if (rowEffected == 0) {
			response.setStatus(400);
			response.setMessage("Not a Registered Teacher or assigned to some other class");
			response.setDescription("Not a Registered Teacher or assigned to some other class");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else if (rowEffected == -5) {
			response.setStatus(400);
			response.setMessage("Not a valid grade");
			response.setDescription("Not a valid grade you can't create classroom");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.setStatus(400);
			response.setMessage("ClassRoom already exist");
			response.setDescription("ClassRoom already exist");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	@RequestMapping("/updateclassteacher")
	public ResponseEntity<Response> updateClassTeachers(@RequestBody ClassSectionTransferObject createclass) {

		int rowEffected = service.updateClassTeacher(createclass);

		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("Class Teacher updated");
			response.setDescription("Class Teacher updated successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMessage("Teacher already assigned");
			response.setDescription("Teacher assigned as other class Teacher");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping("/classrooms/year")
	public List<ClassSectionTransferObject> getClassRoomaByYear(@RequestBody ClassRoom classroom) {
		long academicyear = classroom.getAcademicyear();
		return service.getClassRoomsByYear(academicyear);

	}

	@RequestMapping("/classrooms")
	public List<ClassSectionTransferObject> getClassRooms() {

		return service.getClassRooms();

	}

	@RequestMapping("/academicyear")
	public List<AcademicYears> getacademicyear() {

		return service.getAcademicYears();

	}

	@RequestMapping("/classrooms/yearandsyllabustype")
	public List<ClassSectionTransferObject> listSubjectTeac(
			@RequestBody ClassSectionTransferObject classtransferobject) {
		long academicyear = classtransferobject.getAcademicyear();
		String syllabustype = classtransferobject.getSyllabustype();
		return service.getClassRoomsByYearAndSyllabustype(academicyear, syllabustype);

	}
}