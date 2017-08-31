package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.omniwyse.sms.models.Notifications;
import com.omniwyse.sms.models.StudentClassroom;
import com.omniwyse.sms.services.ParentService;
import com.omniwyse.sms.utils.AttendanceDTO;
import com.omniwyse.sms.utils.MainResultsTransferObject;
import com.omniwyse.sms.utils.ParentDTO;

@RestController
@RequestMapping("/{tenantId}")
public class ParentController {
	@Autowired
	private ParentService service;

	@PreAuthorize("hasAnyRole('ROLE_PARENT')")
	@RequestMapping(value = "/parentchildrens", method = RequestMethod.POST, produces = "application/json")
	public List<ParentDTO> listParentChildrens(@PathVariable("tenantId") long tenantId,
			@RequestBody StudentClassroom studentClassroom) {
		long parentid = studentClassroom.getId();
		return service.listParentChildrens(parentid, tenantId);
	}

	@PreAuthorize("hasAnyRole('ROLE_PARENT')")
	@RequestMapping(value = "/marks", method = RequestMethod.POST, produces = "application/json")
	public MainResultsTransferObject studentsMarks(@PathVariable("tenantId") long tenantId,
			@RequestBody ParentDTO parentDTO) {
		long studentid = parentDTO.getStudentid();
		long classid = parentDTO.getClassid();
		long gradeid = parentDTO.getGradeid();
		long testcreateid = parentDTO.getId();
		return service.studentsMarks(studentid, gradeid, classid, testcreateid, tenantId);
	}

	@PreAuthorize("hasAnyRole('ROLE_PARENT')")
	@RequestMapping(value = "/parentnotifications")
	public List<Notifications> notifications(@PathVariable("tenantId") long tenantId) {
		return service.notifications(tenantId);
	}

	@PreAuthorize("hasAnyRole('ROLE_PARENT')")
	@RequestMapping(value = "/studentattendance")
	public List<AttendanceDTO> attendanceReport(@PathVariable("tenantId") long tenantId,
			@RequestBody StudentClassroom studentClassroom) {
		long classid = studentClassroom.getClassid();
		long studentid = studentClassroom.getStudentid();
		return service.attendanceReport(classid, studentid, tenantId);
	}

}
