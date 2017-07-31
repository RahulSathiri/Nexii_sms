package com.omniwyse.sms.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.services.TeacherModuleService;
import com.omniwyse.sms.utils.ClassSectionTransferObject;
import com.omniwyse.sms.utils.TeacherScheduleDTO;

@RestController
@RequestMapping("/{tenantId}/teacherschedule")
public class TeacherScheduleController {

	@Autowired
	private TeacherModuleService service;

	@RequestMapping("/today")
	public List<TeacherScheduleDTO> defaultSchedule(@PathVariable("tenantId") long tenantId, @RequestBody ClassSectionTransferObject dataObject) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		
		String date = format1.format(Calendar.getInstance().getTime().getTime());
		return service.getSchedule(tenantId,dataObject, date);
	}

	@RequestMapping("/tomorrow")
	public List<TeacherScheduleDTO> scheduleOfNextday(@PathVariable("tenantId") long tenantId, @RequestBody ClassSectionTransferObject dataobject) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		String tomorrow = format1.format(cal.getTime());
		return service.getSchedule(tenantId, dataobject, tomorrow);
	}

}
