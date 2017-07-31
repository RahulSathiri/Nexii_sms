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

import com.omniwyse.sms.services.ResultsService;
import com.omniwyse.sms.utils.MainResultsTransferObject;
import com.omniwyse.sms.utils.Response;
import com.omniwyse.sms.utils.ResultsTransferObject;

@RestController
public class ResultsController {
	@Autowired
	private ResultsService service;
	@Autowired
	private Response response;
	
	
	@RequestMapping(value = "/{tenantId}/viewresults", method = RequestMethod.POST, produces = "application/json")
    public MainResultsTransferObject viewResults(@PathVariable("tenantId") long tenantId,@RequestBody ResultsTransferObject resultstransferobject) {
		return service.viewResults(resultstransferobject,tenantId);
	}

	@RequestMapping(value = "/{tenantId}/entermarks", method = RequestMethod.POST, produces = "application/json")
	public List<ResultsTransferObject> testresults(@PathVariable("tenantId") long tenantId,@RequestBody ResultsTransferObject resultstransferobject) {
		return service.enterMarks(resultstransferobject,tenantId);
	}
	
	@RequestMapping(value = "/{tenantId}/addmarks", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> addMarks(@PathVariable("tenantId") long tenantId,@RequestBody List<ResultsTransferObject> resultstransferobject) {
		 service.addMarks(resultstransferobject,tenantId);
		 response.setStatus(201);
			response.setMessage("added successfully");
			response.setDescription("added successfully");
			return new ResponseEntity<Response>(response, HttpStatus.CREATED);
		 
		 
	}


}
