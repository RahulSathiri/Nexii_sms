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
        dashboard.setEvents(eventsService.listEvents(2));
        dashboard.setHolidays(holidayService.listOfHolidays(2));
        dashboard.setNews(newsService.listNews(2));

		return dashboard;
	}

}
