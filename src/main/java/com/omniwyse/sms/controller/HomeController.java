package com.omniwyse.sms.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.utils.UserAndRoles;

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

    @RolesAllowed("hasAuthority('SUPERADMIN')")
    @RequestMapping(value = "/{tenantId}/superadmin**", method = RequestMethod.POST, produces = "application/json")
    public String superprivatepage(@PathVariable("tenantId") long tenantId, @RequestBody UserAndRoles user) {
        return "superprivatepage";
    }
}
