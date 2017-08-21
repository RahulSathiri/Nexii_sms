package com.omniwyse.sms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omniwyse.sms.utils.DashBoard;

@Service
public class ActivitiesService {

	@Autowired
	private HolidaysService holidayService;

	@Autowired
	private NewsService newsService;

	@Autowired
	private EventsService eventsService;

	public DashBoard listOfActivities(long tenantId) {

		DashBoard dashboard = new DashBoard();
		dashboard.setEvents(eventsService.listEvents(tenantId));
		dashboard.setHolidays(holidayService.listOfHolidays(tenantId));
		dashboard.setNews(newsService.listNews(tenantId));
		return dashboard;
	}

}
