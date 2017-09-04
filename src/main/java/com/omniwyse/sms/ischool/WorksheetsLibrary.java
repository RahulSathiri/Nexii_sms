package com.omniwyse.sms.ischool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DBFactory;
import com.omniwyse.sms.utils.WorkSheetsDTO;

@RestController
public class WorksheetsLibrary {

	@Autowired
	private DBFactory database;
	private Database db;
	
	@Autowired
	private IschoolWorksheetsLibrary service;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	@RequestMapping("/worksheetsfromlibrary")
	public List<WorkSheetsDTO> listOfLibraryWorksheets(@RequestBody WorkSheetsDTO data) {
		db = database.getSchoolDb();
		return service.listingLibraryWorksheets(data,db);

	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	@RequestMapping("/listalllibraryworksheets")
	public List<WorkSheetsDTO> listAllWorksheetsOfLibrary(){
		db = database.getSchoolDb();
		return service.listingAllWorksheets(db);
	}
}
