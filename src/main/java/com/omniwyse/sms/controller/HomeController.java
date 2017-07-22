package com.omniwyse.sms.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/home")
@RestController
public class HomeController {

    @GetMapping("/all")
    public String home(){
        return "home";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/secured/all")
    public String privatepage() {
        return "privatepage";
    }
}
