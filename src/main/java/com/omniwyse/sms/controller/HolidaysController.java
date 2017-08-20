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

import com.omniwyse.sms.models.Holidays;
import com.omniwyse.sms.services.HolidaysService;
import com.omniwyse.sms.utils.Response;

@RestController
@RequestMapping(value = "/{tenantId}")
public class HolidaysController {
	@Autowired
	private HolidaysService service;

	@Autowired
	private Response response;


	@RequestMapping(value = "/postholiday", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> postHoliday(@RequestBody Holidays holiday) {

		int rowEffected = service.postHoliday(holiday);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("posted");
			response.setDescription("posted successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {

			response.setStatus(400);
			response.setMessage("already posted");
			response.setDescription("already posted");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}

	}


	@RequestMapping("/holidays")
    public List<Holidays> listOfHolidays(@PathVariable("tenantId") long tenantId) {

        List<Holidays> list = service.listOfHolidays(tenantId);

		return list;

	}


	@RequestMapping("/editholiday")
	public ResponseEntity<Response> editHoliday(@RequestBody Holidays holiday) {

		service.editHoliday(holiday);
		response.setStatus(200);
		response.setMessage("updated");
		response.setDescription("updated successfuly");
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}


	@RequestMapping("/deleteholiday")
	public ResponseEntity<Response> deleteHoliday(@RequestBody Holidays holiday) {

		service.deleteHoliday(holiday);

		response.setStatus(200);
		response.setMessage("deleted");
		response.setDescription("deleted successfuly");
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

}
