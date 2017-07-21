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

	public int createPeriods(TimeTableDataTransferObject timetable) {
		db = retrive.getDatabase(1);
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
				clsprds.setDateofassigning(timetable.getDateofassigning());
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

	public List<TableView> getClassPeriods(Long id) {
		db = retrive.getDatabase(1);
		return db
				.sql("select wd.day,sbj.subjectname, cp.periodfrom, cp.periodto, cp.dateofassigning from classroom_periods cp join subjects sbj on sbj.id = cp.subjectid join weekdays wd on wd.id = cp.classroomweekdayid where classroomid=?",
						id)
				.results(TableView.class);
	}

	public List<WeekDays> getAllDays() {
		db = retrive.getDatabase(1);
		return db.sql("select * from weekdays").results(WeekDays.class);
	}

	// public List<ClassRoomTimeTablePeriods> listOfPeriods() {
	//
	// db = retrive.getDatabase(1);
	//
	// return db.sql("select * from
	// periods").results(ClassRoomTimeTablePeriods.class);
	// }
	//
	// public List<WeekDays> listOfWeekDays() {
	// db = retrive.getDatabase(1);
	//
	// return db.sql("select * from days").results(WeekDays.class);
	// }
	//
	// public TimeTableView WholeTimeTable(TimeTableDataTransferObject
	// timetableDTO) {
	//
	// db = retrive.getDatabase(1);
	//
	// TimeTableView table = new TimeTableView();
	// /*
	// * long academicyear = timetableDTO.getAcademicyear(); String gradeid =
	// * timetableDTO.getGradeid(); String section =
	// * timetableDTO.getSection();
	// */
	//
	// long classroomid = timetableDTO.getId();
	//
	// List<TimeTableDataTransferObject> periodsandtime = db
	// .sql("select class_period_maintanance.periodfrom,
	// class_period_maintanance.periodto from periods JOIN
	// class_period_maintanance where classroomid = ?",
	// classroomid)
	// .results(TimeTableDataTransferObject.class);
	// table.setPeriodsandtime(periodsandtime);
	//
	// List<TimeTableDataTransferObject> subjects = db
	// .sql("select subjects.subjectname from subjects JOIN
	// gradePeriod__subject_teacher ON subjects.id =
	// gradePeriod__subject_teacher.subjectid where classroomid = ?",
	// classroomid)
	// .results(TimeTableDataTransferObject.class);
	//
	// table.setSubjects(subjects);
	// List<WeekDays> days = db
	// .sql("select days.weekday from days JOIN gradePeriod__subject_teacher ON
	// days.id=gradePeriod__subject_teacher.weekdayid where classroomid = ?",
	// classroomid)
	// .results(WeekDays.class);
	// table.setWeekday(days);
	//
	// return table;
	// }

	// public int setTimeTable(TimeTableDataTransferObject timetable) {
	//
	// db = retrive.getDatabase(1);
	// String subject = timetable.getSubjectname();
	// String day = timetable.getWeekday();
	// String periodname = timetable.getPeriodname();
	//
	// GradePeriodSubjectTeacher gpst = new GradePeriodSubjectTeacher();
	//
	// long classroomid = timetable.getId();
	// long weekdayid = db.where("weekday =?",
	// day).results(WeekDays.class).get(0).getId();
	// long subjectid = db.where("subjectname = ?",
	// subject).results(Subjects.class).get(0).getId();
	//
	// //gpst.setPeriodid(
	// //db.where("periodname = ?",
	// periodname).results(ClassRoomTimeTablePeriods.class).get(0).getId());
	// gpst.setClassroomid(classroomid);
	// gpst.setSubjectid(subjectid);
	// gpst.setTeacherid(db.where("classid =? and subjectid = ?", classroomid,
	// subjectid)
	// .results(SubjectTeacherClass.class).get(0).getTeacherid());
	// gpst.setWeekdayid(weekdayid);
	// return db.insert(gpst).getRowsAffected();
	//
	// }

}
