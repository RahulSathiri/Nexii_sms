package com.omniwyse.sms.ischool;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.utils.WorkSheetsDTO;

@Service
public class IschoolWorksheetsLibrary {


	public List<WorkSheetsDTO> listingLibraryWorksheets(WorkSheetsDTO worksheets, Database db) {

		String subjectname = worksheets.getSubjectname();
		long gradeid = worksheets.getGradeid();
		String level = worksheets.getDegreeofdifficulty();

		String query = "select ischool_worksheets.worksheetname, ischool_worksheets.createdby, ischool_worksheets.gradeid, "
				+ "ischool_worksheets.worksheetpath,ischool_worksheets.tags , ischool_subjects.subjectname, ischool_degreeofdifficulty.degreeofdifficulty "
				+ "from ischool_worksheets JOIN ischool_subjects ON ischool_subjects.id = ischool_worksheets.subjectid"
				+ "	JOIN ischool_degreeofdifficulty ON ischool_degreeofdifficulty.id = ischool_worksheets.degreeofdifficultyid" ;
		
		if (subjectname == null && gradeid == 0 && level == null) {

			return listingAllWorksheets(db);

		} else if (subjectname != null && gradeid == 0 && level == null) {

			long subjectid = db.where("subjectname = ?", subjectname).results(IschoolSubjects.class).get(0).getId();

			return db.sql(query + " where ischool_subjects.subjectid = ?", subjectid).results(WorkSheetsDTO.class);

		} else if (subjectname != null && gradeid != 0 && level == null) {

			long subjectid = db.where("subjectname = ?", subjectname).results(IschoolSubjects.class).get(0).getId();

			return db.sql(query + " where ischool_subjects.subjectid = ? and ischool_worksheets.gradeid = ?", subjectid, gradeid)
					.results(WorkSheetsDTO.class);
		} else if (subjectname != null && gradeid != 0 && level != null) {

			long subjectid = db.where("subjectname = ?", subjectname).results(IschoolSubjects.class).get(0).getId();
			long levelid = db.where("ischool_degreeofdifficulty.degreeofdifficulty = ?", level).results(IschoolLevelsOfDifficulty.class).get(0)
					.getId();

			return db.sql(
					query + " where ischool_subjects.subjectid = ? and ischool_worksheets.gradeid = ? and ischool_worksheets.degreeofdifficultyid = ?",
					subjectid, gradeid, levelid).results(WorkSheetsDTO.class);
		} else if (subjectname == null && gradeid != 0 && level == null) {

			return db.sql(query + " where ischool_worksheets.gradeid = ?", gradeid).results(WorkSheetsDTO.class);
		} else if (subjectname == null && gradeid != 0 && level != null) {

			long levelid = db.where("ischool_worksheets.degreeofdifficulty = ?", level).results(IschoolLevelsOfDifficulty.class).get(0)
					.getId();
			return db.sql(query + " where ischool_worksheets.gradeid = ? and ischool_worksheets.degreeofdifficultyid = ?", gradeid,
					levelid).results(WorkSheetsDTO.class);
		} else if (subjectname == null && gradeid == 0 && level != null) {

			long levelid = db.where("ischool_worksheets.degreeofdifficulty = ?", level).results(IschoolLevelsOfDifficulty.class).get(0)
					.getId();

			return db.sql(query + " where ischool_worksheets.degreeofdifficultyid = ?", levelid).results(WorkSheetsDTO.class);
		} else {
			return null;
		}

	}

	public List<WorkSheetsDTO> listingAllWorksheets(Database db2) {

		String query = "select ischool_worksheets.worksheetname, ischool_worksheets.createdby, ischool_worksheets.gradeid, "
				+ "ischool_worksheets.worksheetpath,ischool_worksheets.tags , ischool_subjects.subjectname, ischool_degreeofdifficulty.degreeofdifficulty "
				+ "from ischool_worksheets JOIN ischool_subjects ON ischool_subjects.id = ischool_worksheets.subjectid"
				+ "	JOIN ischool_degreeofdifficulty ON ischool_degreeofdifficulty.id = ischool_worksheets.degreeofdifficultyid";
		
		return db2.sql(query).results(WorkSheetsDTO.class);
	}
}
