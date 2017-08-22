package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.omniwyse.sms.utils.ResultsTransferObject;


@Controller
public class TennantSpecificController {

    @RequestMapping(value = "/viewtenant", method = RequestMethod.POST, produces = "application/json")
    public List<ResultsTransferObject> viewTestResults(@RequestBody ResultsTransferObject resultstransferobject) {
        return null;
    }

}
