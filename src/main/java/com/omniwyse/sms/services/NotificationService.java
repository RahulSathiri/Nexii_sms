package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.dieselpoint.norm.Transaction;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.Lessons;
import com.omniwyse.sms.models.Notifications;
import com.omniwyse.sms.utils.NotificationsDTO;

@Service
public class NotificationService {

	@Autowired
	private DatabaseRetrieval retrieve;
	private Database db;

	public int publishNotification(long tenantId, NotificationsDTO data) {

		int rowEffected = 0;
		db = retrieve.getDatabase(tenantId);

		Notifications notifications = new Notifications();
		Transaction transaction = db.startTransaction();

		try {
			notifications.setNotificationname(data.getNotificationname());
			notifications.setDescription(data.getDescription());
			checkActioncode(data);
			notifications.setActioncode(data.getActioncode());
			notifications.setParentactionrequired(data.getParentactionrequired());
			notifications.setPublishedby(data.getPublishedby());
			notifications.setNotificationdate(data.getNotificationdate());
			rowEffected = db.transaction(transaction).insert(notifications).getRowsAffected();
			transaction.commit();

		} catch (Exception e) {
			transaction.rollback();
			return -1;
		}
		return rowEffected;
	}

	private void checkActioncode(NotificationsDTO data) {

		long id = data.getId();
		if (data.getActioncode().equals("Timeline")) {

			db.sql("upadte lessons set publishtimeline = true").where("id = ?", id).results(Lessons.class);

		} /*
			 * else if (data.getActioncode().equals("Tests")) {
			 * 
			 * db.update("").where("id = ?", id); }
			 */

	}

	public List<NotificationsDTO> listAllPublishednNotifications(long tenantid, NotificationsDTO data) {

		db = retrieve.getDatabase(tenantid);

		List<NotificationsDTO> list = db.sql("select * from notifications where status = 0")
				.results(NotificationsDTO.class);

		return list;
	}

	public List<NotificationsDTO> listSentPublishednNotifications(long tenantid, NotificationsDTO data) {

		db = retrieve.getDatabase(tenantid);

		List<NotificationsDTO> list = db.sql("select * from notifications where status = 1")
				.results(NotificationsDTO.class);

		return list;
	}

}
