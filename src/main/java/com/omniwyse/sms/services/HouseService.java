package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.House;
import com.omniwyse.sms.models.Students;
import com.omniwyse.sms.utils.HouseDTO;
@Service
public class HouseService {
	@Autowired
	private DatabaseRetrieval retrieve;
	private Database db;

	public List<House> listHouses(long tenantId) {
		db = retrieve.getDatabase(tenantId);
		return db.sql("select * from houses").results(House.class);

	}

	public int addHouseDetails(House house, long tenantId) {
		db = retrieve.getDatabase(tenantId);
		List<House> records = db.where("housename=?", house.getHousename()).results(House.class);
		if (records.isEmpty()) {
			return db.insert(house).getRowsAffected();
		}
		return 0;
	}

	public int editHouseDetails(House house, long tenantId) {
		db = retrieve.getDatabase(tenantId);
		return db.update(house).getRowsAffected();
	}

	public int deleteHouseDetails(House house, long tenantId) {
		db = retrieve.getDatabase(tenantId);
		List<Students> records = db.where("houseid=?", house.getId()).results(Students.class);
		if (records.isEmpty()) {
			return db.delete(house).getRowsAffected();
		} else
			return 0;
	}

	public List<HouseDTO> listnoOfStudentsInHouse(House house, long tenantId) {
		db = retrieve.getDatabase(tenantId);
		List<HouseDTO> records = db
				.sql("select gradeid,gradename,syllabustype,count(*) as noofstudents from students "
						+ "join grades on grades.id=students.gradeid " + "join houses on houses.id=students.houseid "
						+ "where houses.housename=? " + "group by gradeid,gradename,syllabustype", house.getHousename())
				.results(HouseDTO.class);
		for (HouseDTO record : records) {
			long totalNoofStudents = db
					.sql("select count(*) as count from students where gradeid=?", record.getGradeid())
					.first(Long.class);
			record.setTotalnoofstudents(totalNoofStudents);
		}
		return records;
	}

}
