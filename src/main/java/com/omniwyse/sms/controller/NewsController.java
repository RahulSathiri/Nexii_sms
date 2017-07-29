package com.omniwyse.sms.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.NewsFeed;
import com.omniwyse.sms.services.NewsService;
import com.omniwyse.sms.utils.Response;
import com.omniwyse.sms.utils.UserAndRoles;

@RestController
public class NewsController {
	@Autowired
	private NewsService service;

	@Autowired
	private Response response;

    @RolesAllowed("hasAuthority('SUPERADMIN','ADMIN')")
    @RequestMapping(value = "/{tenantId}/postnews", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Response> postNews(@PathVariable("tenantId") long tenantId, @RequestBody UserAndRoles user,
            @RequestBody NewsFeed news) {

        int rowEffected = service.postNews(tenantId, user, news);
		if (rowEffected > 0) {
			response.setStatus(202);
			response.setMessage("news posted");
			response.setDescription("news posted successfuly");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {

			response.setStatus(400);
			response.setMessage("news already posted");
			response.setDescription("already posted");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping("/news")
	public List<NewsFeed> listOfNews() {

		List<NewsFeed> list = service.listNews();

		return list;

	}

    @PreAuthorize("hasRole('SUPERADMIN','ADMIN')")
	@RequestMapping("/editnews")
	public ResponseEntity<Response> editNews(@RequestBody NewsFeed newsfeed) {

		service.editNews(newsfeed);
		response.setStatus(200);
		response.setMessage("news updated");
		response.setDescription("news updated successfuly");
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

    @PreAuthorize("hasRole('SUPERADMIN','ADMIN')")
	@RequestMapping("/deletenews")
	public ResponseEntity<Response> deleteNews(@RequestBody NewsFeed newsfeed) {

		service.deleteNews(newsfeed);
		response.setStatus(200);
		response.setMessage("news deleted");
		response.setDescription("news deleted successfuly");
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}
}
