package com.omniwyse.sms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.services.MainService;

@RestController
public class MainController {

    @Autowired
    MainService mainservice;

    @RequestMapping(value = "/tenant/for/{domain}")
    public @ResponseBody Long getTenant(@PathVariable("domain") String domain, HttpServletRequest request, HttpServletResponse response) {
        // String url = null;
        // try {
        // url = new URL(request.getRequestURL().toString()).toString();
        // } catch (MalformedURLException e) {
        // e.printStackTrace();
        // }
        // String user[] = url.split("/");
        // return mainservice.getTenant(user[user.length - 1]);
        return mainservice.getTenant(domain);

    }

}
