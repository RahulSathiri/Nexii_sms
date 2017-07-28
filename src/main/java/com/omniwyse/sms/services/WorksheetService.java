package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.Degreeofdifficulty;
import com.omniwyse.sms.models.Subjects;
import com.omniwyse.sms.models.Worksheets;
import com.omniwyse.sms.utils.WorkSheetsDTO;

@Service
public class WorksheetService {

	@Autowired
	private DatabaseRetrieval retrive;
	private Database db;

	public List<WorkSheetsDTO> listingWorksheetsOfStdLib(WorkSheetsDTO worksheets) {

		db = retrive.getDatabase(1);
		List<WorkSheetsDTO> list = db
				.sql("select worksheets.worksheetname, worksheets.createdby, worksheets.description,"
						+ " worksheets.gradeid, worksheets.worksheetpath, subjects.subjectname, degreeofdifficulty.description"
						+ " from worksheets JOIN subjects ON subjects.id = worksheets.subjectid"
						+ " JOIN degreeofdifficulty ON degreeofdifficulty.id = worksheets.degreeofdifficultyid")
				.results(WorkSheetsDTO.class);
		return list;
	}

	public List<WorkSheetsDTO> listingWorksheetsOfTenant(WorkSheetsDTO worksheets) {

		db = retrive.getDatabase(1);
		String subjectname = worksheets.getSubjectname();
		long gradeid = worksheets.getGradeid();
		String level = worksheets.getDegreeofdifficulty();

		String query = "select worksheets.worksheetname, worksheets.createdby, worksheets.description,"
				+ " worksheets.gradeid, worksheets.worksheetpath,worksheets.tags ,subjects.subjectname, degreeofdifficulty.degreeofdifficulty"
				+ " from worksheets JOIN subjects ON subjects.id = worksheets.subjectid"
				+ " JOIN degreeofdifficulty ON degreeofdifficulty.id = worksheets.degreeofdifficultyid";
		
		//if all are null call get method
		if (subjectname == null && gradeid == 0 && level == null) {

			return listingWorksheetsOfTenant();

		} else if (subjectname != null && gradeid == 0 && level == null) {

			long subjectid = db.where("subjectname = ?", subjectname).results(Subjects.class).get(0).getId();

			return db.sql(query + " where subjects.subjectid = ?", subjectid).results(WorkSheetsDTO.class);

		} else if (subjectname != null && gradeid != 0 && level == null) {

			long subjectid = db.where("subjectname = ?", subjectname).results(Subjects.class).get(0).getId();

			return db.sql(query + " where subjects.subjectid = ? and worksheets.gradeid = ?", subjectid, gradeid)
					.results(WorkSheetsDTO.class);
		} else if (subjectname != null && gradeid != 0 && level != null) {

			long subjectid = db.where("subjectname = ?", subjectname).results(Subjects.class).get(0).getId();
			long levelid = db.where("worksheets.degreeofdifficulty = ?", level).results(Degreeofdifficulty.class).get(0).getId();

			return db.sql(query + " where subjects.subjectid = ? and worksheets.gradeid = ? and worksheets.degreeofdifficultyid = ?", subjectid,
					gradeid, levelid).results(WorkSheetsDTO.class);
		} else if (subjectname == null && gradeid != 0 && level == null) {

			return db.sql(query + " where gradeid = ?", gradeid).results(WorkSheetsDTO.class);
		} else if (subjectname == null && gradeid != 0 && level != null) {
			
			long levelid = db.where("worksheets.degreeofdifficulty = ?", level).results(Degreeofdifficulty.class).get(0).getId();
			return db.sql(query + " where worksheets.gradeid = ? and worksheets.degreeofdifficultyid = ?", gradeid, levelid)
					.results(WorkSheetsDTO.class);
		}else if(subjectname == null && gradeid == 0 && level != null){
			
			long levelid = db.where("worksheets.degreeofdifficulty = ?", level).results(Degreeofdifficulty.class).get(0).getId();
			
			return db.sql(query + " where worksheets.degreeofdifficultyid = ?", levelid)
					.results(WorkSheetsDTO.class);
		}else {
			return null;
		}

	}

	public List<WorkSheetsDTO> listingWorksheetsOfTenant() {

		db = retrive.getDatabase(1);

		String query = "select worksheets.worksheetname, worksheets.createdby, worksheets.description,"
				+ " worksheets.gradeid, worksheets.worksheetpath,worksheets.tags ,subjects.subjectname, degreeofdifficulty.degreeofdifficulty"
				+ " from worksheets JOIN subjects ON subjects.id = worksheets.subjectid"
				+ " JOIN degreeofdifficulty ON degreeofdifficulty.id = worksheets.degreeofdifficultyid";

		return db.sql(query).results(WorkSheetsDTO.class);
	}

	public int uploadNewSheet(WorkSheetsDTO worksheets) {

		db = retrive.getDatabase(1);

		return 0;
	}

	public int createNewSheet(WorkSheetsDTO worksheets) {

		int rowEffected = 0;
		db = retrive.getDatabase(1);
		Worksheets sheet = new Worksheets();
		sheet.setWorksheetname(worksheets.getWorksheetname());
		sheet.setWorksheetpath(worksheets.getWorksheetpath());
		sheet.setCreatedby(worksheets.getCreatedby());

		long degreeofdifficultyid = db.where("degreeofdifficulty = ?", worksheets.getDegreeofdifficulty())
				.results(Degreeofdifficulty.class).get(0).getId();
		long subjectid = db.where("subjectname = ?", worksheets.getSubjectname()).results(Subjects.class).get(0)
				.getId();

		sheet.setDegreeofdifficultyid(degreeofdifficultyid);
		sheet.setDescription(worksheets.getDescription());
		sheet.setGradeid(worksheets.getGradeid());
		sheet.setSubjectid(subjectid);
		sheet.setTags(worksheets.getTags());

		rowEffected = db.insert(sheet).getRowsAffected();

		return rowEffected;
	}

	public List<WorkSheetsDTO> listDifficulty() {

		db = retrive.getDatabase(1);
		
		return db.sql("select * from degreeofdifficulty").results(WorkSheetsDTO.class);
	}
}
