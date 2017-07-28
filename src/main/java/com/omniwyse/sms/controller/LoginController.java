package com.omniwyse.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.UserCredentials;
import com.omniwyse.sms.services.LoginService;
import com.omniwyse.sms.utils.LoginResponse;

@RestController
@EnableAutoConfiguration
public class LoginController {

	@Autowired
	LoginService service;

    @RequestMapping(value = "/{tenantId}/userlogin", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<LoginResponse> userLogin(@PathVariable("tenantId") long tenantId,@RequestBody UserCredentials clients) {

        return service.userLogin(clients, tenantId);

	}

}
