package com.omniwyse.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.services.ActivitiesService;
import com.omniwyse.sms.utils.DashBoard;

@RestController
public class ActivityController {

    @Autowired
    private ActivitiesService service;

    @RequestMapping("/dashboard")
    public ResponseEntity<DashBoard> activities() {
        DashBoard dashboard = service.listOfActivities();
        return new ResponseEntity<DashBoard>(dashboard, HttpStatus.OK);
    }

}
