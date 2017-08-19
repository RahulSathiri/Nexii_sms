package com.omniwyse.sms.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.Messages;
import com.omniwyse.sms.utils.MessagesDTO;
import com.omniwyse.sms.utils.MessagesDetails;

@Service
public class MessagesService {
	@Autowired
	private DatabaseRetrieval retrive;
	private Database db;

	public int sendMessage(MessagesDTO messagesDTO, long tenantId, String sentflag) {
		if (messagesDTO.getMessage() != null) {
			if (messagesDTO.getSenderid() != 0 || messagesDTO.getRecievers() != null
					|| messagesDTO.getRecieverid() != 0) {
				db = retrive.getDatabase(tenantId);
				Timestamp today = new Timestamp(System.currentTimeMillis());
				Messages message = new Messages();
                message.setIsreply(true);
				message.setMessagedate(today);
				message.setMessage(messagesDTO.getMessage());
				message.setSentflag(sentflag);
				message.setSenderid(messagesDTO.getSenderid());
				message.setClassroomid(messagesDTO.getClassroomid());
				if (messagesDTO.getId() != 0) {
					message.setRecieverid(messagesDTO.getRecieverid());
					message.setRootmessageid(messagesDTO.getId());
					message.setParentmessageid(messagesDTO.getParentmessageid());
					return db.insert(message).getRowsAffected();

				}
				if (messagesDTO.getTypeofmessage() != null && messagesDTO.getTypeofmessage().equals("all")) {
					message.setRecieverid(-1);
                    message.setIsreply(false);
					return db.insert(message).getRowsAffected();
				}
				ArrayList<Long> recievers = messagesDTO.getRecievers();
				for (Long reciever : recievers) {
					message.setRecieverid(reciever);
					db.insert(message);

				}
				return 1;
			}

		} else {
			return 0;
		}

		return -1;
	}


	public List<MessagesDetails> teacherSentMessages(MessagesDTO messagesDTO, long tenantId) {
		db = retrive.getDatabase(tenantId);
		List<MessagesDetails> messages = db.sql("select messages.id,messages.message,messages.classroomid,messages.messagedate,messages.senderid,messages.recieverid,students.name,parents.fathername,parents.mothername "
						+ "from messages left join students on students.id=messages.recieverid left join parents on parents.id=students.parentid"
						+ " where messages.sentflag='T' and messages.senderid=? and messages.rootmessageid=0 order by messages.messagedate desc ",messagesDTO.getSenderid()).results(MessagesDetails.class);

		for (MessagesDetails message : messages) {
			List<MessagesDetails> replymessages = db.sql("select messages.message,messages.senderid,messages.classroomid,messages.recieverid,messages.messagedate from messages"
			        + " where parentmessageid=? order by messagedate asc", message.getId()).results(MessagesDetails.class);
			message.setReplymessages(replymessages);
		}

		return messages;
	}

	public List<MessagesDetails> parentSentMessages(MessagesDTO messagesDTO, long tenantId) {

		db = retrive.getDatabase(tenantId);

		List<MessagesDetails> messages = db.sql("select messages.id,messages.classroomid,messages.message,messages.messagedate,messages.recieverid,messages.senderid,teachers.teachername "
						+ "from messages join teachers on teachers.id=messages.recieverid where messages.sentflag='P' and messages.senderid=? and messages.rootmessageid=0"
						+ " order by messages.messagedate desc", messagesDTO.getSenderid()).results(MessagesDetails.class);

		for (MessagesDetails message : messages) {
			List<MessagesDetails> replymessages = db.sql("select messages.message,messages.classroomid,messages.messagedate from messages where messages.parentmessageid=? order by messagedate asc",
							message.getId()).results(MessagesDetails.class);

			message.setReplymessages(replymessages);
		}
		return messages;
	}

	public List<MessagesDetails> teacherRecievedMessages(MessagesDTO messagesDTO, long tenantId) {
		db = retrive.getDatabase(tenantId);
		List<MessagesDetails> messages= db.sql("select messages.id,messages.message,messages.messagedate,messages.rootmessageid,messages.senderid,messages.recieverid,students.name,parents.fathername,parents.mothername "
                        + "from messages join students on students.id=messages.senderid join parents on parents.id=students.parentid where sentflag='p' and recieverid=? and rootmessageid=0 order by messages.messagedate desc",
						   messagesDTO.getRecieverid()).results(MessagesDetails.class);
		for (MessagesDetails message : messages) {
			List<MessagesDetails> replymessages = db.sql("select messages.message,messages.senderid,messages.recieverid,messages.classroomid,messages.messagedate from messages"
			        + " where messages.parentmessageid=? order by messagedate asc",message.getId()).results(MessagesDetails.class);

			message.setReplymessages(replymessages);
		}
		return messages;
	}

	public List<MessagesDetails> parentRecievedMessages(MessagesDTO messagesDTO, long tenantId) {
		db = retrive.getDatabase(tenantId);
		List<MessagesDetails> messages = db.sql("select messages.id,messages.message,messages.messagedate,messages.classroomid,messages.rootmessageid,messages.senderid,messages.recieverid,teachers.teachername "
                        + "from messages join teachers on teachers.id=messages.senderid where sentflag='T' and recieverid=?  or (recieverid=-1 and classroomid=?) and messages.rootmessageid=0"
						+ " order by messages.messagedate desc",messagesDTO.getRecieverid(),messagesDTO.getClassroomid()).results(MessagesDetails.class);
		for (MessagesDetails message : messages) {
			List<MessagesDetails> replymessages = db.sql("select messages.message,messages.senderid,messages.recieverid,messages.classroomid,messages.messagedate"
			        + " from messages where messages.parentmessageid=? order by messagedate asc",message.getId()).results(MessagesDetails.class);

			message.setReplymessages(replymessages);
		}
		return messages;

	}

}
