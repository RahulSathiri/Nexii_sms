package com.omniwyse.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.services.RegistrationService;
import com.omniwyse.sms.utils.Response;
import com.omniwyse.sms.utils.Users;

@RestController
public class RegistrationController {

	@Autowired
	private final RegistrationService service;

	@Autowired
	private Response response;

	public RegistrationController(RegistrationService service) {
		this.service = service;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> register(@RequestBody Users users) {

		int result = service.registration(users);

		if (result > 0) {
			response.setStatus(202);
			response.setMessage("registration successful");
			response.setDescription("registrstion completed");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {
			response.setStatus(400);
			response.setMessage("already exist");
			response.setDescription("already exist");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

		}

	}

}
