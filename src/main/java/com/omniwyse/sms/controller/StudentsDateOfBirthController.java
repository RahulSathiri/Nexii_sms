package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.ClassRoom;
import com.omniwyse.sms.services.StudentsDateOfBirthService;
import com.omniwyse.sms.utils.DateOfBirthDTO;

@RestController
@RequestMapping("/{tenantId}")
public class StudentsDateOfBirthController {

	@Autowired
	StudentsDateOfBirthService studentsDateOfBirthService;

	@RequestMapping("/birthday/classteacher")
	public List<DateOfBirthDTO> getClassStudentsBirthDay(@PathVariable("tenantId") long tenantId,@RequestBody DateOfBirthDTO dateOfBirthDTO) {
		return studentsDateOfBirthService.getClassStudentsBirthDay(dateOfBirthDTO,tenantId);

	}

	@RequestMapping("/birthday/classsubjectteacher")
	public List<DateOfBirthDTO> getClassSubjectsStudentsBirthDay(@PathVariable("tenantId") long tenantId,@RequestBody DateOfBirthDTO dateOfBirthDTO) {
		return studentsDateOfBirthService.getClassSubjectsStudentsBirthDay(dateOfBirthDTO,tenantId);
	}

	@RequestMapping("/birthday/students")
	public List<DateOfBirthDTO> getStudentsBirthDays(@PathVariable("tenantId") long tenantId) {
		return studentsDateOfBirthService.getStudentsBirthDays(tenantId);

	}
	
	@RequestMapping("/birthday/myclassstudentstoday")
	public List<DateOfBirthDTO> getBirthDaysOfMyClassStudents(@PathVariable("tenantId") long tenantId,@RequestBody ClassRoom classRoom) {
		return studentsDateOfBirthService.getBirthDaysOfMyClassStudents(classRoom,tenantId);

	}
	@RequestMapping("/birthday/myclassstudentstomorrow")
	public List<DateOfBirthDTO> getTomorrowBirthDaysOfMyClassStudents(@PathVariable("tenantId") long tenantId,@RequestBody ClassRoom classRoom) {
		return studentsDateOfBirthService.getTomorrowBirthDaysOfMyClassStudents(classRoom,tenantId);

	}
}
