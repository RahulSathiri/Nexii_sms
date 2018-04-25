package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/{tenantId}")
public class ResultsController {

	@Autowired
	private ResultsService service;

	@Autowired
	private Response response;
	
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	@RequestMapping(value = "/viewresults", method = RequestMethod.POST, produces = "application/json")
    public MainResultsTransferObject viewResults(@PathVariable("tenantId") long tenantId,@RequestBody ResultsTransferObject resultstransferobject) {
		return service.viewResults(resultstransferobject,tenantId);
	}

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	@RequestMapping(value = "/entermarks", method = RequestMethod.POST, produces = "application/json")
	public List<ResultsTransferObject> testresults(@PathVariable("tenantId") long tenantId,@RequestBody ResultsTransferObject resultstransferobject) {
		return service.enterMarks(resultstransferobject,tenantId);
	}
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	@RequestMapping(value = "/addmarks", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> addMarks(@PathVariable("tenantId") long tenantId,@RequestBody List<ResultsTransferObject> resultstransferobject) {
        int val = service.addMarks(resultstransferobject, tenantId);
        if (val > 0) {
            response.setStatus(201);
            response.setMessage("added successfully");
            response.setDescription("added successfully");
            return new ResponseEntity<Response>(response, HttpStatus.CREATED);
        } else {
            response.setStatus(500);
            response.setMessage("problem adding");
            response.setDescription("marks not added");
            return new ResponseEntity<Response>(response, HttpStatus.NOT_MODIFIED);
        }
	}
}
