package com.omniwyse.sms.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.WeekDays;
import com.omniwyse.sms.services.TimeTableService;
import com.omniwyse.sms.utils.Response;
import com.omniwyse.sms.utils.TableView;
import com.omniwyse.sms.utils.TimeTableDataTransferObject;

@RestController
public class TimeTableController {

	@Autowired
	private TimeTableService service;
	@Autowired
	private Response response;

	@RequestMapping("/createperiods")
	public ResponseEntity<Response> addPeriod(@RequestBody TimeTableDataTransferObject timetable)
			throws ParseException {
		int rowEffected = service.createPeriods(timetable);
		if (rowEffected > 0) {
			response.setStatus(202);
			response.setMessage("period added");
			response.setDescription("success");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else if (rowEffected == -3) {
			response.setStatus(403);
			response.setMessage("Exception occured");
			response.setDescription("Exception");
			return new ResponseEntity<Response>(response, HttpStatus.FORBIDDEN);

		} else {

			response.setStatus(400);
			response.setMessage("record existed with same Time");
			response.setDescription("record existed");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// getting all periods data consider classroomid
	@RequestMapping("/timetable")
	public List<TableView> getAllPeriods(@RequestBody TimeTableDataTransferObject classperiods) {
		return service.getClassPeriods(classperiods.getId());
	}

	@RequestMapping("/listofweekdays")
	public List<WeekDays> getWeekDays() {
		return service.getAllDays();
	}

	// public ResponseEntity<Response> timeTable(@RequestBody
	// TimeTableDataTransferObject timetable) {
	//
	// int rowEffected = service.createPeriod(timetable);
	//
	// if (rowEffected == 2) {
	// response.setStatus(202);
	// response.setMessage("Time table created");
	// response.setDescription("Time table created successfully");
	//
	// return new ResponseEntity<Response>(HttpStatus.ACCEPTED);
	// } else {
	// response.setStatus(400);
	// response.setMessage("Time table not created");
	// response.setDescription("unable to create Time table");
	// return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
	// }
	// }
	//
	// @RequestMapping("/assignsubjecttoperiod")
	// public ResponseEntity<Response> setTimeTable(@RequestBody
	// TimeTableDataTransferObject timetable) {
	//
	// int rowEffected = service.setTimeTable(timetable);
	//
	// if (rowEffected == 2) {
	// response.setStatus(202);
	// response.setMessage("Time table created");
	// response.setDescription("Time table created successfully");
	//
	// return new ResponseEntity<Response>(HttpStatus.ACCEPTED);
	// } else {
	// response.setStatus(400);
	// response.setMessage("Time table not created");
	// response.setDescription("unable to create Time table");
	// return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
	// }
	// }
	//
	// @RequestMapping("/listofweekdays")
	// public List<WeekDays> listOfWeekDays() {
	//
	// List<WeekDays> days = service.listOfWeekDays();
	// return days;
	// }
	//
	// @RequestMapping("/listofperiods")
	// public List<ClassRoomTimeTablePeriods> listOfPeriods() {
	//
	// List<ClassRoomTimeTablePeriods> periods = service.listOfPeriods();
	// return periods;
	// }
	//
	// @RequestMapping("/timetable")
	// public TimeTableView timetable(@RequestBody TimeTableDataTransferObject
	// TimetableDTO){
	// return service.WholeTimeTable(TimetableDTO);
	// }
}