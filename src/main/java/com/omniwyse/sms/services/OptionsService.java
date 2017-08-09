package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.AttendanceMode;
import com.omniwyse.sms.models.Options;

public class OptionsService {
	@Autowired
    private DatabaseRetrieval retrive;
	private Database db;
	public int addOption(Options option, long tenantId) {
		db=retrive.getDatabase(tenantId);
		List<Options> record=db.where("name=?", option.getName()).results(Options.class);
		if(record.isEmpty())
		{
		return	db.insert(option).getRowsAffected();
		db = retrive.getDatabase(tenantId);
		String attendancetype=option.getOptionvalue();
		List<AttendanceMode> status = db.where("status=1").results(AttendanceMode.class);
		if (status.isEmpty()){
		db.sql("update school_attendance set status=1 where attendance_type=?",attendancetype).execute();
		return "updated";
		}
		else
		{
		return 0;
		}
	}
	public int  editOption(Options option, long tenantId) {
		db=retrive.getDatabase(tenantId);
		return db.update(option).getRowsAffected();
	}
	public int deleteOption(Options option, long tenantId) {
		db=retrive.getDatabase(tenantId);
		return db.delete(option).getRowsAffected();
	}
	
	

}
