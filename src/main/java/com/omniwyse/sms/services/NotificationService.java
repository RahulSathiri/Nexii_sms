package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.dieselpoint.norm.Transaction;
import com.omniwyse.sms.db.DatabaseRetrieval;
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
			int rows = checkActioncode(tenantId, data);
			if(rows!=0)
			{
			notifications.setActioncode(data.getActioncode());
			notifications.setParentactionrequired(data.getParentactionrequired());
			notifications.setPublishedby(data.getPublishedby());
			notifications.setNotificationdate(data.getNotificationdate());
			rowEffected = db.transaction(transaction).insert(notifications).getRowsAffected();
			transaction.commit();
			}

		} catch (Exception e) {
			transaction.rollback();
			return -1;
		}
		return rowEffected;
	}

	private int checkActioncode(long tenantId, NotificationsDTO data) {
		
		db = retrieve.getDatabase(tenantId);
		int rowEffected = 0;
		long id = data.getId();
		if (data.getActioncode().equals("Timeline")) {
			long notificationid=isExistInNotifications(data);
			if(notificationid!=0){
				rowEffected = db.sql("update lessons set publishtimeline = ? where id = ?", false, id).execute()
						.getRowsAffected();
				db.sql("delete from notifications where id=?",notificationid).execute();
				return 0;
			} else {
				 db.sql("update lessons set publishtimeline = ? where id = ?",true, id).execute()
						.getRowsAffected();
				
			}
		}

		/*
			 * else if (data.getActioncode().equals("Tests")) {
			 * 
			 * db.update("").where("id = ?", id); }
			 */
		return 1;
	}

	private long isExistInNotifications(NotificationsDTO data) {

		List<Notifications> notification=db.where("notificationname = ?", data.getNotificationname()).results(Notifications.class);
		if (!notification.isEmpty()) {
			return notification.get(0).getId();
		}

		return 0;
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