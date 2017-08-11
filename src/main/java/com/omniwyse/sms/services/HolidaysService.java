package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.Holidays;

@Service
public class HolidaysService {

	@Autowired
    private com.omniwyse.sms.db.DatabaseRetrieval retrive;
	private Database db;

	public int postHoliday(Holidays holiday) {
		db = retrive.getDatabase(1);
		String date = holiday.getFromdate();
		List<Holidays> holidays = db.where("fromdate=?", date).results(Holidays.class);
		if (holidays.isEmpty()) {
			return db.insert(holiday).getRowsAffected();
		} else
			return 0;

	}

    public List<Holidays> listOfHolidays(long tenantId) {
		db = retrive.getDatabase(1);
		return db.sql("select * from holidays").results(Holidays.class);
	}

	public int deleteHoliday(Holidays holiday) {
		db = retrive.getDatabase(1);
		return db.delete(holiday).getRowsAffected();
	}

	public int editHoliday(Holidays holiday) {
		db = retrive.getDatabase(1);
		return db.update(holiday).getRowsAffected();
	}

}
