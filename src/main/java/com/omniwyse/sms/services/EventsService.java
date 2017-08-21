package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.Events;

@Service
public class EventsService {
	@Autowired
    private com.omniwyse.sms.db.DatabaseRetrieval retrive;
	private Database db;

    public int postEvent(Events event, long tenantId) {

        db = retrive.getDatabase(tenantId);

		return db.insert(event).getRowsAffected();
	}

	public List<Events> listEvents(long tenantId) {

		db = retrive.getDatabase(tenantId);
		return db.sql("select * from events").results(Events.class);
	}

	public int deleteEvent(Events event, long tenantId) {
		db = retrive.getDatabase(tenantId);
		return db.delete(event).getRowsAffected();

	}

	public int editEvent(Events event, long tenantId) {
		db = retrive.getDatabase(tenantId);
		return db.update(event).getRowsAffected();

	}
}
