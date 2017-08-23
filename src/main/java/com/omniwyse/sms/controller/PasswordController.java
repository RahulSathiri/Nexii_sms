package com.omniwyse.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.omniwyse.sms.services.PasswordService;
import com.omniwyse.sms.utils.PasswordDTO;
import com.omniwyse.sms.utils.Response;

@RestController
@RequestMapping("/{tenantId}")
public class PasswordController {
	@Autowired
	private Response response;
	@Autowired
	private PasswordService service;
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/changepassword")
	public ResponseEntity<Response> changePassword(@PathVariable("tenantId") long tenantId,
			@RequestBody PasswordDTO passwordDTO) {
		int rowEffected = service.changePassword(tenantId, passwordDTO);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("password changed successfully");
			response.setDescription("password changed successfully");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else if (rowEffected == 0) {
			response.setStatus(403);
			response.setMessage("not a valid credentials");
			response.setDescription("not a valid credentials");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else if (rowEffected == 0) {
			response.setStatus(403);
			response.setMessage("new password must be different from old password");
			response.setDescription("new password must be different from old password");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.setStatus(400);
			response.setMessage("please fill all the fields");
			response.setDescription("please fill all the fields");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}

	}

}
