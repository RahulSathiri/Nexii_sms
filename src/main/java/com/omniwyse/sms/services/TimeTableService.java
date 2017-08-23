package com.omniwyse.sms.services;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.dieselpoint.norm.Transaction;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.ClassRoomPeriods;
import com.omniwyse.sms.models.Subjects;
import com.omniwyse.sms.models.WeekDays;
import com.omniwyse.sms.utils.ShowPeriods;
import com.omniwyse.sms.utils.TableView;
import com.omniwyse.sms.utils.TimeTableDataTransferObject;


@Service
public class TimeTableService {

	@Autowired
	private DatabaseRetrieval retrive;

	public Database db;

	public int createPeriods(long tenantId, TimeTableDataTransferObject timetable) {
		db = retrive.getDatabase(tenantId);
		int num = 0;
		Transaction transact = db.startTransaction();
		try {

			if (checkIfExistingTime(timetable)) {
				return -1;
			} else {

				ClassRoomPeriods clsprds = new ClassRoomPeriods();

				clsprds.setPeriodfrom((timetable.getPeriodfrom()));
				clsprds.setPeriodto(timetable.getPeriodto());
				clsprds.setClassroomweekdayid(timetable.getDayid());
				clsprds.setClassroomid(timetable.getId());
				clsprds.setSubjectid(
						db.where("subjectname =?", timetable.getSubjectname()).results(Subjects.class).get(0).getId());
				num = db.transaction(transact).insert(clsprds).getRowsAffected();
				transact.commit();
			}
		} catch (Throwable thr) {
			transact.rollback();
			return -3;
		}
		return num;
	}

	private boolean checkIfExistingTime(TimeTableDataTransferObject timetable) {
		boolean flag = true;
		LocalTime tFrom = timetable.getPeriodfrom();
		LocalTime tTo = timetable.getPeriodto();
		List<ShowPeriods> from = db
				.sql("select * from classroom_periods where periodfrom = ? and classroomweekdayid = ?", tFrom,
						timetable.getDayid())
				.results(ShowPeriods.class);
		List<ShowPeriods> to = db.sql("select * from classroom_periods where periodto = ? and classroomweekdayid = ?",
				tTo, timetable.getDayid()).results(ShowPeriods.class);

		if (from.isEmpty()) {
			if (to.isEmpty()) {
				List<ShowPeriods> existingFromTime = db
						.sql("select * from classroom_periods where classroomweekdayid =?", timetable.getDayid())
						.results(ShowPeriods.class);
				return isBetween(tFrom, tTo, existingFromTime);
			}
		}
		return flag;
	}

	@SuppressWarnings("deprecation")
	private boolean isBetween(LocalTime tFrom, LocalTime tTo, List<ShowPeriods> existingFromTime) {
		ClassRoomPeriods clsperiods = new ClassRoomPeriods();
		boolean flag = false;
		for (int i = 0; i < existingFromTime.size(); i++) {
			clsperiods.setPeriodfrom(LocalTime.of(existingFromTime.get(i).getPeriodfrom().getHours(),
					existingFromTime.get(i).getPeriodfrom().getMinutes()));
			clsperiods.setPeriodto(LocalTime.of(existingFromTime.get(i).getPeriodto().getHours(),
					existingFromTime.get(i).getPeriodto().getMinutes()));
			if (clsperiods.getPeriodfrom().compareTo(tFrom) == -1 && clsperiods.getPeriodfrom().compareTo(tTo) == -1) {
				return false;
			} else if (clsperiods.getPeriodfrom().compareTo(tFrom) == 1
					&& clsperiods.getPeriodto().compareTo(tFrom) == 1) {

				return false;
			}
		}
		return flag;
	}

	public List<TableView> getClassPeriods(long tenantId, Long id) {
		db = retrive.getDatabase(tenantId);

		return db.sql("select wd.day,sbj.subjectname, cp.periodfrom, cp.periodto, cp.dateofassigning from "
				+ "classroom_periods cp join subjects sbj on sbj.id = cp.subjectid join weekdays wd"
				+ " on wd.id = cp.classroomweekdayid where classroomid=?", id).results(TableView.class);

	}

	public List<WeekDays> getAllDays(long tenantId) {
		db = retrive.getDatabase(tenantId);
		return db.sql("select * from weekdays").results(WeekDays.class);
	}

}
