package com.omniwyse.sms.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.services.TeacherModuleService;
import com.omniwyse.sms.utils.ClassSectionTransferObject;
import com.omniwyse.sms.utils.TeacherScheduleDTO;

@RestController
@RequestMapping("/teacherschedule")
public class TeacherScheduleController {

	@Autowired
	private TeacherModuleService service;

	@RequestMapping("/today")
	public List<TeacherScheduleDTO> defaultSchedule(@RequestBody ClassSectionTransferObject dataObject) {

		Date date = new Date(Calendar.getInstance().getTime().getTime());
		return service.getSchedule(dataObject, date);
	}

	@RequestMapping("/tomorrow")
	public List<TeacherScheduleDTO> scheduleOfNextday(@RequestBody ClassSectionTransferObject dataobject) {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = cal.getTime();
		return service.getSchedule(dataobject, tomorrow);
	}

}
