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

import com.omniwyse.sms.models.Options;
import com.omniwyse.sms.services.OptionsService;
import com.omniwyse.sms.utils.Response;

@RestController
@RequestMapping("/{tenantId}")

public class OptionsController {
	@Autowired
	private OptionsService service;
	@Autowired
	private Response response;

	@RequestMapping(value = "/addoption", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> addOption(@PathVariable("tenantId") long tenantId, @RequestBody Options option) {

		int rowEffected = service.addOption(option, tenantId);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("option added");
			response.setDescription("option added successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMessage("option already exist");
			response.setDescription("option already exist");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

		}
	}
	@RequestMapping(value = "/editoption", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> editOption(@PathVariable("tenantId") long tenantId, @RequestBody Options option) {
		int rowEffected=service.editOption(option,tenantId);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("option edited");
			response.setDescription("option edited successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMessage("try again");
			response.setDescription("try again");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

		}
}
	@RequestMapping(value = "/deleteoption", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> deleteOption(@PathVariable("tenantId") long tenantId, @RequestBody Options option) {
		service.deleteOption(option,tenantId);
		response.setStatus(200);
		response.setMessage("option deleted");
		response.setDescription("option deleted successfuly");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
		
		
	}
	@RequestMapping(value = "/listoptions")
	public List<Options> deleteOption(@PathVariable("tenantId") long tenantId) {
		return service.getOptions(tenantId);
}
}
