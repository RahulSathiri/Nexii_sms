package com.omniwyse.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.Subjects;
import com.omniwyse.sms.services.SubjectService;
import com.omniwyse.sms.utils.Response;

@RestController
public class SubjectController {
	
	@Autowired
	private SubjectService service;
	@Autowired
	private Response response;
	@PostMapping
	@RequestMapping("/addsubjects")

	public ResponseEntity<Response> addSubjects(@RequestBody Subjects subjects) {
		int result = service.addSubject(subjects);
		if (result > 0) {
			response.setStatus(202);
			response.setMessage("Subject added");
			response.setDescription("Subject added successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {
			response.setStatus(400);
			response.setMessage("Subject already Exists");
			response.setDescription("Subject already Exists");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}

	}
	
	
}
