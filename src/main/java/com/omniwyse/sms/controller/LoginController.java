package com.omniwyse.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.Clients;
import com.omniwyse.sms.services.LoginService;
import com.omniwyse.sms.utils.LoginResponse;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/{tenantId}")
public class LoginController {

	@Autowired
	LoginService service;

    @Autowired
    private LoginResponse response;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/userlogin", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<LoginResponse> userLogin(@PathVariable("tenantId") long tenantId, @RequestBody Clients clients) {
        response.setUserId(service.getUserId(clients, tenantId));
        response.setStatus(200);
        response.setDescription("success");
        return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);

	}

}
