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

	public DashBoard listOfActivities() {
		DashBoard dashboard = new DashBoard();
		dashboard.setEvents(eventsService.listEvents());
		dashboard.setHolidays(holidayService.listOfHolidays());
		dashboard.setNews(newsService.listNews());

		return dashboard;
	}

}
