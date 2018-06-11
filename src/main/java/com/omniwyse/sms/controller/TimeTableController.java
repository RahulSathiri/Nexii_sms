package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.WeekDays;
import com.omniwyse.sms.services.TimeTableService;
import com.omniwyse.sms.utils.Response;
import com.omniwyse.sms.utils.TableView;
import com.omniwyse.sms.utils.TimeTableDataTransferObject;

@RestController
@RequestMapping("/{tenantId}")
public class TimeTableController {

	@Autowired
	private TimeTableService service;

	@Autowired
	private Response response;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping("/createperiods")
	public ResponseEntity<Response> addPeriod(@PathVariable("tenantId") long tenantId, @RequestBody TimeTableDataTransferObject timetable)
			 {
		int rowEffected = service.createPeriods(tenantId, timetable);
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	@RequestMapping("/timetable")
	public List<TableView> getAllPeriods(@PathVariable("tenantId") long tenantId, @RequestBody TimeTableDataTransferObject classperiods) {
		return service.getClassPeriods(tenantId, classperiods.getId());
	}

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping("/listofweekdays")
	public List<WeekDays> getWeekDays(@PathVariable("tenantId") long tenantId) {
		return service.getAllDays(tenantId);
	}
}