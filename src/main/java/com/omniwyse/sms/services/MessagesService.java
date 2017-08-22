package com.omniwyse.sms.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.Messages;
import com.omniwyse.sms.models.Teachers;
import com.omniwyse.sms.utils.MessagesDTO;
import com.omniwyse.sms.utils.MessagesDetails;
import com.omniwyse.sms.utils.StudentTransferObject;

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
					if (messagesDTO.getRootmessageid() == 0) {
						message.setParentmessageid(messagesDTO.getId());
						message.setRootmessageid(messagesDTO.getId());
					} else {
						message.setParentmessageid(messagesDTO.getId());
						message.setRootmessageid(messagesDTO.getRootmessageid());
					}
					return db.insert(message).getRowsAffected();

				} else {
					if (messagesDTO.getRecievers() != null && !messagesDTO.getRecievers().isEmpty()) {
						if (messagesDTO.getRecievers().get(0) == -1) {
							message.setRecieverid(-1);
							message.setIsreply(false);
							return db.insert(message).getRowsAffected();
						} else {
							ArrayList<Long> recievers = messagesDTO.getRecievers();
							for (Long reciever : recievers) {
								message.setRecieverid(reciever);
								db.insert(message).getRowsAffected();
							}
							return 1;
						}
					}
					return 0;
				}

			}

			return 0;
		} else
			return -1;
	}

	public List<MessagesDetails> teacherSentMessages(MessagesDTO messagesDTO, long tenantId) {
		db = retrive.getDatabase(tenantId);
		List<MessagesDetails> messages = db.sql(
				"select messages.id,messages.message,messages.classroomid,messages.messagedate,messages.senderid,messages.recieverid,students.name,parents.fathername,parents.mothername "
						+ "from messages left join students on students.id=messages.recieverid left join parents on parents.id=students.parentid"
						+ " where messages.sentflag='T' and messages.senderid=? and messages.classroomid=? and messages.rootmessageid=0 and messages.parentmessageid=0 "
						+ "order by messages.messagedate desc ",
				messagesDTO.getSenderid(), messagesDTO.getClassroomid()).results(MessagesDetails.class);

		return getReplyMessages(messages);

	}

	public List<MessagesDetails> parentSentMessages(MessagesDTO messagesDTO, long tenantId) {

		db = retrive.getDatabase(tenantId);

		List<MessagesDetails> messages = db.sql(
				"select messages.id,messages.classroomid,messages.message,messages.messagedate,messages.recieverid,messages.senderid,teachers.teachername "
						+ "from messages join teachers on teachers.id=messages.recieverid where messages.sentflag='P' and messages.senderid=? and messages.classroomid=? "
						+ "and messages.rootmessageid=0 and messages.parentmessageid=0 order by messages.messagedate desc",
				messagesDTO.getSenderid(), messagesDTO.getClassroomid()).results(MessagesDetails.class);

		return getReplyMessages(messages);
	}

	public List<MessagesDetails> teacherRecievedMessages(MessagesDTO messagesDTO, long tenantId) {
		db = retrive.getDatabase(tenantId);
		List<MessagesDetails> messages = db.sql(
				"select messages.id,messages.message,messages.messagedate,messages.classroomid,messages.senderid,messages.recieverid,students.name,parents.fathername,parents.mothername "
						+ "from messages join students on students.id=messages.senderid join parents on parents.id=students.parentid where sentflag='p' and recieverid=? and classroomid=? "
						+ "and messages.rootmessageid=0 and messages.parentmessageid=0 order by messages.messagedate desc",
				messagesDTO.getRecieverid(), messagesDTO.getClassroomid()).results(MessagesDetails.class);
		return getReplyMessages(messages);
	}

	public List<MessagesDetails> parentRecievedMessages(MessagesDTO messagesDTO, long tenantId) {
		db = retrive.getDatabase(tenantId);
		List<MessagesDetails> messages = db.sql(
				"select messages.id,messages.message,messages.messagedate,messages.classroomid,messages.senderid,messages.recieverid,teachers.teachername "
						+ "from messages join teachers on teachers.id=messages.senderid where ((sentflag='T' and recieverid=?) or (recieverid=-1 and classroomid=?)) "
						+ "and messages.rootmessageid=0 and messages.parentmessageid=0 order by messages.messagedate desc",
				messagesDTO.getRecieverid(), messagesDTO.getClassroomid()).results(MessagesDetails.class);
		return getReplyMessages(messages);
	}

	public List<MessagesDetails> getSenderName(List<MessagesDetails> replymessages) {
		for (MessagesDetails replymessage : replymessages) {
			if (replymessage.getSentflag().equals("T")) {
				replymessage.setSendername(
						db.where("id=?", replymessage.getSenderid()).results(Teachers.class).get(0).getTeachername());
				List<StudentTransferObject> sendersnames=db.sql("select parents.fathername,students.name from parents join students on students.parentid=parents.id where students.id=?",
						replymessage.getRecieverid()).results(StudentTransferObject.class);
				if(!sendersnames.isEmpty())
				{
				replymessage.setFathername(sendersnames.get(0).getFathername());
				replymessage.setRecievername(sendersnames.get(0).getName());
				}
				
			} else
			{
						List<StudentTransferObject> sendersnames=db.sql("select parents.fathername,students.name from parents join students on students.parentid=parents.id where students.id=?",
								replymessage.getSenderid()).results(StudentTransferObject.class);
						if(!sendersnames.isEmpty())
						{
						replymessage.setSendername(sendersnames.get(0).getName());
						replymessage.setFathername(sendersnames.get(0).getFathername());
						}
						replymessage.setRecievername(db.where("id=?", replymessage.getRecieverid()).results(Teachers.class).get(0).getTeachername());
			}
		}
		return replymessages;
	}

	public List<MessagesDetails> getReplyMessages(List<MessagesDetails> messages) {
		for (MessagesDetails message : messages) {

			List<MessagesDetails> replymessages = db
					.sql("select messages.id,messages.message,messages.senderid,messages.sentflag,messages.classroomid,messages.recieverid,messages.messagedate from messages  where parentmessageid=? order by messagedate asc",
							message.getId())
					.results(MessagesDetails.class);

			message.setReplymessages(getSenderName(replymessages));
		}
		return messages;
	}

	public List<MessagesDetails> techerMessages(MessagesDTO messagesDTO, long tenantId) {
		db = retrive.getDatabase(tenantId);
		List<MessagesDetails> messages=
		db.sql("select messages.id,messages.message,messages.messagedate,messages.classroomid,messages.senderid,messages.recieverid,messages.sentflag from messages "
		+ "where ((sentflag='p' and recieverid=?) or (messages.sentflag='T' and messages.senderid=?))  and classroomid=? "
		+"and messages.rootmessageid=0 and messages.parentmessageid=0 order by messages.messagedate desc",messagesDTO.getSenderid(),messagesDTO.getSenderid(),messagesDTO.getClassroomid()).results(MessagesDetails.class);
		return getReplyMessages(getSenderName(messages));
		
	}

	public List<MessagesDetails> parentMessages(MessagesDTO messagesDTO, long tenantId) {
		db = retrive.getDatabase(tenantId);
		List<MessagesDetails> messages=
		db.sql("select messages.id,messages.message,messages.messagedate,messages.classroomid,messages.senderid,messages.recieverid,messages.sentflag " 
		+"from messages where ((sentflag='T' and (recieverid=? or recieverid=-1)) or (messages.sentflag='P' and messages.senderid=?))  and classroomid=? "
		+"and messages.rootmessageid=0 and messages.parentmessageid=0 order by messages.messagedate desc",messagesDTO.getSenderid(),messagesDTO.getSenderid(),messagesDTO.getClassroomid()).results(MessagesDetails.class);
		return getReplyMessages(getSenderName(messages));
	}
	

}
