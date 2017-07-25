package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.ClassRoom;
import com.omniwyse.sms.services.StudentsDateOfBirthService;
import com.omniwyse.sms.utils.DateOfBirthDTO;

@RestController
public class StudentsDateOfBirthController {

	@Autowired
	StudentsDateOfBirthService studentsDateOfBirthService;

	@RequestMapping("birthday/classteacher")
	public List<DateOfBirthDTO> getClassStudentsBirthDay(@RequestBody DateOfBirthDTO dateOfBirthDTO) {
		return studentsDateOfBirthService.getClassStudentsBirthDay(dateOfBirthDTO);

	}

	@RequestMapping("birthday/classsubjectteacher")
	public List<DateOfBirthDTO> getClassSubjectsStudentsBirthDay(@RequestBody DateOfBirthDTO dateOfBirthDTO) {
		return studentsDateOfBirthService.getClassSubjectsStudentsBirthDay(dateOfBirthDTO);
	}

	@RequestMapping("birthday/students")
	public List<DateOfBirthDTO> getStudentsBirthDays() {
		return studentsDateOfBirthService.getStudentsBirthDays();

	}
	
	@RequestMapping("/birthday/myclassstudentstoday")
	public List<DateOfBirthDTO> getBirthDaysOfMyClassStudents(@RequestBody ClassRoom classRoom) {
		return studentsDateOfBirthService.getBirthDaysOfMyClassStudents(classRoom);

	}
	@RequestMapping("/birthday/myclassstudentstomorrow")
	public List<DateOfBirthDTO> getTomorrowBirthDaysOfMyClassStudents(@RequestBody ClassRoom classRoom) {
		return studentsDateOfBirthService.getTomorrowBirthDaysOfMyClassStudents(classRoom);

	}
}
