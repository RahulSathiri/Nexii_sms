package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.Grades;
import com.omniwyse.sms.models.NoticeBoard;
import com.omniwyse.sms.utils.NoticeBoardTransferObject;

@Service
public class NoticeBoardService {
	@Autowired
    private com.omniwyse.sms.db.DatabaseRetrieval database;
	private NoticeBoard noticeboard;
	public Database db;

	public int postNotice(long tenantId, NoticeBoardTransferObject noticeboardTransferObject) {
		String gradename = noticeboardTransferObject.getGradename();
		String syllabustype = noticeboardTransferObject.getSyllabustype();
		db = database.getDatabase(tenantId);
		List<Grades> grade = db.where("syllabustype=? and gradename=?", syllabustype, gradename).results(Grades.class);
		if (grade.isEmpty()) {
			return -1;
		} else {
			long gradeid = grade.get(0).getId();
			noticeboard = new NoticeBoard();
			noticeboard.setNoticeid(gradeid);
			noticeboard.setNoticeddate(noticeboardTransferObject.getNoticeddate());
			noticeboard.setDescription(noticeboardTransferObject.getDescription());
			return db.insert(noticeboard).getRowsAffected();

		}

	}

	public int editNotice(long tenantId, NoticeBoard noticeboard) {
		db = database.getDatabase(tenantId);
	return	db.update(noticeboard).getRowsAffected();
		
	}

	public List<NoticeBoardTransferObject> listNotice(long tenantId) {
		db = database.getDatabase(tenantId);
	return	db.sql("select grades.gradename,noticeboard.date,noticeboard.description from noticeboard inner join grades on noticeboard.noticeid=grades.id").results(NoticeBoardTransferObject.class);
	}
	

}
