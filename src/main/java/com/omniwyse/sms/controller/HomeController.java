package com.omniwyse.sms.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping(value = "/home")
@RestController
public class HomeController {

    @RequestMapping(value = { "/", "/home**" }, method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    // @Secured(value = { "ADMIN" })
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public String privatepage() {
        return "privatepage";
    }

    @Secured(value = { "SUPERADMIN" })
    @RequestMapping(value = "/superadmin**", method = RequestMethod.GET)
    public String superprivatepage() {
        return "superprivatepage";
    }
}
