package com.omniwyse.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.services.ActivitiesService;
import com.omniwyse.sms.utils.DashBoard;

@RestController
public class ActivityController {

    @Autowired
    private ActivitiesService service;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/{tenantId}/dashboard")
    public ResponseEntity<DashBoard> activities(@PathVariable("tenantId") long tenantId) {
        DashBoard dashboard = service.listOfActivities(tenantId);
        return new ResponseEntity<DashBoard>(dashboard, HttpStatus.OK);
    }

}
