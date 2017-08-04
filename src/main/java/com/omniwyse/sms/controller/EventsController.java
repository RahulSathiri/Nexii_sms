package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.Events;
import com.omniwyse.sms.services.EventsService;
import com.omniwyse.sms.utils.Response;

@RestController
@RequestMapping("/{tenantId}")
public class EventsController {
	@Autowired
	private EventsService service;

	@Autowired
	private Response response;

    @RequestMapping(value = "/postevent", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Response> postEvent(@PathVariable("tenantId") long tenantId, @RequestBody Events events) {

        int rowEffected = service.postEvent(events, tenantId);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("event posted");
			response.setDescription("event posted successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {

			response.setStatus(400);
			response.setMessage("event already posted");
			response.setDescription("already posted");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping("/events")
	public List<Events> listOfEvents(@PathVariable("tenantId") long tenantId) {

		List<Events> list = service.listEvents(tenantId);
		return list;

	}

	@RequestMapping("/editevent")
	public ResponseEntity<Response> editEvent(@PathVariable("tenantId") long tenantId,@RequestBody Events event) {
		service.editEvent(event,tenantId);
		response.setStatus(200);
		response.setMessage("event updated");
		response.setDescription("event updated successfuly");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping("/deleteevent")
	public ResponseEntity<Response> listOfEvents(@PathVariable("tenantId") long tenantId,@RequestBody Events event) {
		int rowEffected=service.deleteEvent(event,tenantId);
		if(rowEffected>0)
		{
		response.setStatus(200);
		response.setMessage("event deleted");
		response.setDescription("event deleted successfuly");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		else
		{
			response.setStatus(400);
			response.setMessage("there is no such record to delete");
			response.setDescription("there is no such record to delete");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);	
		}

	}
}
