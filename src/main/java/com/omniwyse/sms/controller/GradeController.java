package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.Grades;
import com.omniwyse.sms.services.GradeService;
import com.omniwyse.sms.utils.ClassSectionTransferObject;
import com.omniwyse.sms.utils.GradeDTO;
import com.omniwyse.sms.utils.Response;

@RestController
@RequestMapping("/{tenantId}")
public class GradeController {

	@Autowired
	private Response response;

	@Autowired
	private GradeService service;

	@RequestMapping("/addgrade")
	public ResponseEntity<Response> addingGrade(@PathVariable("tenantId") long tenantId, @RequestBody GradeDTO addgrade) {

		int rowEffected = service.addGrade(tenantId, addgrade);

		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("Grade Added successfuly");
			response.setDescription("Grade successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		if (rowEffected == -1) {
			response.setStatus(400);
			response.setMessage("not a valid syllabustype");
			response.setDescription("not a valid syllabustype");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}

		else {

			response.setStatus(400);
			response.setMessage("Grade Existed with same syllabus");
			response.setDescription("Grade existed with same syllabus");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping("/listgrades")
	public List<Grades> listOfAllGrades(@PathVariable("tenantId") long tenantId) {

		return service.listAllGrades(tenantId);
	}
	
	@RequestMapping("/listdistinctgrades")
	public List<Grades> listOfDistinctGrades(@PathVariable("tenantId") long tenantId) {

		return service.listDistinctGrades(tenantId);
	}
	
	@RequestMapping("/listgradesofsyllabustype")
	public List<Grades> listGradesOfSyllabusType(@PathVariable("tenantId") long tenantId, @RequestBody ClassSectionTransferObject classtransferobject) {
		String syllabustype = classtransferobject.getSyllabustype();
		return service.getListOfGrades(tenantId, syllabustype);

	}

}
