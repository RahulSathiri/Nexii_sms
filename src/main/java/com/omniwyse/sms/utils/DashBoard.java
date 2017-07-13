package com.omniwyse.sms.utils;

import java.util.List;

import com.omniwyse.sms.models.Events;
import com.omniwyse.sms.models.Holidays;
import com.omniwyse.sms.models.NewsFeed;


public class DashBoard {

	private List<Events> events;
	private List<NewsFeed> news;
	private List<Holidays> holidays;
	
	public List<Events> getEvents() {
		return events;
	}
	public void setEvents(List<Events> events) {
		this.events = events;
	}
	public List<NewsFeed> getNews() {
		return news;
	}
	public void setNews(List<NewsFeed> news) {
		this.news = news;
	}
	public List<Holidays> getHolidays() {
		return holidays;
	}
	public void setHolidays(List<Holidays> holidays) {
		this.holidays = holidays;
	}
	
}
