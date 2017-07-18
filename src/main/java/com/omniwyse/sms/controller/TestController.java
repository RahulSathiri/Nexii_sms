package com.omniwyse.sms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.TestSyllabus;
import com.omniwyse.sms.models.TestType;
import com.omniwyse.sms.models.Testmode;
import com.omniwyse.sms.services.TestService;
import com.omniwyse.sms.utils.Response;
import com.omniwyse.sms.utils.TestSubjectsDisplay;
import com.omniwyse.sms.utils.TestTransferObject;

@RestController
public class TestController {

	@Autowired
	private TestService service;
	@Autowired
	private Response response;

	@RequestMapping(value = "/addtesttype", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> addTestType(@RequestBody TestType testtype) {
		int rowEffected = service.addTestType(testtype);
		if (rowEffected > 0) {
			response.setStatus(202);
			response.setMessage("testtype added");
			response.setDescription("testtype  added successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {

			response.setStatus(400);
			response.setMessage("testtype already added");
			response.setDescription("testtype already exists in records");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping("/listtesttype")
	public List<TestType> listTestType() {
		return service.listtesttypes();

	}

	@RequestMapping("/listtestmode")
	public List<Testmode> listTestmode() {
		return service.listtestmodes();

	}

	@RequestMapping("/listalltests")
	public List<TestTransferObject> listTests() {
		return service.listAllTests();

	}

	@RequestMapping(value = "/createtest", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> createTest(@RequestBody TestTransferObject testcreate) {
		int rowEffected = service.createTest(testcreate);
		if (rowEffected > 0) {
			response.setStatus(202);
			response.setMessage("test scheduled");
			response.setDescription("test scheduled successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {

			response.setStatus(400);
			response.setMessage("test already scheduled");
			response.setDescription("test already scheduled");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/listtests", method = RequestMethod.POST, produces = "application/json")
	public List<TestTransferObject> getListOfTests(@RequestBody TestTransferObject testcreate) throws IOException {
		List<TestTransferObject> tests= service.getListOfTests(testcreate);
		if(tests.isEmpty())
		{
		return null;	
		}
		return tests; 

	}

	@RequestMapping(value = "/listtestsubjects", method = RequestMethod.POST, produces = "application/json")
	public List<TestSubjectsDisplay> getListOfTestSubjects(@RequestBody TestSubjectsDisplay testsubjectsdisplay) {
		return service.getListOfTestSubjects(testsubjectsdisplay);

	}
	
	

	@RequestMapping(value = "/addsyllabus", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> addSyllabus(@RequestBody TestSyllabus testsyllabus) {
		int rowEffected = service.addSyllabus(testsyllabus);
		if(rowEffected > 0){
		response.setStatus(202);
		response.setMessage("syllubs added successfully");
		response.setDescription("syllabus added successfuly");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
		}

	}

}
