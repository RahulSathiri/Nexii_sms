package com.omniwyse.sms.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	@RequestMapping("/today")
	public List<TeacherScheduleDTO> defaultSchedule(@PathVariable("tenantId") long tenantId, @RequestBody ClassSectionTransferObject dataObject) {
		Calendar cal = Calendar.getInstance();
		int dayId = cal.get(Calendar.DAY_OF_WEEK);
		return service.getSchedule(tenantId,dataObject, dayId);
	}

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	@RequestMapping("/tomorrow")
	public List<TeacherScheduleDTO> scheduleOfNextday(@PathVariable("tenantId") long tenantId, @RequestBody ClassSectionTransferObject dataobject) {
    	Calendar cal = Calendar.getInstance();
		int dayId = cal.get(Calendar.DAY_OF_WEEK);
		if(dayId == 7){
			dayId = 0;
		}
		return service.getSchedule(tenantId, dataobject, dayId+1);
	}

}
