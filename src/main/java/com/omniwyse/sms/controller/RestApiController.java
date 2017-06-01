package com.omniwyse.sms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.Application;
import com.omniwyse.sms.models.Tenant;
import com.omniwyse.sms.services.TenantService;

@RestController
public class RestApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    TenantService service;

    @RequestMapping("/schools")
    public List<Tenant> getAllTenants() {
        LOGGER.info("getting all the schools");
        return (List<Tenant>) service.findSchools();
    }

}
