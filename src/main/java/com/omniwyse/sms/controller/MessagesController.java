package com.omniwyse.sms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.services.MessagesService;
import com.omniwyse.sms.services.StudentsService;
import com.omniwyse.sms.utils.ClassRoomStudents;
import com.omniwyse.sms.utils.MessagesDTO;
import com.omniwyse.sms.utils.MessagesDetails;
import com.omniwyse.sms.utils.Response;
@RestController
@RequestMapping("/{tenantId}")
public class MessagesController {
	@Autowired
	MessagesService service;
	@Autowired
	private Response response;
	@Autowired StudentsService studentservice;
	
	@PreAuthorize("hasAnyRole('ROLE_TEACHER')")
	@RequestMapping("/sendmessagetoparent")
	public ResponseEntity<Response> sendMessageToParent(@PathVariable("tenantId") long tenantId,
			@RequestBody MessagesDTO messagesDTO) {
		 String sentflag="T";
		int rowEffected = service.sendMessage(messagesDTO, tenantId,sentflag);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("message sent successfully");
			response.setDescription("message sent successfully");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
			
		}
		if(rowEffected==0)
		{
			response.setStatus(400);
			response.setMessage("select parents to send message");
			response.setDescription("select parents to send message");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);	
		}
		
		else {
			response.setStatus(400);
			response.setMessage("message cannot be null");
			response.setDescription("message cannot be null");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_PARENT')")
	@RequestMapping("/sendmessagetoteacher")
	public ResponseEntity<Response> sendMessageToTeacher(@PathVariable("tenantId") long tenantId,
			@RequestBody MessagesDTO messagesDTO) {
		 String sentflag="P";
		int rowEffected = service.sendMessage(messagesDTO, tenantId,sentflag);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("message sent successfully");
			response.setDescription("message sent successfully");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		if(rowEffected==0)
		{
			response.setStatus(400);
			response.setMessage("select parents to send message");
			response.setDescription("select parents to send message");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);	
		}
		
		else {
			response.setStatus(400);
			response.setMessage("message cannot be null");
			response.setDescription("message cannot be null");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_TEACHER')")
	@RequestMapping("/teachersentmessages")
	public List<MessagesDetails> teacherSentMessages(@PathVariable("tenantId") long tenantId,
			@RequestBody MessagesDTO messagesDTO) {
	return	service.teacherSentMessages(messagesDTO,tenantId);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_TEACHER')")
	@RequestMapping("/teacherrecievedmessages")
	public List<MessagesDetails> teacherRecievedMessages(@PathVariable("tenantId") long tenantId,
			@RequestBody MessagesDTO messagesDTO) {
	return	service.teacherRecievedMessages(messagesDTO,tenantId);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_TEACHER')")
	@RequestMapping("/replytoparentmessages")
	public ResponseEntity<Response> replyToParentMessages(@PathVariable("tenantId") long tenantId,
			@RequestBody MessagesDTO messagesDTO) {
	 String sentflag="T";
	int rowEffected=	service.sendMessage(messagesDTO,tenantId,sentflag);
	if (rowEffected > 0) {
		response.setStatus(200);
		response.setMessage("message sent successfully");
		response.setDescription("message sent successfully");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	else
	{
		response.setStatus(400);
		response.setMessage("enter message");
		response.setDescription("enter message");
	return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);	
	}
	}

	@PreAuthorize("hasAnyRole('ROLE_PARENT')")
	@RequestMapping("/parentsentmessages")
	public List<MessagesDetails> parentSentMessages(@PathVariable("tenantId") long tenantId,
			@RequestBody MessagesDTO messagesDTO) {
	return	service.parentSentMessages(messagesDTO,tenantId);
	}

    @PreAuthorize("hasAnyRole('ROLE_PARENT')")
	@RequestMapping("/parentrecievedsmessages")
	public List<MessagesDetails> parentRecievedMessages(@PathVariable("tenantId") long tenantId,
			@RequestBody MessagesDTO messagesDTO) {
	return	service.parentRecievedMessages(messagesDTO,tenantId);
	}
	
    @PreAuthorize("hasAnyRole('ROLE_PARENT')")
	@RequestMapping("/replytoteachermessages")
    public ResponseEntity<Response> replyToTeacherMessages(@PathVariable("tenantId") long tenantId,
			@RequestBody MessagesDTO messagesDTO) {
	 String sentflag="P";
	int rowEffected=	service.sendMessage(messagesDTO,tenantId,sentflag);
	if (rowEffected > 0) {
		response.setStatus(200);
		response.setMessage("message sent successfully");
		response.setDescription("message sent successfully");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	else
	{
		response.setStatus(400);
		response.setMessage("enter message");
		response.setDescription("enter message");
	return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);	
	}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_TEACHER')")
	@RequestMapping("/{classroomid}/classroomstudents")
	public List<ClassRoomStudents> replyToTeacherMessages(@PathVariable("tenantId") long tenantId,
			@PathVariable("classroomid") long classroomid) {
		ClassRoomStudents classroomstudents = new ClassRoomStudents();
		classroomstudents.setName("All");
		classroomstudents.setId(-1);
		List<ClassRoomStudents> students = new ArrayList<ClassRoomStudents>();
		students.add(classroomstudents);
		students.addAll(studentservice.getStudentsOfClassRoom(classroomid, tenantId));
		return students;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_TEACHER')")
	@RequestMapping("/teachermessages")
	public List<MessagesDetails> teacherMessages(@PathVariable("tenantId") long tenantId,
			@RequestBody MessagesDTO messagesDTO) {
		return	service.techerMessages(messagesDTO,tenantId);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_PARENT')")
	@RequestMapping("/parentmessages")
	public List<MessagesDetails> parentMessages(@PathVariable("tenantId") long tenantId,
			@RequestBody MessagesDTO messagesDTO) {
		return service.parentMessages(messagesDTO, tenantId);
	}
}
