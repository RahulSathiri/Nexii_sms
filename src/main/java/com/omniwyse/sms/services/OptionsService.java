package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.Options;
@Service
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
	public List<Options> getOptions(long tenantId) {
		db=retrive.getDatabase(tenantId);
	return 	db.sql("select * from options").results(Options.class);
	}
	
	

}
