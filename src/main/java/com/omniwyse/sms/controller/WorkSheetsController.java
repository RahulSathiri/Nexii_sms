package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.services.WorksheetService;
import com.omniwyse.sms.utils.Response;
import com.omniwyse.sms.utils.WorkSheetsDTO;

@RestController
public class WorkSheetsController {

	@Autowired
	private WorksheetService service;

	@Autowired
	private Response response;

	@GetMapping
	@RequestMapping("/listlevelsofdifficulty")
	public List<WorkSheetsDTO> listDifficultyLevels(){
		
		return service.listDifficulty();
	}
	
	@RequestMapping("/listischoolworksheets")
	public List<WorkSheetsDTO> listingiSchoolWorkSheets(@RequestBody WorkSheetsDTO worksheets) {

		List<WorkSheetsDTO> list = service.listingWorksheetsOfStdLib(worksheets);

		return list;
	}

	@PostMapping
	@RequestMapping("/listmyworksheets")
	public List<WorkSheetsDTO> listingAllWorkSheets(@RequestBody WorkSheetsDTO worksheets) {

		List<WorkSheetsDTO> list = service.listingWorksheetsOfTenant(worksheets);

		return list;
	}

	@GetMapping
	@RequestMapping("/getlistmyworksheets")
	public List<WorkSheetsDTO> listingAllWorkSheets(){

		List<WorkSheetsDTO> list = service.listingWorksheetsOfTenant();

		return list;
	}

	@RequestMapping("/uploadmyworksheet")
	public ResponseEntity<Response> uploadWorkSheet(@RequestBody WorkSheetsDTO worksheets) {

		int rowEffected = service.uploadNewSheet(worksheets);

		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("WorkSheet Uploaded");
			response.setDescription("WorkSheet Uploaded successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {

			response.setStatus(400);
			response.setMessage("Worksheet Not Uploaded");
			response.setDescription("Not Uploaded");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping("/createmyworksheet")
	public ResponseEntity<Response> createWorkSheet(@RequestBody WorkSheetsDTO worksheets) {

		int rowEffected = service.createNewSheet(worksheets);

		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("WorkSheet Uploaded");
			response.setDescription("WorkSheet Uploaded successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {

			response.setStatus(400);
			response.setMessage("Worksheet Not Uploaded");
			response.setDescription("Not Uploaded");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}

	}
}