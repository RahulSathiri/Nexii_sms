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

import com.omniwyse.sms.services.NotificationService;
import com.omniwyse.sms.utils.NotificationsDTO;
import com.omniwyse.sms.utils.Response;

@RestController
@RequestMapping("{tenantid}")
public class NotificationController {

	@Autowired
	private NotificationService service;

	@Autowired
	private Response response;
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping("/publishnotification")
	public ResponseEntity<Response> newNotification(@PathVariable("tenantid") long tenantId,
			@RequestBody NotificationsDTO notifications) {

		int rowEffected = service.publishNotification(tenantId, notifications);

		if (rowEffected > 0) {
			response.setStatus(202);
			response.setMessage("Notification published");
			response.setDescription("Notification published and you can send it");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else if (rowEffected == 0) {
			response.setStatus(400);
			response.setMessage("Notification publishing failed");
			response.setDescription("Notification publishing failed");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

		} else {
			response.setStatus(400);
			response.setMessage("Exception occured");
			response.setDescription("Exception from Backend");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PARENT')")
	@RequestMapping("/publishednotifications")
	public List<NotificationsDTO> listPublishedNotifications(@PathVariable("tenantid") long tenantid,
			@RequestBody NotificationsDTO data) {

		return service.listAllPublishednNotifications(tenantid, data);
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping("/listsentnotifications")
	public List<NotificationsDTO> listSentNotifications(@PathVariable("tenantid") long tenantid,
			@RequestBody NotificationsDTO data) {

		return service.listSentPublishednNotifications(tenantid, data);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping("/sendnotification")
	public ResponseEntity<Response> sendNotification(@PathVariable("tenantid") long tenantid,
			@RequestBody NotificationsDTO data) {

		return null;
	}
}
